package com.faizurazadri.movieapp.ui.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.faizurazadri.movieapp.BuildConfig;
import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.databinding.ActivityDetailBinding;
import com.faizurazadri.movieapp.model.Result;
import com.faizurazadri.movieapp.room.Movie;
import com.faizurazadri.movieapp.viewmodel.MovieAddViewModel;
import com.faizurazadri.movieapp.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding detailMovieBinding;
    private Result result;
    private Movie movie;
    private MovieAddViewModel movieAddViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailMovieBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(detailMovieBinding.getRoot());

        result = getIntent().getParcelableExtra("DATA");

        movieAddViewModel = obtainViewModel(DetailActivity.this);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Detail " + result.getTitle());
        }

        Glide.with(this).load(BuildConfig.SERVER_IMG + result.getBackdropPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        detailMovieBinding.progressImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        detailMovieBinding.progressImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .error(getResources().getDrawable(R.drawable.ic_movie)).into(detailMovieBinding.backdropPath);
        detailMovieBinding.originalTitle.setText(result.getOriginalTitle() + " : " + result.getOriginalLanguage());
        detailMovieBinding.overview.setText(result.getOverview());
        detailMovieBinding.popularity.setText("Popularity : " + result.getPopularity());
    }

    @NonNull
    private static MovieAddViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(MovieAddViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tab_favorite){
            movie = new Movie();
            movie.setBackdrop_path(result.getBackdropPath());
            movie.setOriginal_language(result.getOriginalLanguage());
            movie.setOriginal_title(result.getOriginalTitle());
            movie.setOverview(result.getOverview());
            movie.setPopularity(result.getPopularity());
            movie.setTitle(result.getTitle());
            movie.setPoster_path(result.getPosterPath());
            movie.setRelease_date(result.getReleaseDate());
            movie.setVoteAverage(result.getVoteAverage());
            movie.setVoteCount(result.getVoteCount());
            movieAddViewModel.insert(movie);
            Snackbar.make(detailMovieBinding.getRoot(), getResources().getString(R.string.message_success), Snackbar.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}