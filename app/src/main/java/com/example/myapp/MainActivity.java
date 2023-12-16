package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    AppList appList = new AppList();
    WeatherData weatherData = new WeatherData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,appList).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2,weatherData).commit();
    }

}