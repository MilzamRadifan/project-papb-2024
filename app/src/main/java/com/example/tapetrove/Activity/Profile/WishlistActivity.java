package com.example.tapetrove.Activity.Profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tapetrove.Activity.Search.SearchAdapter;
import com.example.tapetrove.Database.Wishlist;
import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WishlistActivity extends AppCompatActivity {
  private FirebaseAuth mAuth;
  private DatabaseReference databaseReference;
  private FirebaseUser curUser;
  RecyclerView recyclerView;
  private WishlistAdapter adapter;
  private TextView tvGreet;

  List<Wishlist> wishlistList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wishlist);

    recyclerView = findViewById(R.id.rvWishlist);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    mAuth = FirebaseAuth.getInstance();
    curUser = mAuth.getCurrentUser();
    databaseReference = FirebaseDatabase.getInstance().getReference("wishlist").child(curUser.getUid());

    adapter = new WishlistAdapter(this, wishlistList);
    recyclerView.setAdapter(adapter);

    tvGreet = findViewById(R.id.tvGreet);
    tvGreet.setText(curUser.getEmail() + " Wishlist");



    fetchDataWislist();
  }

  @Override
  protected void onResume() {
    super.onResume();
    fetchDataWislist();
  }

  private void fetchDataWislist() {
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        wishlistList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
          Wishlist wishlist = snapshot.getValue(Wishlist.class);
          if (wishlist != null) {
            wishlist.setKey(snapshot.getKey());
            wishlistList.add(wishlist);
          }
        }
        adapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(getApplicationContext(), "Failed fetch data", Toast.LENGTH_SHORT).show();
      }
    });
  }
}

class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
  private final Context context;
  private final List<Wishlist> wishlistList;
  private DatabaseReference databaseReference;
  private FirebaseAuth mAuth;
  private static String baseUrl = "https://api.themoviedb.org/3/movie/";
  public static String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmOGU5ZjRjODFiNGVkNDJjMjA5NjM0NTYwMTY3ZTFhOSIsInN1YiI6IjY2MjEyM2I2M2Y0ODMzMDE4Njc0MTQyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WVRXwZs6rRB8Wht30hpF-UVxsdanmbzbal6XzhuNU7U";

  public WishlistAdapter(Context context, List<Wishlist> wishlistList) {
    this.context = context;
    this.wishlistList = wishlistList;
    mAuth = FirebaseAuth.getInstance();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View vh = LayoutInflater.from(this.context).inflate(R.layout.row_wishlist, parent, false);
    return new ViewHolder(vh);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Wishlist w = wishlistList.get(position);

    String url = baseUrl + w.getMovieId();
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer " + token)
            .build();

    new Thread(() -> {
      try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful()) {
          String res = response.body().string();
          JSONObject jsonObject = new JSONObject(res);
          String title = jsonObject.getString("title");
          String release = jsonObject.getString("release_date");
          String pathImage = jsonObject.getString("backdrop_path");
          holder.itemView.post(() -> {
            holder.tvTitle.setText(title);
            holder.tvReleaseDate.setText(release);
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + pathImage).into(holder.imgPoster);
          });
        }
      } catch (IOException | JSONException e) {
        e.printStackTrace();
      }
    }).start();

    holder.btDelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        deleteWishlist(holder.getAdapterPosition());
      }
    });
  }

  private void deleteWishlist(int adapterPosition) {
    if (adapterPosition < 0 || adapterPosition >= wishlistList.size()) {
      Toast.makeText(context, "Invalid position", Toast.LENGTH_SHORT).show();
      return;
    }

    Wishlist wishlist = wishlistList.get(adapterPosition);
    FirebaseUser user = mAuth.getCurrentUser();

    if (user == null) {
      Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show();
      return;
    }

    databaseReference = FirebaseDatabase.getInstance().getReference("wishlist")
            .child(user.getUid()).child(wishlist.getKey());

    databaseReference.removeValue().addOnSuccessListener(aVoid -> {
      if (adapterPosition < wishlistList.size()) {
        wishlistList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, wishlistList.size());
        Toast.makeText(context, "Wishlist deleted successfully", Toast.LENGTH_SHORT).show();
      }
    }).addOnFailureListener(e -> {
      Toast.makeText(context, "Failed to delete wishlist", Toast.LENGTH_SHORT).show();
    });
  }

  @Override
  public int getItemCount() {
    return wishlistList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvReleaseDate;
    Button btDelete;
    ImageView imgPoster;
    CardView cardrv;

    public ViewHolder(View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvReleaseDate = itemView.findViewById(R.id.tvReleasedate);
      btDelete = itemView.findViewById(R.id.btDelete);
      imgPoster = itemView.findViewById(R.id.imgPoster);
      cardrv = itemView.findViewById(R.id.cardRv);
    }
  }
}