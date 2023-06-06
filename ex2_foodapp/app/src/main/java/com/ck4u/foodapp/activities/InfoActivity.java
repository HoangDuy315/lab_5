package com.ck4u.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ck4u.foodapp.R;

public class InfoActivity extends AppCompatActivity {

    private String[][] InfoStaff = {
        {"Name: Nguyen Hoang Duy"},
        {"ID: 19521425"},
        {"Position: Staff"},
    };

    private String[][] InfoStaff2 = {
            {"Name: Bui Duc Manh"},
            {"ID: 19521822"},
            {"Position: Staff"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


    }
}