package net.husnilkamil.layartancap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.husnilkamil.layartancap.adapter.MovieListAdapter;
import net.husnilkamil.layartancap.model.MovieItem;
import net.husnilkamil.layartancap.model.MovieList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Log.d(TAG, String.valueOf(daftarFilm.size()));

        movieListAdapter = new MovieListAdapter();
        movieListAdapter.setDaftarFilm(daftarFilm);
        movieListAdapter.setClickHandler(this);

        rvMovieList = findViewById(R.id.rv_movie_list);
        rvMovieList.setAdapter(movieListAdapter);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));


        getNowPlayingMovies();

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

        String API_BASE_URL = "https://api.themoviedb.org";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbClient client =  retrofit.create(TmdbClient.class);

        Call<MovieList> call = client.getMovies("cf51c94af17e64e7a0b2fdf107a3dbc6");

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                MovieList movieList = response.body();
                List<MovieItem> listMovieItem = movieList.results;
                movieListAdapter.setDaftarFilm(listMovieItem);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void movieItemClicked(MovieItem movieItem) {

        Toast.makeText(
                this,
                "Item yang diklik adalah : " + movieItem.getTitle(),
                Toast.LENGTH_SHORT).show();

        Intent detailMovieIntent = new Intent(this, DetailActivity.class);
        detailMovieIntent.putExtra("key_movie_parcelable", movieItem);
        startActivity(detailMovieIntent);
    }
}
