package com.ck4u.foodapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ck4u.foodapp.models.MealDetailModel;
import com.ck4u.foodapp.repository.MealDetailRepository;

public class ShowDetailViewModel extends ViewModel {
     private MealDetailRepository mealDetailRepository;

    public ShowDetailViewModel() {
        mealDetailRepository = new MealDetailRepository();
    }
    public MutableLiveData<MealDetailModel> mealDetailModelMutableLiveData(int id) {
     return mealDetailRepository.getMealDetail(id);
    }
}
