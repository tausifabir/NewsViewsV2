package com.example.newsviewsv2.NewsApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

   @GET("v2/everything?domains=wsj.com&apiKey=3975ff42898248e6a533247d5af693cb")
    Call<NewsResponse> getNewsResponse();
}
