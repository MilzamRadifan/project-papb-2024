package com.example.tapetrove;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Film> films;
    private final Context context;

    public FilmAdapter(Context context, List<Film> films){
        this.context = context;
        this.films = films;
    }

    public class VH extends RecyclerView.ViewHolder {
        private final TextView tvNamaFilm;
        private final TextView tvGenre;
        private final ImageView ivfilm;
        private final Button btDetail;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvNamaFilm = itemView.findViewById(R.id.tvNamaFilm);
            this.tvGenre = itemView.findViewById(R.id.tvGenre);
            this.ivfilm = itemView.findViewById(R.id.ivfilm);
            this.btDetail = itemView.findViewById(R.id.btDetail);

            this.btDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Film film = films.get(position);
                        PopupHelper.showPopup(context, v, film);
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    //digunakan untuk membuat 1 baris layout
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vh = LayoutInflater.from(this.context)
                .inflate(R.layout.row_film, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Film f = this.films.get(position);
        VH vh = (VH) holder;
        vh.tvNamaFilm.setText(f.namaFilm.toString());
        vh.tvGenre.setText(f.genre.toString());
        vh.ivfilm.setImageBitmap(f.posterfilm);
    }

    @Override
    public int getItemCount() {
        return this.films.size();
    }
}
