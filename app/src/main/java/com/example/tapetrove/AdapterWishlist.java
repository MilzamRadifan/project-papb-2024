package com.example.tapetrove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterWishlist extends RecyclerView.Adapter<AdapterWishlist.HolderData> {
    List<FilmWishlistEntity> listData;

    public AdapterWishlist(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    LayoutInflater inflater;

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.wishlist_list, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        FilmWishlistEntity film = listData.get(position);
        holder.tvJudul.setText(film.getJudul());
        holder.tvDurasi.setText(film.getDurasi());
        holder.tvTanggalRilis.setText(film.getTanggalRilis());
        holder.tvGenre.setText(film.getGenre());

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WishlistActivity) inflater.getContext()).deleteFilm(film);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listData == null) {
            return 0;
        }
        return listData.size();
    }

    public void setFilms(List<FilmWishlistEntity> films) {
        this.listData = films;
        notifyDataSetChanged();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvJudul;
        TextView tvDurasi;
        TextView tvTanggalRilis;
        TextView tvGenre;
        Button btDelete;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvDurasi = itemView.findViewById(R.id.tvDurasi);
            tvTanggalRilis = itemView.findViewById(R.id.tvTanggalRilis);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            btDelete = itemView.findViewById(R.id.btDelete);
        }
    }
}

