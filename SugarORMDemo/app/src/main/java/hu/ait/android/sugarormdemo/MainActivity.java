package hu.ait.android.sugarormdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ait.android.sugarormdemo.data.City;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.etCity)
    EditText etCity;
    @Bind(R.id.tvData)
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSave)
    public void saveClicked(View v){
        City newCity = new City(etCity.getText().toString(),
                new Date(System.currentTimeMillis()).toString());
        newCity.save();
        refreshList();
    }

    @OnClick(R.id.btnQuery)
    public void queryCities(View v) {
        refreshList();
    }

    private void refreshList() {
        List<City> cities = City.listAll(City.class);


        tvData.setText("");
        for (City city : cities) {
            tvData.append(city.getCityName()+", "+city.getDateAdded()+"\n");
        }
    }

    @OnClick(R.id.btnDeleteAll)
    public void deleteAll(View v) {
        City.deleteAll(City.class);
        refreshList();
    }

}
