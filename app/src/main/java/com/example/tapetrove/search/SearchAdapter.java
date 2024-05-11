package com.example.tapetrove.search;

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
import com.example.tapetrove.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//  retrofit implementation on recycler view
  private final Context context;
  private final List<Movie> movieList;
  private static ClickListener clickListener;
//  private List<Movie.Results> resultsList;

//  private final ArrayList<Poster> posters;
//  private final RecyclerViewInterface rvInterface;

  public SearchAdapter (Context context,
                        List<Movie> movieList
//                        ArrayList<Poster> posters,
//                        RecyclerViewInterface rvInterface
  ) {
    this.context = context;
    this.movieList = movieList;
//    this.posters = posters;
//    this.rvInterface = rvInterface;
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
  public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View vh  = LayoutInflater.from(this.context).inflate(R.layout.row_movie, parent, false);
    return new ViewHolder(vh);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Movie m = this.movieList.get(position);
    ViewHolder vh = (ViewHolder) holder;

    vh.tvTitle.setText(m.getTitle().toString());

    if (m.getRelease_date_year() != null) {
      vh.tvReleasedate.setText(m.getRelease_date_year().toString());
    } else {
      vh.tvReleasedate.setText("N/A"); // Atau tindakan lain sesuai kebutuhan aplikasi Anda
    }

//    vh.tvReleasedate.setText(m.getRelease_date_year().toString());
    Glide.with(context).
            load("https://image.tmdb.org/t/p/w500"+m.getPoster_path()).into(vh.imgPoster);
    vh.cardRv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(vh.itemView.getContext(), "You clicked " + m.getTitle(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return movieList.size();
  }

  public interface ClickListener {
    void onItemClick(int position, View v);
  }
}