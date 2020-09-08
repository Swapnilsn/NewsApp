package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAppAdapter  extends RecyclerView.Adapter<NewsAppAdapter.NewsDataViewHolder> {
    ArrayList<NewsApp> arrayList;
    public NewsAppAdapter(ArrayList<NewsApp> arrayList) {
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public NewsAppAdapter.NewsDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newsapp,parent,false);

        return new NewsDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAppAdapter.NewsDataViewHolder holder, int position) {
        final NewsApp n = arrayList.get(position);
        holder.tvTitle.setText(n.getTitle());
       Picasso.get()
                .load(n.getUrlToImage())
              .placeholder(R.drawable.ico_news)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.newImage);

        holder.tvAuthor.setText(n.getAuthor());
        holder.tvDate.setText(n.publishedAt);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailedNews.class);
                i.putExtra("author", n.getAuthor());
                i.putExtra("title", n.getTitle());
                i.putExtra("description", n.getDescription());
                i.putExtra("url", n.getUrl());
                i.putExtra("urlToImage", n.getUrlToImage());
                i.putExtra("publishedAt", n.getPublishedAt());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override

    public int getItemCount() {
        return arrayList.size();
    }

    public class NewsDataViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView tvTitle;
        ImageView newImage;
        TextView tvAuthor;
        TextView tvDate;
        Button btnShow;
        WebView webView;
        public NewsDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            newImage=itemView.findViewById(R.id.profile_image);
            tvAuthor=itemView.findViewById(R.id.tvAuthor);
            tvDate=itemView.findViewById(R.id.tvDate);
            card=itemView.findViewById(R.id.card);
            webView=itemView.findViewById(R.id.web);

            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    webView.setWebViewClient(new WebViewClient());
              webView.loadUrl("http://www.google.com");

                }
            });


        }


    }
}
