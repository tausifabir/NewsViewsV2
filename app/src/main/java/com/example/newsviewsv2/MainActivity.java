package com.example.newsviewsv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    ActionBarDrawerToggle drawerToggle;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerID);
        recyclerView = findViewById(R.id.newsRecyclerView);
        NavigationView navigationView = findViewById(R.id.navigationID);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.navi_open,
                R.string.navi_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setCheckedItem(R.id.Home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                            NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this,newsResponse);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(newsAdapter);
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
                break;
            case R.id.Home:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.Home){
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.about){
            Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.login){
            Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.exit){
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
        }

}
