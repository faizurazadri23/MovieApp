package com.faizurazadri.movieapp.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.faizurazadri.movieapp.adapter.AdapterMovieFavorite;
import com.faizurazadri.movieapp.databinding.FragmentFavoriteMovieBinding;
import com.faizurazadri.movieapp.viewmodel.MainViewModel;
import com.faizurazadri.movieapp.viewmodel.ViewModelFactory;


public class FavoriteMovieFragment extends Fragment {

    FragmentFavoriteMovieBinding fragmentFavoriteBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentFavoriteBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false);
        getDataFavoriteMovie();
        return fragmentFavoriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getDataFavoriteMovie();

    }

    private void getDataFavoriteMovie() {
        fragmentFavoriteBinding.progressMovie.setVisibility(View.VISIBLE);
        MainViewModel mainViewModel = obtainViewModel(getActivity());
        mainViewModel.getAllMovie().observe(getActivity(), movies -> {
            fragmentFavoriteBinding.progressMovie.setVisibility(View.GONE);
            AdapterMovieFavorite adapterMovie = new AdapterMovieFavorite(movies, getActivity());

            adapterMovie.notifyDataSetChanged();
            fragmentFavoriteBinding.rvFavoriteMovie.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            fragmentFavoriteBinding.rvFavoriteMovie.setHasFixedSize(true);
            fragmentFavoriteBinding.rvFavoriteMovie.setAdapter(adapterMovie);
        });
    }

    private MainViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentFavoriteBinding = null;
    }
}