package com.example.android3_dz3_v2_30.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.android3_dz3_v2_30.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
    }
}