package io.taweesoft.wonglhao.managers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by TAWEESOFT on 4/13/16 AD.
 */
public class Utility {

    public static Toast showToastDialog(Context context, String message , boolean isShowLong) {
        return Toast.makeText(context , message, isShowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
    }

    public static void canAccessLocation(Activity activity) {
        final int REQUEST_CODE_ASK_PERMISSIONS = 123;
        int hasWriteContactsPermission = activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
    }
}
