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

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FilmApi> films;
    private Context context;

    public FilmAdapter(Context context, List<FilmApi> films){
        this.context = context;
        this.films = films;
    }

    // Method untuk memperbarui data film
    public void setFilms(List<FilmApi> films) {
        this.films = films;
        notifyDataSetChanged(); // Memberitahu RecyclerView bahwa data telah berubah
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

//            this.btDetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        Film film = films.get(position);
//                        PopupHelper.showPopup(context, v, film);
//                    }
//                }
//            });

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
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        FilmApi.ResultsBean f = this.films.get(position);
        vh.tvNamaFilm.setText(f.getTitle());
        vh.tvGenre.setText("Genre: " + f.getOverview());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + f.getPosterPath())
                .into(vh.ivfilm);

        // Create a Film object from the Film object
        Film film = new Film(f.getPaymentMethod(), f.getHargaSewa(), f.getTanggalRent(), f.getTanggalReturn(), f.getStatus());

        // Show the popup when the user clicks the btDetail
        vh.btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupHelper.showPopup(context, v, film);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.films.size();
    }
}
