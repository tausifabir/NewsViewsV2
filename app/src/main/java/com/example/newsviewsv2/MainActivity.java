package com.example.newsviewsv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newsviewsv2.Adapter.NewsAdapter;
import com.example.newsviewsv2.NewsApi.NewsResponse;
import com.example.newsviewsv2.NewsApi.NewsService;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    public  static final String baseUrl ="https://newsapi.org/";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerID);
        recyclerView = findViewById(R.id.newsRecyclerView);
        NavigationView navigationView = findViewById(R.id.navigationID);
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.navi_open,
                R.string.navi_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




/*
        NewsService newsService = RetrofitClient
                .getClient(baseUrl)
                .create(NewsService.class);
        newsService.getNewsResponse()
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if(response.isSuccessful()){
                            NewsResponse newsResponse = response.body();
                            LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(MainActivity.this);
                            adapter = new NewsAdapter(MainActivity.this, newsResponse);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(MainActivity.this, ""+newsResponse.getTotalResults(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        Log.e("onFailure: ",t.getLocalizedMessage());
                    }
                });
*/



        getApi();


    }



    public void getApi(){

        NewsService newsService = RetrofitClient
                .getClient(baseUrl)
                .create(NewsService.class);
        newsService.getNewsResponse()
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if(response.isSuccessful()){
                            NewsResponse newsResponse = response.body();
                            LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(MainActivity.this);
                            adapter = new NewsAdapter(MainActivity.this, newsResponse);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(MainActivity.this, ""+newsResponse.getTotalResults(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        Log.e("onFailure: ",t.getLocalizedMessage());
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.refresh:
                Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.Home:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
