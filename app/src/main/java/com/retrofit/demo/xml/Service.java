package com.retrofit.demo.xml;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/portal.php?mod=rss&catid=")
    Call<Channel> getChannel();
}
