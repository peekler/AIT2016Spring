package phonelight.android.deluxcomp.hu.phonelight;

import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Peter on 2015.01.01..
 */
public class FlashLightListenerService extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final String QUERY_FLASH_ON = "/request/on";
    public static final String QUERY_FLASH_OFF = "/request/off";
    public static final String QUERY_RING = "/request/ring";
    public static final String QUERY_RESPONSE_PATH_FLASH = "/response/flash";
    public static final String QUERY_RESPONSE_PATH_RING = "/response/ring";

    private static final String TAG = "FlashLightListenerService.Mobile";
    public static final String RESULT_OK = "OK";
    public static final String RESULT_ERROR = "ERROR";

    //Communication part
    private GoogleApiClient mGoogleApiClient;
    private Node mNode;
    private String currentQueryPath = QUERY_FLASH_OFF;

    private Camera camera;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);

        //Every message create a client instance
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wearable.API)
                .build();

        //Generate answer
        currentQueryPath = messageEvent.getPath();

        //Connect when answer is ready
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        //Client connected, find wearable node
        resolveNode();
    }

    private void resolveNode() {
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                for (Node node : nodes.getNodes()) {
                    //First node is enough
                    mNode = node;
                }
                sendPendingMessage();
            }
        });
    }

    private void sendPendingMessage() {
        //Send message to wearable
        if (mNode != null && mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            byte[] result = null;
            if (currentQueryPath.equals(QUERY_FLASH_ON)) {
                result = turnOnFlashLight().getBytes();
                sendResult(QUERY_RESPONSE_PATH_FLASH, result);
            } else if (currentQueryPath.equals(QUERY_FLASH_OFF)) {
                result = turnOffFlashLight().getBytes();
                sendResult(QUERY_RESPONSE_PATH_FLASH, result);
            } else if (currentQueryPath.equals(QUERY_RING)) {
                ringPhone();
                result = RESULT_OK.getBytes();
                sendResult(QUERY_RESPONSE_PATH_RING, result);
            }
        }
    }

    private void sendResult(String queryPath, byte[] result) {
        Wearable.MessageApi.sendMessage(
                mGoogleApiClient, mNode.getId(), queryPath, result).setResultCallback(

                new ResultCallback<MessageApi.SendMessageResult>() {
                    @Override
                    public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                        if (!sendMessageResult.getStatus().isSuccess()) {
                            Log.e(TAG, getString(R.string.txt_failed_to_switch) + sendMessageResult.getStatus().getStatusCode());
                        }
                    }
                }
        );
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Not important now
        turnOffFlashLight();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Not important now
    }

    private String turnOnFlashLight() {
        try {
            camera = Camera.open();
            Camera.Parameters p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();

            return RESULT_OK;
        } catch (Exception e) {
            return RESULT_ERROR;
        }
    }

    private String turnOffFlashLight() {
        if (camera != null) {
            try {
                camera.stopPreview();
                camera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return RESULT_ERROR;
            }
            return RESULT_OK;
        } else {
            return RESULT_ERROR;
        }
    }

    private void ringPhone() {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(),
                R.raw.ring);
        mp.setVolume(1f, 1f);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        turnOffFlashLight();
    }
}
