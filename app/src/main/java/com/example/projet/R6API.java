package com.example.projet;

import retrofit2.Call;
import retrofit2.http.GET;

public interface R6API {
    @GET("r6info_API.json")
    Call<RestR6Response> getR6Response();
}
