package com.faizurazadri.movieapp.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.faizurazadri.movieapp.BuildConfig;
import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.adapter.AdapterMovie;
import com.faizurazadri.movieapp.api.ApiConfig;
import com.faizurazadri.movieapp.databinding.FragmentMovieBinding;
import com.faizurazadri.movieapp.response.Movie;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMovieNowPlating();
    }

    private void getMovieNowPlating(){
        binding.progressMovie.setVisibility(View.VISIBLE);
        Call<Movie> movieCall = ApiConfig.getApiService().getNowPlaying(BuildConfig.API_KEY, "en-US");
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    AdapterMovie adapterMovie = new AdapterMovie(response.body().getResults(), getActivity());
                    adapterMovie.notifyDataSetChanged();
                    binding.rvMovie.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    binding.rvMovie.setHasFixedSize(true);
                    binding.rvMovie.setAdapter(adapterMovie);
                }

                binding.progressMovie.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.connection_internet), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}