package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("ProjetMobile", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<r6_info> r6_infoList = getDataFromCache();
        if(r6_infoList != null){
            showList(r6_infoList);
        }else{
            makeApiCall();
        }
    }


    private List<r6_info> getDataFromCache() {
        String jsonr6_info = sharedPreferences.getString("jsonr6_infoList", null);

        if(jsonr6_info == null){
            return null;
        }else {
            Type listType = new TypeToken<List<r6_info>>(){}.getType();
            return gson.fromJson(jsonr6_info, listType);
        }
    }



    private void showList(List<r6_info>r6_infoList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(r6_infoList,this);
        recyclerView.setAdapter(mAdapter);
    }

    private static final String BASE_URL = "https://raw.githubusercontent.com/val-gam/Projet/master/";
    private void makeApiCall(){


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
                    saveList(r6_infoList);
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

    private void saveList(List<r6_info> r6_infoList) {
        String jsonString = gson.toJson(r6_infoList);
        sharedPreferences
                .edit()
                .putString("jsonr6_infoList", jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
    }
}
