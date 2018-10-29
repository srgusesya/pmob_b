package net.husnilkamil.layartancap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.text_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Jika tombol refresh diklik
        if(item.getItemId() == R.id.menu_refresh){
            Toast.makeText(this, "Refreshing data", Toast.LENGTH_SHORT).show();

            getNowPlayingMovies();

        }
        return super.onOptionsItemSelected(item);
    }


    //Method untuk mengambil data ke internet
    private void getNowPlayingMovies() {

        GetNowPlayingTask task = new GetNowPlayingTask();
        task.execute();

    }

    class GetNowPlayingTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            textResult.setText(s);
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String webUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=cf51c94af17e64e7a0b2fdf107a3dbc6";
            HttpURLConnection urlConnection;

            try {

                //Konek ke server
                URL url = new URL(webUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Ambil data dari server
                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null){
                    return null;
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                //Konvert data ke bentuk String
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                String result = buffer.toString();

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
    }
}
