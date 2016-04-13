package io.taweesoft.wonglhao.managers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by TAWEESOFT on 4/13/16 AD.
 */
public class Utility {

    public static Toast showToastDialog(Context context, String message , boolean isShowLong) {
        return Toast.makeText(context , message, isShowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
    }
}
