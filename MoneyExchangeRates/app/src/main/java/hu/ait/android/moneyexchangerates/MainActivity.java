package hu.ait.android.moneyexchangerates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hu.ait.android.moneyexchangerates.data.MoneyResult;
import hu.ait.android.moneyexchangerates.network.MoneyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.fixer.io").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final MoneyAPI moneyAPI = retrofit.create(MoneyAPI.class);

        tvResult = (TextView) findViewById(R.id.tvResult);
        Button btnGet = (Button) findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MoneyResult> moneyQuery = moneyAPI.getRatesToUsd("USD");
                moneyQuery.enqueue(new Callback<MoneyResult>() {
                    @Override
                    public void onResponse(Call<MoneyResult> call, Response<MoneyResult> response) {
                        tvResult.setText("HUF"+response.body().getRates().getHUF());
                    }

                    @Override
                    public void onFailure(Call<MoneyResult> call, Throwable t) {
                        tvResult.setText("ERROR: "+t.getMessage());
                    }
                });
            }
        });
    }
}
