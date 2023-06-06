package com.ck4u.foodapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ck4u.foodapp.models.MessModel;
import com.ck4u.foodapp.retrofit.FoodAppApi;
import com.ck4u.foodapp.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private FoodAppApi api;
    MutableLiveData<MessModel> data;

    public CartRepository() {
        api = RetrofitInstance.getRetrofit().create(FoodAppApi.class);
        data = new MutableLiveData<>();
    }
    public void checkOut(int iduser, int amount, double total, String detail) {
        api.postCart(iduser,amount,total,detail).enqueue(new Callback<MessModel>() {
            @Override
            public void onResponse(Call<MessModel> call, Response<MessModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MessModel> call, Throwable t) {
                data.postValue(null);
                Log.d("loggg", t.getMessage());
            }
        });
    }

    public MutableLiveData<MessModel> messModelMutableLiveData(){
        return data;
    }

}
