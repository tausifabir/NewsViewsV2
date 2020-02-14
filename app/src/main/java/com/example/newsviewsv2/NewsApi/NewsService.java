package com.example.newsviewsv2.NewsApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

   @GET("v2/everything?q=bitcoin&from=2020-01-14&sortBy=publishedAt&apiKey=1719a60abc8047b18d9f22b5342a7fe5")
    Call<NewsResponse> getNewsResponse();
}
