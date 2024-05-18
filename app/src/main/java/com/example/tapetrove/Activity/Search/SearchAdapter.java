package com.example.tapetrove.Activity.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final Context context;
  private final List<ApiResponse.Movie> movieList;

  public SearchAdapter (Context context, List<ApiResponse.Movie> movieList) {
    this.context = context;
    this.movieList = movieList != null ? movieList : new ArrayList<>();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvTitle, tvReleasedate;
    ImageView imgPoster;
    CardView cardRv;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvReleasedate = itemView.findViewById(R.id.tvReleasedate);
      imgPoster = itemView.findViewById(R.id.imgPoster);
      cardRv = itemView.findViewById(R.id.cardRv);
    }

  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View vh  = LayoutInflater.from(this.context).inflate(R.layout.row_movie, parent, false);
    return new ViewHolder(vh);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ApiResponse.Movie m = this.movieList.get(position);
    ViewHolder vh = (ViewHolder) holder;

    vh.tvTitle.setText(m.getTitle().toString());

    if (m.getRelease_date_year() != null) {
      vh.tvReleasedate.setText(m.getRelease_date_year().toString());
    } else {
      vh.tvReleasedate.setText("N/A");
    }

    Glide.with(context).
            load("https://image.tmdb.org/t/p/w500"+m.getPoster_path()).into(vh.imgPoster);
    vh.cardRv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(holder.itemView.getContext(), "You clicked " + m.getTitle(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return movieList.size();
  }
}
