package io.taweesoft.wonglhao;

import android.app.Application;

import io.taweesoft.wonglhao.managers.Utility;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by TAWEESOFT on 4/20/16 AD.
 */
public class WongLhaoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/cloud-light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
