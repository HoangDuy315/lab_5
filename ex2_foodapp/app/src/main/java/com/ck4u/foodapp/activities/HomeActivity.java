package com.ck4u.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ck4u.foodapp.R;
import com.ck4u.foodapp.adapters.CategoryAdapter;
import com.ck4u.foodapp.adapters.PopularAdapter;
import com.ck4u.foodapp.databinding.ActivityHomeBinding;
import com.ck4u.foodapp.listener.CategoryListener;
import com.ck4u.foodapp.listener.EventClickListener;
import com.ck4u.foodapp.models.Category;
import com.ck4u.foodapp.models.Meals;
import com.ck4u.foodapp.viewModel.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements CategoryListener, EventClickListener {

    HomeViewModel homeViewModel;
    ActivityHomeBinding binding;

    ImageView imageView;

    FirebaseAuth auth;
    FirebaseUser user;
    TextView userDetail;
    ImageView logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        userDetail = findViewById(R.id.tvhi);
        logoutBtn = findViewById(R.id.logout);
        user =auth.getCurrentUser();
        if(user == null) {
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            userDetail.setText(user.getEmail());
        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initView();
        initData();

    }

    private void initView() {
        binding.rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rcCategory.setLayoutManager(layoutManager);

        binding.rcPopular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, 3);
        binding.rcPopular.setLayoutManager(layoutManager1);

        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(info);
            }
        });

        binding.floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(cart);
            }
        });
    }

    private void initData() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.categoryModelMutableLiveData().observe(this, categoryModel -> {
            if(categoryModel.isSuccess()) {
                CategoryAdapter adapter = new CategoryAdapter(categoryModel.getResult(), this);
                binding.rcCategory.setAdapter(adapter);
            }
        });
        homeViewModel.mealModelMutableLiveData(2).observe(this, mealModel -> {
            if(mealModel.isSuccess()) {
                PopularAdapter adapter = new PopularAdapter(mealModel.getResult(), this);
                binding.rcPopular.setAdapter(adapter);
            }
        });
    }


    //bat su kien chuyen man hinh toi category page
    @Override
    public void onCategoryClick(Category category) {
        Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
        intent.putExtra("idcate", category.getId());
        intent.putExtra("namecate", category.getCategory());
        startActivity(intent);
    }

    @Override
    public void onPopularClick(Meals meals) {
        Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
        intent.putExtra("id", meals.getIdMeal());
        startActivity(intent);
    }
}