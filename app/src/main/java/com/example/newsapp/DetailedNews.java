package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedNews extends AppCompatActivity {
    ImageView imgHeader;
    TextView tvAuthor;
    TextView tvTitle;
    TextView tvDescription;
    TextView  tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);
        imgHeader=findViewById(R.id.imgHeader);
        tvAuthor=findViewById(R.id.tvAuthor);
        tvTitle=findViewById(R.id.tvTitle);
        tvDescription=findViewById(R.id.tvDescription);
        tvTime=findViewById(R.id.tvTime);
        Picasso.get()
                .load(getIntent().getStringExtra("urlToImage"))
                .placeholder(R.drawable.ico_news)
                .error(R.drawable.ic_launcher_foreground)
                .into(imgHeader);
        tvAuthor.setText(getIntent().getStringExtra("author"));
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvDescription.setText(getIntent().getStringExtra("description"));
        tvTime.setText(getIntent().getStringExtra("publishedAt"));


    }
}