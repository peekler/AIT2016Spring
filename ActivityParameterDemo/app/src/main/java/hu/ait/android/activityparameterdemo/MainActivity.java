package hu.ait.android.activityparameterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hu.ait.android.activityparameterdemo.data.ShopItem;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_NEW_ITEM = 101;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);

        Button btnNew = (Button) findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start Activity and get result
                startActivityForResult(new Intent(
                        MainActivity.this,
                        NewItemActivity.class), REQUEST_CODE_NEW_ITEM);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_CODE_NEW_ITEM) {
            if (resultCode == RESULT_OK) {
                ShopItem newItem = (ShopItem) data.getSerializableExtra(
                        NewItemActivity.KEY_NEW_ITEM);

                tvResult.append(newItem.getName()+", "+
                        newItem.getPrice()+"\n");
            } else {
                Toast.makeText(MainActivity.this, "NOT OK",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
