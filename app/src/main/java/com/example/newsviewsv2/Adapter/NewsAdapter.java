package com.example.newsviewsv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsviewsv2.NewsApi.NewsResponse;
import com.example.newsviewsv2.R;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    private NewsResponse newsResponse;

    public NewsAdapter(Context context, NewsResponse newsResponse) {
        this.context = context;
        this.newsResponse = newsResponse;
    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_row,parent,false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.authorTV.setText(newsResponse.getArticles().get(position).getAuthor());
        holder.titleTV.setText(newsResponse.getArticles().get(position).getTitle());
        holder.descripTV.setText(newsResponse.getArticles().get(position).getDescription());
        holder.publishTV.setText(newsResponse.getArticles().get(position).getPublishedAt());
        holder.urlTV.setText(newsResponse.getArticles().get(position).getUrl());
        Picasso.get().load(newsResponse.getArticles().get(position).getUrlToImage()).into(holder.imageViewID);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewID;
        TextView authorTV,titleTV,descripTV, publishTV,urlTV;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewID = itemView.findViewById(R.id.imageurlID);
            authorTV = itemView.findViewById(R.id.authorID);
            titleTV = itemView.findViewById(R.id.titleID);
            descripTV = itemView.findViewById(R.id.descriptionID);
            publishTV = itemView.findViewById(R.id.publishDateID);
            urlTV = itemView.findViewById(R.id.urlID);

        }
    }
}
