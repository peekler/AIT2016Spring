package hu.ait.android.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hu.ait.android.fragmentdemo.fragment.FragmentDetails;
import hu.ait.android.fragmentdemo.fragment.FragmentMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMain = (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(FragmentMain.TAG);
            }
        });
        Button btnDetails = (Button) findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(FragmentDetails.TAG);
            }
        });


        showFragment(FragmentMain.TAG);
    }

    private void showFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment == null) {
            if (tag.equals(FragmentMain.TAG)) {
                fragment = new FragmentMain();
            } else if (tag.equals(FragmentDetails.TAG)){
                fragment = new FragmentDetails();
            }
        }

        FragmentTransaction trans = fragmentManager.beginTransaction();

        trans.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        trans.replace(R.id.layoutContainer, fragment, tag);

        trans.addToBackStack(null);

        trans.commit();
    }

}
