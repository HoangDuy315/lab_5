package com.ck4u.foodapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ck4u.foodapp.models.MealModel;
import com.ck4u.foodapp.repository.MealRepository;

public class CategoryViewModel  extends ViewModel {
    private MealRepository mealRepository;

    public CategoryViewModel() {
        mealRepository = new MealRepository();
    }

    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate){
        return mealRepository.getMeals(idcate);
    }
}
