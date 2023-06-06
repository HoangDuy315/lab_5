package com.ck4u.foodapp.retrofit;

import com.ck4u.foodapp.models.CategoryModel;
import com.ck4u.foodapp.models.MealDetailModel;
import com.ck4u.foodapp.models.MealModel;
import com.ck4u.foodapp.models.MessModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodAppApi {
    @GET("category.php")
    Call<CategoryModel> getCategory();

    @POST("meal.php")
    @FormUrlEncoded
    Call<MealModel> getMeals(
            @Field("idcate") int idcate
    );

    @POST("mealdetail.php")
    @FormUrlEncoded
    Call<MealDetailModel> getMealsDetail(
            @Field("id") int id
    );

    @POST("cart.php")
    @FormUrlEncoded
    Call<MessModel> postCart(
            @Field("iduser") int id,
            @Field("amount") int amount,
            @Field("total") double total,
            @Field("detail") String detail
            );
}
