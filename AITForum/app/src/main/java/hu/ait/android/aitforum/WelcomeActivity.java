package hu.ait.android.aitforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ResideMenu resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);

        ResideMenuItem menuItemLogin = new ResideMenuItem(this,
                R.drawable.ic_polymer, "Login");
        menuItemLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start login Activity
                startActivity(new Intent(WelcomeActivity.this,
                        LoginActivity.class));
            }
        });
        resideMenu.addMenuItem(menuItemLogin, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem menuItemAbout = new ResideMenuItem(this,
                R.drawable.ic_polymer, "About");
        resideMenu.addMenuItem(menuItemAbout, ResideMenu.DIRECTION_LEFT);

    }
}
