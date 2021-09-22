package com.faizurazadri.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faizurazadri.movieapp.BuildConfig;
import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.databinding.ItemMovieBinding;
import com.faizurazadri.movieapp.model.Result;
import com.faizurazadri.movieapp.room.Movie;
import com.faizurazadri.movieapp.ui.detail.DetailActivity;

import java.util.List;

public class AdapterMovieFavorite extends RecyclerView.Adapter<AdapterMovieFavorite.ViewMovie> {

    private List<Movie> resultList;
    private Context context;

    public AdapterMovieFavorite(List<Movie> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewMovie(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMovie holder, int position) {
        Movie result = resultList.get(position);
        holder.setItemMovieBinding(result);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewMovie extends RecyclerView.ViewHolder {

        final ItemMovieBinding itemMovieBinding;

        public ViewMovie(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());

            this.itemMovieBinding = itemMovieBinding;
        }

        void setItemMovieBinding(Movie result){
            Glide.with(context).load(BuildConfig.SERVER_IMG + result.getPoster_path()).into(itemMovieBinding.posterPath);
            itemMovieBinding.title.setText(result.getTitle());
            itemMovieBinding.releaseDate.setText(result.getRelease_date());

            itemView.setOnClickListener(view -> {
                Intent toDetail = new Intent(context.getApplicationContext(), DetailActivity.class);
                toDetail.putExtra("DATA", result);
                context.startActivity(toDetail);
            });
        }
    }
}
