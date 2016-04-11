package hu.ait.android.activityparameterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.ait.android.activityparameterdemo.data.ShopItem;

public class NewItemActivity extends AppCompatActivity {

    public static final String KEY_NEW_ITEM = "KEY_NEW_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPrice = (EditText) findViewById(R.id.etPrice);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopItem newItem = new ShopItem(etName.getText().toString(),
                        etPrice.getText().toString());

                Intent intentResult = new Intent();
                intentResult.putExtra(KEY_NEW_ITEM, newItem);

                setResult(RESULT_OK, intentResult);

                finish();
            }
        });
    }
}
