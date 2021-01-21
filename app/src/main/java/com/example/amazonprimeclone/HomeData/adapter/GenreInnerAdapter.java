package com.example.amazonprimeclone.HomeData.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amazonprimeclone.HomeData.model.Movie;
import com.example.amazonprimeclone.R;

import java.util.List;

public class GenreInnerAdapter extends RecyclerView.Adapter<GenreInnerAdapter.GenreInnerVH> {

    private Context context;
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;
    private String imageBaseUrl = "https://image.tmdb.org/t/p/w500/";

    public GenreInnerAdapter(Context context, List<Movie> movieList ) {
        this.context = context;
        this.movieList = movieList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public GenreInnerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.genere_inner_unit, parent, false);
        return new GenreInnerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreInnerVH holder, int position) {
        String bannerImageUrl = imageBaseUrl + movieList.get(position).getPosterPath();

        Glide.with(context)
                .load(bannerImageUrl).placeholder(R.drawable.image_icon)
                .fallback(R.drawable.genre_image_fallback)
                .error(R.drawable.genre_image_fallback)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieBanner);

        holder.movieTitle.setText(movieList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {

        return movieList.size();
    }

    class GenreInnerVH extends RecyclerView.ViewHolder{
        private ImageView movieBanner;
        private TextView movieTitle;

        public GenreInnerVH(@NonNull View itemView) {
            super(itemView);
            movieBanner = itemView.findViewById(R.id.genre_movie_image);
            movieTitle = itemView.findViewById(R.id.genre_movie_title);

        }
    }
}
