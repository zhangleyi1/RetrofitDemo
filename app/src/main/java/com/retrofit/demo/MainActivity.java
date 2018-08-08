package com.retrofit.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.retrofit.demo.json.MovieService;
import com.retrofit.demo.json.Movies;
import com.retrofit.demo.xml.Channel;
import com.retrofit.demo.xml.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        getScalarsMethod();
//        getXmlMethod();
    }


    //本例是获取服务端的数据为String原始数据，需要调用Gson来解析
    public void getScalarsMethod() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())  //获取字符串，需要自己解析
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
//        Call<String> call = movieService.getTopMoviePostScalars(0, 1); //Post
        Call<String> call = movieService.getTopMovieGetScalars(0, 1); //Get
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                tvRx.setText(response.body().toString());
                Log.d("zly", "zly --> response:" + response.body() + " count:" + response.body().length());
                Gson gson = new Gson();
                Movies movies = gson.fromJson(response.body(), Movies.class);
                Log.d("zly", "zly --> mList.size:" + movies.getCount());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.d("zly", "zl;y --> onFailure throwable:" + throwable.getMessage());
            }
        });
    }

    private void getGsonMethod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<Movies> movie = movieService.getTopMovieGetGson(0, 1);
        movie.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                Movies sMovices = response.body();
                assert sMovices != null;
                Log.d("zly", "zly --> getTitle:" + sMovices.getCount());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d("zly", "zly --> t:" + t.getMessage());
            }
        });
    }

    private void getXmlMethod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.inexus.co")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        Service sService = retrofit.create(Service.class);
        Call<Channel> sCall = sService.getChannel();
        sCall.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {
                Channel sChannel = response.body();
                Log.d("zly", "zly --> getLink" + sChannel.getLink());
            }

            @Override
            public void onFailure(Call<Channel> call, Throwable t) {
                Log.d("zly", "zly --> t:" + t.getMessage());
            }
        });
    }
}
