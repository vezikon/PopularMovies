package com.vezikon.popularmovies.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vezikon.popularmovies.R;
import com.vezikon.popularmovies.movies.MoviesFragment;
import com.vezikon.popularmovies.data.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vezikon on 7/11/15.
 */
public class MoviesAdapter extends BaseAdapter {

    Context context;
    ArrayList<Movie> movies;
    MoviesFragment.OnMoviesFragmentListener mListener;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;


        try {
            mListener = (MoviesFragment.OnMoviesFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnMoviesFragmentListener");
        }
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Movie movie = (Movie) getItem(position);

        if (movie.getPoster_path() != null) {
            String path = "http://image.tmdb.org/t/p/w342";

            Picasso.with(context).load(path + movie.getPoster_path())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.movieImageView);
        }

        return convertView;
    }

    public void replaceData(ArrayList<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'list_item_movie.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.list_item_image)
        ImageView movieImageView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
