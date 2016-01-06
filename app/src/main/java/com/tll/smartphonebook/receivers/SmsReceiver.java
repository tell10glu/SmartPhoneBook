package com.tll.smartphonebook.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.helpers.DateUtils;
import com.tll.smartphonebook.models.Message;

import java.util.Date;

/**
 * Created by abdullahtellioglu on 29/12/15.
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("girdim","girdim");
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++)
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                for (SmsMessage message : messages) {
                    String msg = message.getMessageBody();
                    String from = message.getOriginatingAddress();
                    Toast.makeText(context,"Message"+msg +"num"+from,Toast.LENGTH_LONG).show();
                    SmartPhoneApplication application = (SmartPhoneApplication)context;
                    try {
                        new DatabaseHelper(context).addMessage(new Message(from,application.getMyPhoneNumber(),msg, DateUtils.getFullDate(new Date())));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
