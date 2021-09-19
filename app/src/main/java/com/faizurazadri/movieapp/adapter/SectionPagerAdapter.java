package com.faizurazadri.movieapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.ui.movie.FavoriteMovieFragment;
import com.faizurazadri.movieapp.ui.tvshow.TvShowFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private Context mcontext;
    private String[] title;

    public SectionPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mcontext = context;
        title = new String[]{context.getResources().getString(R.string.title_movie), context.getResources().getString(R.string.title_tvshow)};
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position){
            case 0:
                fragment = new FavoriteMovieFragment();
                break;

            case 1:
                fragment = new TvShowFragment();
                break;

            default:

                fragment = null;

                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
