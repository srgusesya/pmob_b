package net.husnilkamil.layartancap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.husnilkamil.layartancap.model.MovieItem;

public class DetailActivity extends AppCompatActivity {

    TextView textReleaseDate;
    TextView textRating;
    TextView textOverview;
    MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textReleaseDate = findViewById(R.id.text_release_date);
        textOverview = findViewById(R.id.text_overview);
        textRating = findViewById(R.id.text_rating);

        Intent detailIntent = getIntent();
        if(null != detailIntent) {
            movieItem = detailIntent.getParcelableExtra("key_movie_parcelable");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(movieItem != null) {
            getSupportActionBar().setTitle(movieItem.getTitle());
            textRating.setText(String.valueOf(movieItem.getVote_average()));
            textReleaseDate.setText(movieItem.getRelease_date());
            textOverview.setText(movieItem.getOverview());

        }
    }
}
