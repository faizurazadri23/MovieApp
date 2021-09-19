package com.faizurazadri.movieapp.api;

import com.faizurazadri.movieapp.response.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/now_playing")
    Call<Movie> getNowPlaying(@Query("api_key") String api_key, @Query("language") String language);
}
