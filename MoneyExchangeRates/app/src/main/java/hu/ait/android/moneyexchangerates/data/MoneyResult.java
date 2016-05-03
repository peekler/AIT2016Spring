package hu.ait.android.moneyexchangerates.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoneyResult {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    /**
     *
     * @return
     * The base
     */
    public String getBase() {
        return base;
    }

    /**
     *
     * @param base
     * The base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The rates
     */
    public Rates getRates() {
        return rates;
    }

    /**
     *
     * @param rates
     * The rates
     */
    public void setRates(Rates rates) {
        this.rates = rates;
    }

}
