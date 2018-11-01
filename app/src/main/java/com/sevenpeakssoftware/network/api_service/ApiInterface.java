package com.sevenpeakssoftware.network.api_service;

import com.sevenpeakssoftware.model.CarResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("article/get_articles_list")
    Call<CarResponse> getCarsList();

}
