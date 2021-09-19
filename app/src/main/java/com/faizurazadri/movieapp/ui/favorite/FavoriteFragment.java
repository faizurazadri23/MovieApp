package com.faizurazadri.movieapp.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.faizurazadri.movieapp.adapter.SectionPagerAdapter;
import com.faizurazadri.movieapp.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (((AppCompatActivity)getActivity()).getSupportActionBar()!=null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0);
        }

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        binding.viewPagerFavorite.setAdapter(sectionPagerAdapter);
        binding.tabFavorite.setupWithViewPager(binding.viewPagerFavorite);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}