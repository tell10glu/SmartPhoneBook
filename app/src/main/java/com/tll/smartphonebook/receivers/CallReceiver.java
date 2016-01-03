package com.tll.smartphonebook.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.models.Contact;
import com.tll.smartphonebook.models.ContactCalls;

import java.util.Date;

/**
 * Created by abdullahtellioglu on 28/12/15.
 */
public class CallReceiver extends BroadcastReceiver {

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static boolean isIncoming;
    private static String savedNumber;  //because the passed incoming is only valid in ringing
    private long lastCurrentMillis=0;
    private DatabaseHelper helper;
    @Override
    public void onReceive(Context context, Intent intent) {
        helper = new DatabaseHelper(context);
        //We listen to two intents.  The new outgoing call only tells us of an outgoing call.  We use it to get the number.
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            ContactCalls calls = new ContactCalls(savedNumber,0,new Date(),false);
            helper.addContactCalls(calls);
        }
        else{
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                ContactCalls calls = null;
                if(lastState==TelephonyManager.CALL_STATE_RINGING){
                    // cevapsiz
                    calls = new ContactCalls(number,0,new Date(),true);

                }else if(lastState==TelephonyManager.CALL_STATE_OFFHOOK){
                    // konusuludu
                        calls = new ContactCalls(number,System.currentTimeMillis()-lastCurrentMillis,new Date(),true);
                }else{
                    // bu durum yok sanirsam...
                }
                if(calls!=null)
                    helper.addContactCalls(calls);
                lastState = TelephonyManager.CALL_STATE_IDLE;

            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                lastState = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                lastState = TelephonyManager.CALL_STATE_RINGING;
            }
            lastCurrentMillis = System.currentTimeMillis();

        }
    }

}
