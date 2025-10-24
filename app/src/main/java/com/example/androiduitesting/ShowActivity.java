package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView nameView = findViewById(R.id.text_city_name);
        Button backBtn = findViewById(R.id.button_back);

        Intent i = getIntent();
        String city = i.getStringExtra("city_name");
        if (city == null) city = "";
        nameView.setText(city);
        // returns to MainActivity
        backBtn.setOnClickListener(v -> finish());
    }
}

