package com.faizurazadri.movieapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Update()
    void update (Movie movie);

    @Delete()
    void delete(Movie movie);

    @Query("SELECT * from movie ORDER BY id ASC")
    LiveData<List<Movie>> getAllMovie();

}
