package hg.com.frescotest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Hurman on 24/02/2016.
 */
public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
