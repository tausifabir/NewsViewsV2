package com.example.newsviewsv2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsviewsv2.NewsApi.NewsResponse;
import com.example.newsviewsv2.NewsApi.Source;
import com.example.newsviewsv2.NewsItem;
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

        holder.timeTV.setText(newsResponse.getArticles().get(position).getPublishedAt());
        Picasso.get().load(newsResponse.getArticles().get(position).getUrlToImage()).into(holder.imageViewID);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ImageView = newsResponse.getArticles().get(position).getUrlToImage();
                String Author = newsResponse.getArticles().get(position).getAuthor();
                String Title = newsResponse.getArticles().get(position).getTitle();
                String Descrip = newsResponse.getArticles().get(position).getDescription();
                String Content = newsResponse.getArticles().get(position).getContent();
                String PublishDate = newsResponse.getArticles().get(position).getPublishedAt();


                Intent intent = new Intent(context,NewsItem.class)
                        .putExtra("image",ImageView)
                        .putExtra("author",Author)
                        .putExtra("title",Title)
                        .putExtra("des",Descrip)
                        .putExtra("content",Content)
                        .putExtra("pub",PublishDate);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return newsResponse.getArticles().size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewID;
        TextView authorTV,titleTV,descripTV, publishTV, sourceTV,timeTV;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewID = itemView.findViewById(R.id.imageurlID);
            authorTV = itemView.findViewById(R.id.authorID);
            titleTV = itemView.findViewById(R.id.titleID);
            descripTV = itemView.findViewById(R.id.descriptionID);
            publishTV = itemView.findViewById(R.id.publishDateID);
            timeTV = itemView.findViewById(R.id.TimeID);

        }
    }
}
