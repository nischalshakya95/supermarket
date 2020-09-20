package com.dinube.supermarket.service;

import com.dinube.supermarket.model.data.BankData;
import com.dinube.supermarket.model.data.ConsentResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AfterBankDataService {

    @GET("/listOfSupportedBanks")
    Call<BankData> findAllBanks();

    @GET("/consent/get")
    Call<ConsentResponseData> getConsent();
}
