package com.dinube.supermarket.nearbyshare;

import android.content.Context;

import com.dinube.supermarket.utils.UiUtils;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.Strategy;

public class NearbyAdvertise {

    public static void startAdvertising(Context context, String endpointName) {
        AdvertisingOptions advertisingOptions = new AdvertisingOptions.Builder().setStrategy(Strategy.P2P_CLUSTER).build();
        Nearby.getConnectionsClient(context).startAdvertising(endpointName, "com.dinube.supermarket",
                new NearbyConnectionLifeCycleCallBack(context), advertisingOptions)
                .addOnSuccessListener((Void unused) -> {
                    UiUtils.showToast(context, "Advertising " + endpointName);
                })
                .addOnFailureListener((e) -> {
                    UiUtils.showToast(context, "Exception in start advertising " + e.getMessage());
                });
    }

    public static void stopAdvertising(Context context, String endpointName) {
        Nearby.getConnectionsClient(context).stopAdvertising();
        Nearby.getConnectionsClient(context).stopAllEndpoints();
        UiUtils.showToast(context, "Advertise Stopped " + endpointName);
    }
}
