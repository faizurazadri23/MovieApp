package com.faizurazadri.movieapp.ui.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.databinding.ActivityDetailMovieBinding;

public class DetailMovieActivity extends AppCompatActivity {

    private ActivityDetailMovieBinding detailMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailMovieBinding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        setContentView(detailMovieBinding.getRoot());
    }
}