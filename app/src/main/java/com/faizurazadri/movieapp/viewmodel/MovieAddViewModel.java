package com.faizurazadri.movieapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.faizurazadri.movieapp.repository.MovieRepository;
import com.faizurazadri.movieapp.room.Movie;

public class MovieAddViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieAddViewModel(Application application) {
        movieRepository = new MovieRepository(application);
    }

    public void insert(Movie movie){
        movieRepository.insert(movie);
    }

    public void update(Movie movie){
        movieRepository.update(movie);
    }

    public void delete(Movie movie){
        movieRepository.delete(movie);
    }
}
