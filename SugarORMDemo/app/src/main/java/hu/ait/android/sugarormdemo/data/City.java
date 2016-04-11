package hu.ait.android.sugarormdemo.data;

import com.orm.SugarRecord;

public class City extends SugarRecord {

    String cityName;
    String dateAdded;

    public City() {
    }

    public City(String cityName, String dateAdded) {
        this.cityName = cityName;
        this.dateAdded = dateAdded;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
