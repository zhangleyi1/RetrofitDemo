package com.retrofit.demo.json;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//这里采用get和post的两种方法，结果是一致的。
public interface MovieService {
    @FormUrlEncoded
    @POST("top250")
    Call<String> getTopMoviePostScalars(@Field("start") int start, @Field("count") int count);

    @GET("top250")
    Call<String> getTopMovieGetScalars(@Query("start") int start, @Query("count") int count);

    @FormUrlEncoded
    @POST("top250")
    Call<Movies> getTopMoviePostGson(@Field("start") int start, @Field("count") int count);

    @GET("top250")
    Call<Movies> getTopMovieGetGson(@Query("start") int start, @Query("count") int count);
}
