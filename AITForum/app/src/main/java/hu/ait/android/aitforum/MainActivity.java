package hu.ait.android.aitforum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Iterator;

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

    }

    @OnClick(R.id.btnSend)
    public void sendClick(View v) {
        // save the message on BaaS
        ForumMessage message = new ForumMessage();
        message.setSender("Peter");
        message.setMessageText(etMessage.getText().toString());

        Backendless.Persistence.save(message,
                new BackendlessCallback<ForumMessage>() {
            @Override
            public void handleResponse(ForumMessage response) {
                Toast.makeText(MainActivity.this,
                        "Message saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this,
                        "Error: "+fault.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnRefresh)
    public void refreshButtonClick(View v) {
        refreshMessages();
    }

    public void refreshMessages() {
        Backendless.Persistence.of(ForumMessage.class).find(new BackendlessCallback<BackendlessCollection<ForumMessage>>() {
            @Override
            public void handleResponse(
                    BackendlessCollection<ForumMessage> response) {

                Iterator<ForumMessage> messageIterator =
                        response.getCurrentPage().iterator();

                StringBuffer messageBuffer = new StringBuffer();

                while (messageIterator.hasNext()) {
                    ForumMessage message = messageIterator.next();

                    // use the message
                    messageBuffer.append("<"+message.getSender()+"> "+
                    message.getMessageText()+"\n");
                }

                tvMessages.setText(messageBuffer.toString());
            }
        });
    }

}
