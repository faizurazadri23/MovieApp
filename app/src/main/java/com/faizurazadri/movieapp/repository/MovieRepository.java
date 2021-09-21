package com.faizurazadri.movieapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.faizurazadri.movieapp.room.Movie;
import com.faizurazadri.movieapp.room.MovieDatabase;
import com.faizurazadri.movieapp.room.MoviesDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieRepository {

    private MoviesDao moviesDao;
    private ExecutorService executorService;

    public MovieRepository(Application application){
        executorService = Executors.newSingleThreadExecutor();
        MovieDatabase database = MovieDatabase.getDatabase(application);
        moviesDao = database.moviesDao();
    }

    public LiveData<List<Movie>> getAllMovie(){
        return moviesDao.getAllMovie();
    }

    public void insert(final Movie movie){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                moviesDao.insert(movie);
            }
        });
    }

    public void delete(final Movie movie){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                moviesDao.delete(movie);
            }
        });
    }

    public void update(final Movie movie){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                moviesDao.update(movie);
            }
        });
    }
}
