package com.sevenpeakssoftware.mahesh.articles;

import com.sevenpeakssoftware.model.CarResponse;
import com.sevenpeakssoftware.network.api_service.ApiInterface;
import com.sevenpeakssoftware.network.client.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCarInteractorImpl implements ArticleContract.GetCarIntractor {

    @Override
    public void getCarArrayList(final OnFinishedListener onFinishedListener) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call call = apiService.getCarsList();

        call.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                if (response.body() != null) {
                    if(response.body().getStatus().equalsIgnoreCase("SUCCESS")){
                        onFinishedListener.onFinished(response.body().getCarsList());
                    }else{
                        onFinishedListener.onResponseFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                onFinishedListener.onApiFailure(t);
            }
        });
    }
}
