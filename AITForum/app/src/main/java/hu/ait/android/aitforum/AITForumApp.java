package hu.ait.android.aitforum;

import android.app.Application;

import com.backendless.Backendless;

public class AITForumApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp(this,
                "3008C063-BD5D-431E-FF84-10120E875600",
                "027282C4-27C9-5AA9-FFCF-E61EA845A600",
                "v1");
    }
}
