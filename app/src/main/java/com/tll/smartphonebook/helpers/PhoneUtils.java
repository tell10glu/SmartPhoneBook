package com.tll.smartphonebook.helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by abdullahtellioglu on 29/12/15.
 */
public class PhoneUtils {
    public static void call(Activity activity,String numberToCall){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + numberToCall));
        activity.startActivity(intent);
    }
}
