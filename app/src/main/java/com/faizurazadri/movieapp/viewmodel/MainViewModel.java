package com.faizurazadri.movieapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.faizurazadri.movieapp.repository.MovieRepository;
import com.faizurazadri.movieapp.room.Movie;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MainViewModel(Application application){
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovie(){
        return movieRepository.getAllMovie();
    }
}
