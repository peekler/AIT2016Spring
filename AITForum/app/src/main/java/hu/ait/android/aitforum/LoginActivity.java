package hu.ait.android.aitforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.dd.CircularProgressButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPwd)
    EditText etPwd;
    @Bind(R.id.etMail)
    EditText etMail;
    @Bind(R.id.btnRegister)
    CircularProgressButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void registerClick(View v) {
        btnRegister.setIndeterminateProgressMode(true);
        btnRegister.setProgress(50);

        BackendlessUser newUser = new BackendlessUser();
        newUser.setEmail(etMail.getText().toString());
        newUser.setProperty("userName", etUserName.getText().toString());
        newUser.setPassword(etPwd.getText().toString());

        Backendless.UserService.register(newUser, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(LoginActivity.this,
                        "REGISTRATION OK", Toast.LENGTH_SHORT).show();

                btnRegister.setProgress(100);
                btnRegister.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnRegister.setProgress(0);
                    }
                }, 2000);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                btnRegister.setProgress(-1);
                btnRegister.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnRegister.setProgress(0);
                    }
                }, 1000);
                Toast.makeText(LoginActivity.this,
                        "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnLogin)
    public void loginClick(View v) {

        Backendless.UserService.login(
                etMail.getText().toString(),
                etPwd.getText().toString(),
                new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        startActivity(new Intent(LoginActivity.this,
                                MainActivity.class));
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(LoginActivity.this,
                                "Error: "+fault.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
