package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        makeApiCall();
    }

    private void showList(List<r6_info>r6_infoList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(r6_infoList);
        recyclerView.setAdapter(mAdapter);
    }

    private static final String BASE_URL = "https://raw.githubusercontent.com/val-gam/Projet_rainbowsix_siege/master/";
    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        R6API r6API = retrofit.create(R6API.class);

        Call<RestR6Response> call = r6API.getR6Response();
        call.enqueue(new Callback<RestR6Response>() {
            @Override
            public void onResponse(Call<RestR6Response> call, Response<RestR6Response> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<r6_info> r6_infoList = response.body().getResults();
                    Toast.makeText(getApplicationContext(), "API Succes", Toast.LENGTH_SHORT).show();
                    showList(r6_infoList);
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestR6Response> call, Throwable t) {
                    showError();
            }
        });


    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
    }
}
