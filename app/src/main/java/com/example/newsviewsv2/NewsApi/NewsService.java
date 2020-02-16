package com.example.newsviewsv2.NewsApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

   @GET("v2/everything?q=bitcoin&from=2020-01-16&sortBy=publishedAt&apiKey=bb6f8c3519e74813b3b09d038f8ac9dd")
    Call<NewsResponse> getNewsResponse();
}
