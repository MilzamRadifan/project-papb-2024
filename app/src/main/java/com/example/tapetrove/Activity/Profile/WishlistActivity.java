package com.example.tapetrove.Activity.Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapetrove.Database.Wishlist;
import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
  private FirebaseAuth mAuth;
  private DatabaseReference databaseReference;
  private FirebaseUser curUser;
  RecyclerView recyclerView;

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




  }
}

class WishlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final Context context;
  private final List<Wishlist> wishlistList;
  private DatabaseReference databaseReference;
  private FirebaseAuth mAuth;

  public WishlistAdapter (Context context, List<Wishlist> wishlistList) {
    this.context = context;
    this.wishlistList = wishlistList;
    mAuth = FirebaseAuth.getInstance();
  }


  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View vh = LayoutInflater.from(this.context).inflate(R.layout.row_wishlist, parent, false);
    return new VievHolder(vh);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Wishlist w = wishlistList.get(position);
    VievHolder vh = (VievHolder) holder;
//    kene urung dadi
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class VievHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvReleaseDate;
    Button btDelete;
    ImageView imgPoster;
    CardView cardrv;
    public VievHolder(View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvReleaseDate = itemView.findViewById(R.id.tvReleasedate);
      btDelete = itemView.findViewById(R.id.btDelete);
      imgPoster = itemView.findViewById(R.id.imgPoster);
      cardrv = itemView.findViewById(R.id.cardRv);
    }
  }

}