package com.example.newsviewsv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsviewsv2.NewsApi.NewsResponse;
import com.squareup.picasso.Picasso;

public class NewsItem extends AppCompatActivity {

    private TextView authorTV,titleTV,descripTV, publishTV,urlTV,contentTV;
    private ImageView imageViewID;
    NewsResponse newsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        imageViewID = findViewById(R.id.imageurlID);
        authorTV = findViewById(R.id.authorID);
        titleTV = findViewById(R.id.titleID);
        descripTV = findViewById(R.id.descriptionID);
        publishTV = findViewById(R.id.publishDateID);
        urlTV = findViewById(R.id.urlID);
        contentTV = findViewById(R.id.contentID);

        newsResponse = new NewsResponse();


        Intent intent = getIntent();
        String Image = intent.getStringExtra("image");
        String Author = intent.getStringExtra("author");
        String Title = intent.getStringExtra("title");
        String Description = intent.getStringExtra("des");
        String PublishDate = intent.getStringExtra("pub");
        String URL = intent.getStringExtra("url");
        String Content = intent.getStringExtra("content");


        authorTV.setText(Author);
        Picasso.get().load(Image).into(imageViewID);

        titleTV.setText(Title);
        descripTV.setText(Description);
        publishTV.setText(PublishDate);
        urlTV.setText(URL);
        contentTV.setText(Content);

    }
}
