package com.dinube.supermarket.service;

import com.dinube.supermarket.model.BankData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AfterBankDataService {

    @GET("/listOfSupportedBanks")
    Call<BankData> findAllBanks();
}
