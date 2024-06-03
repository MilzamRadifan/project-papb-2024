package com.example.tapetrove.Activity.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.R;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {

    private final Context context;
    private final List<ApiResponse.Movie> movies;

    public MovieAdapter(Context context, List<ApiResponse.Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView iv1;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.iv1 = itemView.findViewById(R.id.ivPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Mendapatkan posisi item yang diklik
                    int position = getAdapterPosition();
                    // Mendapatkan objek Film berdasarkan posisi
//                    Film film = films.get(position);
                    ApiResponse.Movie movie= movies.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film", movie);
                    // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
                    ((MainActivity) context).replaceFragmentWithBundle(new PeminjamanFragment(), bundle);
                }
            });
        }
    }

    @NonNull
    @Override
    public VH
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_poster, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ApiResponse.Movie todo = movies.get(position);
        // Memuat gambar menggunakan Glide
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + todo.getPoster_path())
                .into(holder.iv1);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}