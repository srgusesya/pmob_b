package net.husnilkamil.layartancap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import net.husnilkamil.layartancap.adapter.MovieListAdapter;
import net.husnilkamil.layartancap.model.MovieItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MovieListAdapter.OnMovieItemClicked{

    private static final String TAG = "MainActivity";
    ArrayList<MovieItem> daftarFilm = new ArrayList<>();
    RecyclerView rvMovieList;
    MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDummyData();

        Log.d(TAG, String.valueOf(daftarFilm.size()));

        movieListAdapter = new MovieListAdapter();
        movieListAdapter.setDaftarFilm(daftarFilm);
        movieListAdapter.setClickHandler(this);

        rvMovieList = findViewById(R.id.rv_movie_list);
        rvMovieList.setAdapter(movieListAdapter);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Jika tombol refresh diklik
        if (item.getItemId() == R.id.menu_refresh) {
            Toast.makeText(this, "Refreshing data", Toast.LENGTH_SHORT).show();

            getNowPlayingMovies();

        }
        return super.onOptionsItemSelected(item);
    }


    //Method untuk mengambil data ke internet
    private void getNowPlayingMovies() {

        loadDummyData();

    }

    private void loadDummyData() {

        daftarFilm.add(new MovieItem(
                123,
                "Venom",
                "ini film kerem",
                6.8,
                "2018-09-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));

        daftarFilm.add(new MovieItem(
                124,
                "Anak yang Tertukar",
                "Adaptasi sinetron",
                4.0,
                "2017-09-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                125,
                "Putri yang Tertukar",
                "Tukar-tukaran anak",
                5.0,
                "2018-05-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                126,
                "What's wrong with Secretary Kim",
                "Drama korea",
                7.2,
                "2018-07-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                127,
                "Tenggelamnya Kapal Vanderwick",
                "Titanicnya Indonesia",
                6.8,
                "2008-09-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                123,
                "Venom",
                "ini film kerem",
                6.8,
                "2018-09-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));

        daftarFilm.add(new MovieItem(
                124,
                "Anak yang Tertukar",
                "Adaptasi sinetron",
                4.0,
                "2017-09-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                125,
                "Putri yang Tertukar",
                "Tukar-tukaran anak",
                5.0,
                "2018-05-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                126,
                "What's wrong with Secretary Kim",
                "Drama korea",
                7.2,
                "2018-07-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        daftarFilm.add(new MovieItem(
                127,
                "Tenggelamnya Kapal Vanderwick",
                "Titanicnya Indonesia",
                6.8,
                "2008-09-10",
                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
    }


    @Override
    public void movieItemClicked() {
        Intent detailMovieIntent = new Intent(this, DetailActivity.class);
        startActivity(detailMovieIntent);
    }
}
