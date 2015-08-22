package com.vezikon.popularmovies;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vezikon.popularmovies.data.MoviesContract;
import com.vezikon.popularmovies.fragments.MovieDetailFragment;
import com.vezikon.popularmovies.fragments.MoviesFragment;
import com.vezikon.popularmovies.models.Movie;

import static com.vezikon.popularmovies.fragments.MoviesFragment.*;


public class MainActivity extends AppCompatActivity implements OnMoviesFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment movieFragment = MoviesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, movieFragment)
                    .commit();
        }
    }


    @Override
    public void onMovieSelected(Movie movie) {

        Fragment movieDetailFragment = MovieDetailFragment.newInstance(movie, isFav(movie));

        //check screen status
        if (getResources().getBoolean(R.bool.isMultiPan)) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details, movieDetailFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, movieDetailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * Use this method to check whether the movie is in Favorite database or not
     *
     * @param movie instance of {@link Movie}
     * @return boolean
     */
    private boolean isFav(Movie movie) {

        String selection = MoviesContract.FavMoviesEntry.TABLE_NAME + "."
                + MoviesContract.FavMoviesEntry._ID + " = ? ";

        String[] selectionArgs = {String.valueOf(movie.getId())};

        Cursor cursor = getContentResolver().query(MoviesContract.FavMoviesEntry.CONTENT_URI,
                MoviesFragment.MOVIE_COLUMNS,
                selection,
                selectionArgs,
                null
        );

        return cursor.getCount() > 0;
    }
}
