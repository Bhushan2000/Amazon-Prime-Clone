package com.example.amazonprimeclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MovieDetails extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ImageView ivPoster;
    TextView tvDetails, tvTitle;

    private String imageBaseUrl = "https://image.tmdb.org/t/p/w1280/";
    String id;
    TextView tvIMDB, tvYear, tvLanguages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        if (intent != null) {
            id = intent.getStringExtra("movie_id");
        }


        tvIMDB = findViewById(R.id.tvIMDB);
        tvYear = findViewById(R.id.tvYear);
        tvLanguages = findViewById(R.id.tvlanguages);


        relativeLayout = findViewById(R.id.relativeLayout);

        ivPoster = findViewById(R.id.ivPoster);

        tvDetails = findViewById(R.id.tvDetails);

        tvTitle = findViewById(R.id.tvTitle);


//        Ex; of GET Request
//        AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}")
////                .addPathParameter("pageNumber", "0")
////                .addQueryParameter("limit", "3")
////                .addHeaders("token", "1234")
////                .setTag("test")
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        relativeLayout.setVisibility(View.GONE);
//                        tvDetails.setText(response.toString());
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//
//                        relativeLayout.setVisibility(View.GONE);
//                        tvDetails.setText(error.toString());
//                    }
//                });


        setMovieData();

        // setCreditData();


    }


    // movie details
    private void setMovieData() {
        // Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/{movie_id}")
                .addPathParameter("movie_id", "464052")
                .addQueryParameter("api_key", "d133f391f5157ae9db79e26771ec314b")
                .addQueryParameter("language", "en-US")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        relativeLayout.setVisibility(View.GONE);


                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());


                            String url = imageBaseUrl + jsonObject.getString("backdrop_path");


                            Glide.with(MovieDetails.this)
                                    .load(url).placeholder(R.drawable.image_fallback)
                                    .fallback(R.drawable.image_fallback)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivPoster);

                            tvDetails.setText(jsonObject.getString("overview"));
                            tvTitle.setText(jsonObject.getString("title"));
                            tvIMDB.setText("IMDB ID:    " + jsonObject.getString("imdb_id"));
                            tvYear.setText("Release date:   " + jsonObject.getString("release_date"));

                            JSONArray jsonArray = new JSONArray(jsonObject.getString("spoken_languages"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                tvLanguages.setText("Languages: " + jsonObject1.getString("name"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError error) {
                        relativeLayout.setVisibility(View.GONE);

                        Toast.makeText(MovieDetails.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    // creditData
    private void setCreditData() {
        AndroidNetworking.get("https://api.themoviedb.org/3/credit/{credit_id}")
                .addPathParameter("credit_id", "5d61847554f6eb00149ba7b1")
                .addQueryParameter("api_key", "d133f391f5157ae9db79e26771ec314b")
                .addQueryParameter("language", "en-US")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        relativeLayout.setVisibility(View.GONE);
                        tvDetails.setText(response.toString());
                    }

                    @Override
                    public void onError(ANError error) {
                        relativeLayout.setVisibility(View.GONE);
                        tvDetails.setText(error.toString());
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}