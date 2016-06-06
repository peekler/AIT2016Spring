package flame.hu.deluxcomp.android.flame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.wearable.view.WatchViewStub;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mTextView;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FlameView(this));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}
