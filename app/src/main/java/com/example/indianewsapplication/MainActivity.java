package com.example.indianewsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdaptor.CategoryClickInterface {
      private RecyclerView newsRv,categoryRV;
      private ProgressBar loadingPB;
      private ArrayList<Articales> articalesArrayList;
      private ArrayList<CategoryRecyclerModal> categoryRecyclerModalArrayList;
      private CategoryRVAdaptor categoryRVAdaptor;
      private NewsRVAdaptor newsRVAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRv = findViewById(R.id.rv_news);
        categoryRV = findViewById(R.id.rv_categories);

        loadingPB = findViewById(R.id.pb_loading);

        articalesArrayList = new ArrayList<>();
        categoryRecyclerModalArrayList = new ArrayList<>();
        newsRVAdaptor = new NewsRVAdaptor(articalesArrayList,this);
        categoryRVAdaptor =new CategoryRVAdaptor(categoryRecyclerModalArrayList,this,this::onCategoryClick) ;
        newsRv.setLayoutManager(new LinearLayoutManager(this));
        newsRv.setAdapter(newsRVAdaptor);
        categoryRV.setAdapter(categoryRVAdaptor);
        getCategories();
        getNews("All");
        newsRVAdaptor.notifyDataSetChanged();
    }

    private void getCategories(){
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("All","https://images.unsplash.com/photo-1713817130755-ae81ab9b32a4?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxNHx8fGVufDB8fHx8fA%3D%3D"));
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("Technology","https://media.istockphoto.com/id/1487972668/photo/artificial-neural-network-abstract-technology-background.webp?b=1&s=170667a&w=0&k=20&c=h9jDLJZUi6NPQ34pXXKfAQa6gGGHyYFtL3QyJ0uS6hw="));
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("Science","https://img.freepik.com/free-psd/school-science-education-concept-isolated-transparent-background_191095-22843.jpg?size=626&ext=jpg"));
//        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("Sport","https://media.istockphoto.com/id/1476198878/photo/different-sport-balls-and-equipment-soccer-ffotball-basketball-handball-rugby-and-volleyball.jpg?s=1024x1024&w=is&k=20&c=-cpIrkog2Cxtkzsp-V1Qd-oRsx5qSfqL4lDt5K8iRaM="));
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("General","https://images.unsplash.com/photo-1513542789411-b6a5d4f31634?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGdlbmVyYWx8ZW58MHx8MHx8fDA%3D"));
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("Business","https://plus.unsplash.com/premium_photo-1678453145158-07bc0009bfa6?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8YnVzaW5lc3N8ZW58MHx8MHx8fDA%3D"));
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("Entertainment","https://images.unsplash.com/photo-1496337589254-7e19d01cec44?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fGVudGVydGFpbm1lbnR8ZW58MHx8MHx8fDA%3D"));
        categoryRecyclerModalArrayList.add(new CategoryRecyclerModal("Health","https://images.unsplash.com/photo-1579684385127-1ef15d508118?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fG1lZGljYWx8ZW58MHx8MHx8fDA%3D"));

        categoryRVAdaptor.notifyDataSetChanged();
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articalesArrayList.clear();

        String categoryUrl= "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=bcc537b87aaa4c3199ad6fd6c08bed3b";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=bcc537b87aaa4c3199ad6fd6c08bed3b";
        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call = retrofitApi.getAllNews(url);

        }
        else{
            call = retrofitApi.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal= response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articales> articales = newsModal.getArticles();

                for(int i=0;i<articales.size();i++){
                    articalesArrayList.add(new Articales(articales.get(i).getTitle(),articales.get(i).getDescription()
                    ,articales.get(i).getUrlToImage(),articales.get(i).getUrl(),articales.get(i).getContent()));
                }
                newsRVAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }



    @Override
    public void onCategoryClick(int position) {
        String category = categoryRecyclerModalArrayList.get(position).getCategory();
        getNews(category);


    }
}