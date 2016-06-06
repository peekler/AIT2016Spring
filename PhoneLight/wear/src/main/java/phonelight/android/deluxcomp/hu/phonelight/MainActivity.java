package phonelight.android.deluxcomp.hu.phonelight;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, MessageApi.MessageListener {

    public static final String QUERY_FLASH_ON = "/request/on";
    public static final String QUERY_FLASH_OFF = "/request/off";
    public static final String QUERY_RING = "/request/ring";
    public static final String QUERY_RESPONSE_PATH_FLASH = "/response/flash";
    public static final String QUERY_RESPONSE_PATH_RING = "/response/ring";



    private GoogleApiClient mGoogleApiClient;
    private Node mNode;
    private boolean mResolvingError = false;
    private Handler handler = new Handler();

    private CircularProgressButton btnFlash;
    private CircularProgressButton btnRing;

    private boolean flashOnRequest = true;

    public static final String RESULT_ERROR = "ERROR";
    public static final String RESULT_OK = "OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                btnFlash = (CircularProgressButton) stub.findViewById(R.id.btnFlashLight);
                btnFlash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnFlash.setIndeterminateProgressMode(true);
                        btnFlash.setProgress(50);
                        sendFlashLightRequestMessage(flashOnRequest);
                    }
                });

                btnRing = (CircularProgressButton) stub.findViewById(R.id.btnRing);
                btnRing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnRing.setIndeterminateProgressMode(true);
                        btnRing.setProgress(50);
                        sendRingRequestMessage();
                    }
                });
            }
        });

        //Connect the GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        //Client connected, find handheld node
        resolveNode();
        //Add data listener
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
    }

    private void resolveNode() {
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                for (Node node : nodes.getNodes()) {
                    //First node is enough
                    mNode = node;
                    break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Connect if client is disconnected
        if (!mResolvingError && !mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        sendFlashLightRequestMessage(false);
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            //Disconnect data listener
            Wearable.MessageApi.removeListener(mGoogleApiClient, this);
            //Disconnect client
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnectionSuspended(int i) {
        sendFlashLightRequestMessage(false);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Not important now
    }

    private void sendFlashLightRequestMessage(boolean requestOn) {
        //Send message to handheld iff client is connected
        if (mNode != null && mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            String requestPath = requestOn ? QUERY_FLASH_ON : QUERY_FLASH_OFF;

            Wearable.MessageApi.sendMessage(
                    mGoogleApiClient, mNode.getId(), requestPath,  null).setResultCallback(
                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                            if (!sendMessageResult.getStatus().isSuccess()) {
                                showFlashButtonError();
                            } else {
                                flashOnRequest = !flashOnRequest;
                                showFlashButtonCompleted();
                            }
                        }
                    }
            );
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.txt_not_connected), Toast.LENGTH_LONG).show();
            showFlashButtonError();
        }
    }

    private void sendRingRequestMessage() {
        //Send message to handheld iff client is connected
        if (mNode != null && mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Wearable.MessageApi.sendMessage(
                    mGoogleApiClient, mNode.getId(), QUERY_RING,  null).setResultCallback(
                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                            if (!sendMessageResult.getStatus().isSuccess()) {
                                showRingButtonError();
                            } else {
                                showRingButtonCompleted();
                            }
                        }
                    }
            );
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.txt_not_connected), Toast.LENGTH_LONG).show();
            showRingButtonError();
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        // no need to handle
    }

    private void showFlashButtonCompleted() {
        btnFlash.setProgress(100);
        btnFlash.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnFlash.setProgress(0);
            }
        }, 2000);
    }

    private void showFlashButtonError() {
        btnFlash.setProgress(-1);
        btnFlash.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnFlash.setProgress(0);
            }
        }, 2000);
    }

    private void showRingButtonCompleted() {
        btnRing.setProgress(100);
        btnRing.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnRing.setProgress(0);
            }
        }, 500);
    }

    private void showRingButtonError() {
        btnRing.setProgress(-1);
        btnRing.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnRing.setProgress(0);
            }
        }, 1000);
    }
}
