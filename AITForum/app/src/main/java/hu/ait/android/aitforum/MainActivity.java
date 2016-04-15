package hu.ait.android.aitforum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ait.android.aitforum.data.ForumMessage;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.etMessage)
    EditText etMessage;
    @Bind(R.id.tvMessages)
    TextView tvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Backendless.initApp(
                this,
                "3008C063-BD5D-431E-FF84-10120E875600",
                "027282C4-27C9-5AA9-FFCF-E61EA845A600",
                "v1");
    }


    @OnClick(R.id.btnSend)
    public void sendBtnPressed(View v) {
        ForumMessage message = new ForumMessage();
        message.setSender("Peter");
        message.setMessage(etMessage.getText().toString());

        Backendless.Persistence.save(message , new BackendlessCallback<ForumMessage>()
        {
            @Override
            public void handleResponse( ForumMessage message )
            {
                Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, "Fault: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } );
    }

    private void registerUser() {
        BackendlessUser user = new BackendlessUser();
        user.setEmail( "michael@backendless.com" );
        user.setPassword( "my_super_password" );

        Backendless.UserService.register( user);
    }

}
