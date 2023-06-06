package com.ck4u.foodapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ck4u.foodapp.models.MealModel;
import com.ck4u.foodapp.models.MessModel;
import com.ck4u.foodapp.repository.CartRepository;

public class CartViewModel extends ViewModel {
    private CartRepository cartRepository;
    private MutableLiveData<MessModel> mutableLiveData;
    public void init() {
        cartRepository = new CartRepository();
        mutableLiveData = cartRepository.messModelMutableLiveData();
    }
    public void checkOut(int iduser, int amount, double total, String detail) {
        cartRepository.checkOut(iduser, amount, total, detail);
    }
    public MutableLiveData<MessModel> messModelMutableLiveData() {
        return mutableLiveData;
    }

}
