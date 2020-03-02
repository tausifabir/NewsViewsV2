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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newsviewsv2.Adapter.NewsAdapter;
import com.example.newsviewsv2.NewsApi.NewsResponse;
import com.example.newsviewsv2.NewsApi.NewsService;
import com.example.newsviewsv2.SharedPreference.UserPreference;
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

    private UserPreference userPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.newsRecyclerView);


        /////******** Drawerlayout Code******** ////
        drawerLayout = findViewById(R.id.drawerID);
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



        //*******SharedPreference**********//

        userPreference = new UserPreference(this);

        userPreference.setLoginStatus(false);


        //******** Retrofit Api Get method ******** //
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
                            newsAdapter = new NewsAdapter(MainActivity.this,newsResponse);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(newsAdapter);
                            Toast.makeText(MainActivity.this, ""+newsResponse.getTotalResults(), Toast.LENGTH_SHORT).show();


                        }else{
                            Toast.makeText(MainActivity.this, "Api key experied ", Toast.LENGTH_SHORT).show();
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


        MenuItem loginItem = menu.findItem(R.id.login);
        MenuItem logoutItem = menu.findItem(R.id.logout);

        if(userPreference.getLoginStatus()){
            loginItem.setVisible(false);
            logoutItem.setVisible(true);

        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.refresh:
                Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Home:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                //userPreference.setLoginStatus(false);
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                userPreference.setLoginStatus(true);

                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
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
            userPreference.setLoginStatus(true);
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
