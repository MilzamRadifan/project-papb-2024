package com.example.tapetrove;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PosterAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final Context context;
    private final List<Film> films;
    public PosterAdapter(Context context, List<Film> films){
        this.context = context;
        this.films = films;
    }
    public class VH1 extends RecyclerView.ViewHolder{

        private final ImageView ivPoster;

        public VH1(@NonNull View itemView) {
            super(itemView);
            this.ivPoster = itemView.findViewById(R.id.ivPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Film film = films.get(position);

                    if (film.upcoming) {
                        // Menampilkan toast
                        Toast.makeText(context, film.name + " belum tersedia", Toast.LENGTH_SHORT).show();
                    } else {
                        // Memulai aktivitas baru dengan Intent
                        Intent intent = new Intent(context, PeminjamanActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh1 = LayoutInflater.from(this.context)
                .inflate(R.layout.row_poster,parent,false);
        return new PosterAdapter.VH1(vh1);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder, int position) {
        Film f = this.films.get(position);
        PosterAdapter.VH1 vh1 = (PosterAdapter.VH1) holder;
        vh1.ivPoster.setImageBitmap(f.poster);
    }

    @Override
    public int getItemCount() {
        return this.films.size();
    }
}
