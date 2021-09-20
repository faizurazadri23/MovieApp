package com.faizurazadri.movieapp.ui.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.faizurazadri.movieapp.BuildConfig;
import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.databinding.ActivityDetailBinding;
import com.faizurazadri.movieapp.model.Result;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding detailMovieBinding;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailMovieBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(detailMovieBinding.getRoot());

        result = getIntent().getParcelableExtra("DATA");

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
}