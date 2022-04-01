package com.hamza.ieeechallenge.ui.home;

import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.model.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApiCall {
    //https://run.mocky.io/v3/493f3a0e-506e-4a71-b6fc-d0854c9e0360
    @GET("v3/493f3a0e-506e-4a71-b6fc-d0854c9e0360")
    Call<JSONResponse> getFood();
}
