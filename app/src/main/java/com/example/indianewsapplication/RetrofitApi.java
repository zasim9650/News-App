package com.example.indianewsapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitApi {

    @GET
    Call<NewsModal> getAllNews(@Url String url);
    @GET
    Call<NewsModal> getNewsByCategory(@Url String url);
}
