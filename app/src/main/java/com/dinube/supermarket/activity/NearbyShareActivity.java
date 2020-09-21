package com.dinube.supermarket.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.dinube.supermarket.R;
import com.dinube.supermarket.nearbyshare.NearbyAdvertise;

public class NearbyShareActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch advertiseSwitch;

    private String endpointName;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_share);

        context = this;
        endpointName = Build.MODEL;

        advertiseSwitch = findViewById(R.id.advertiseSwitch);

        advertiseSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                NearbyAdvertise.startAdvertising(context, endpointName);
            } else {
                NearbyAdvertise.stopAdvertising(context, endpointName);
            }
        });
    }
}