package com.example.tapetrove.Activity.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tapetrove.Api.Genre;
import com.example.tapetrove.Database.Peminjaman;
import com.example.tapetrove.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
  private static Context context = null;
  private static List<Peminjaman> peminjamanList = null;
  private static String baseUrl = "https://api.themoviedb.org/3/movie/";
  public static String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmOGU5ZjRjODFiNGVkNDJjMjA5NjM0NTYwMTY3ZTFhOSIsInN1YiI6IjY2MjEyM2I2M2Y0ODMzMDE4Njc0MTQyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WVRXwZs6rRB8Wht30hpF-UVxsdanmbzbal6XzhuNU7U";


  public HistoryAdapter(Context context, List<Peminjaman> peminjamanList) {
    this.context = context;
    this.peminjamanList = peminjamanList;
  }
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivfilm;
    private TextView tvNamaFilm,tvGenre;
    private Button btDetail;
    private CardView cardRv;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      this.ivfilm = itemView.findViewById(R.id.ivfilm);
      this.tvNamaFilm=itemView.findViewById(R.id.tvNamaFilm);
      this.tvGenre=itemView.findViewById(R.id.tvHistoryGenre);
      this.btDetail=itemView.findViewById(R.id.btDetail);
      this.cardRv = itemView.findViewById(R.id.cardRv);
      btDetail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int position = getAdapterPosition();
          if (position != RecyclerView.NO_POSITION) {
            Peminjaman peminjaman =peminjamanList.get(position);
            PopupHelper.showPopup(context, v, peminjaman);
          }
        }
      });
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View vh = LayoutInflater.from(this.context).inflate(R.layout.row_history, parent, false);
    return new ViewHolder(vh);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Peminjaman p = peminjamanList.get(position);
    String url = baseUrl + p.getId_movie();
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
          String pathImage = jsonObject.getString("poster_path");
          JSONArray genresArray = jsonObject.getJSONArray("genres");
          List<Genre.GenresBean> listGenre = new ArrayList<>();
          for (int i = 0; i < genresArray.length(); i++) {
            JSONObject genreObject = genresArray.getJSONObject(i);
            Genre.GenresBean genre = new Genre.GenresBean();
            genre.setId(genreObject.getInt("id"));
            genre.setName(genreObject.getString("name"));
            listGenre.add(genre);
          }
          String genre= listGenre.get(0).getName();
          for (int i = 1; i < listGenre.size() ; i++) {
            genre = genre + ", " + listGenre.get(i).getName();
          }


          String finalGenre = genre;
          holder.itemView.post(() -> {
            holder.tvNamaFilm.setText(title);
            holder.tvGenre.setText(finalGenre);
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + pathImage).into(holder.ivfilm);
          });
        }
      } catch (IOException | JSONException e) {
        e.printStackTrace();
      }
    }).start();

    holder.cardRv.setOnClickListener(v -> {

    });
  }

  @Override
  public int getItemCount() {
    return this.peminjamanList.size();
  }
}
