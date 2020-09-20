package com.dinube.supermarket.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinube.supermarket.R;
import com.dinube.supermarket.adapters.CustomAdapter;
import com.dinube.supermarket.model.Banks;
import com.dinube.supermarket.model.ConsentResponse;
import com.dinube.supermarket.model.data.BankData;
import com.dinube.supermarket.model.data.ConsentResponseData;
import com.dinube.supermarket.retrofit.RetrofitInstance;
import com.dinube.supermarket.service.AfterBankDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BanksActivity extends AppCompatActivity {

    private List<Banks> banks;

    AfterBankDataService afterBankDataService = RetrofitInstance.getInstance().create(AfterBankDataService.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_activity);

        Call<BankData> call = afterBankDataService.findAllBanks();

        call.enqueue(new Callback<BankData>() {
            @Override
            public void onResponse(Call<BankData> call, Response<BankData> response) {
                assert response.body() != null;
                generateDataList(response.body().getList());
            }

            @Override
            public void onFailure(Call<BankData> call, Throwable t) {
                Toast.makeText(BanksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener onClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        Toast.makeText(BanksActivity.this, banks.get(position).getFullname(), Toast.LENGTH_SHORT).show();

        Call<ConsentResponseData> call = afterBankDataService.getConsent();

        call.enqueue(new Callback<ConsentResponseData>() {
            @Override
            public void onResponse(Call<ConsentResponseData> call, Response<ConsentResponseData> response) {
                assert response.body() != null;
                ConsentResponse consentResponse = response.body().getT();
                Toast.makeText(BanksActivity.this, consentResponse.toString(), Toast.LENGTH_SHORT).show();

                WebView webView = new WebView(BanksActivity.this);
                webView.loadUrl(consentResponse.getFollow());
                setContentView(webView);
            }

            @Override
            public void onFailure(Call<ConsentResponseData> call, Throwable t) {
                Toast.makeText(BanksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    };

    private void generateDataList(List<Banks> banks) {
        this.banks = banks;
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        CustomAdapter adapter = new CustomAdapter(this, banks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BanksActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.onSetClickListener(onClickListener);
    }
}
