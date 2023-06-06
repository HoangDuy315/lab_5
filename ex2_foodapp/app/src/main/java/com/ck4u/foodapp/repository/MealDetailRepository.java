package com.ck4u.foodapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ck4u.foodapp.models.MealDetailModel;
import com.ck4u.foodapp.retrofit.FoodAppApi;
import com.ck4u.foodapp.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailRepository {
    private FoodAppApi appApi;

    public MealDetailRepository() {
        appApi = RetrofitInstance.getRetrofit().create(FoodAppApi.class);
    }
    public MutableLiveData<MealDetailModel> getMealDetail(int id) {
        MutableLiveData<MealDetailModel> data = new MutableLiveData<>();
        appApi.getMealsDetail(id).enqueue(new Callback<MealDetailModel>() {
            @Override
            public void onResponse(Call<MealDetailModel> call, Response<MealDetailModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MealDetailModel> call, Throwable t) {
                data.setValue(null);
                Log.d("logg", t.getMessage());
            }
        });
        return data;
    }
}
