package com.example.indianewsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView titleTV,sub_descriptionTV,contentTV;
    private Button btn_read_all_news;
    String title,desc,content,imageUrl,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title =getIntent().getStringExtra("title");
        desc =getIntent().getStringExtra("des");
        content =getIntent().getStringExtra("content");
        imageUrl =getIntent().getStringExtra("image");
        url =getIntent().getStringExtra("url");


        image = (ImageView) findViewById(R.id.news_detail_image_view);
        titleTV = (TextView) findViewById(R.id.news_detail_title_text_view);
        sub_descriptionTV = (TextView) findViewById(R.id.news_detail_sub_title_text_view);
        contentTV = (TextView)findViewById(R.id.news_detail_content);
        btn_read_all_news = (Button) findViewById(R.id.read_all_news);

        titleTV.setText(title);
        sub_descriptionTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageUrl).into(image);

        btn_read_all_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW) ;
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

}