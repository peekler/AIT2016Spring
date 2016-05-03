package hu.ait.android.moneyexchangerates.network;

import hu.ait.android.moneyexchangerates.data.MoneyResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoneyAPI {
    @GET("latest")
    Call<MoneyResult> getRatesToUsd(@Query("base") String base);
}
