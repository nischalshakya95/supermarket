package com.dinube.supermarket.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dinube.supermarket.R;
import com.dinube.supermarket.nearbyshare.NearbyAdvertise;
import com.dinube.supermarket.utils.UiUtils;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;

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

    class NearbyPayloadCallback extends PayloadCallback {

        @Override
        public void onPayloadReceived(@NonNull String s, @NonNull Payload payload) {
            byte[] receivedBytes = payload.asBytes();
            String message = new String(receivedBytes);
            UiUtils.showToast(context, message);
        }

        @Override
        public void onPayloadTransferUpdate(@NonNull String s, @NonNull PayloadTransferUpdate payloadTransferUpdate) {

        }
    }
}