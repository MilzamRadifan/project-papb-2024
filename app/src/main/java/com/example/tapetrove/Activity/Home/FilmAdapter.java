package com.example.tapetrove.Activity.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tapetrove.Activity.Home.MainActivity;
import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.R;

import java.io.Serializable;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<ApiResponse.Movie> movies;

    public FilmAdapter(Context context, List<ApiResponse.Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView ivTopPoster;

        private final TextView tvTopGenre;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.ivTopPoster = itemView.findViewById(R.id.ivTopPoster);
            this.tvTopGenre = itemView.findViewById(R.id.tvTopGenre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Mendapatkan posisi item yang diklik
                    int position = getAdapterPosition();
                    // Mendapatkan objek Film berdasarkan posisi
//                    Film film = films.get(position);
                    ApiResponse.Movie movie = movies.get(position);
                    // Memulai aktivitas baru dengan Intent

//          Intent intent = new Intent(context, PeminjamanActivity.class);
//          intent.putExtra("film", (Serializable) movie);
//          context.startActivity(intent);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film", movie);
                    ((MainActivity) context).replaceFragmentWithBundle(new PeminjamanFragment(), bundle);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(this.context).inflate(R.layout.row_top_poster, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder, int position) {
//        Film f = this.films.get(position);
        ApiResponse.Movie movie = this.movies.get(position);
        VH vh = (VH) holder;
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path())
                .into(vh.ivTopPoster);
//        vh.ivTopPoster.setImageBitmap(f.topPoster);
        vh.tvTopGenre.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }
}
