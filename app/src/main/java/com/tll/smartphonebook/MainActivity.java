package com.tll.smartphonebook;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tll.smartphonebook.constants.BaseConstants;
import com.tll.smartphonebook.dialogs.BaseDialogs;
import com.tll.smartphonebook.fragments.CallFragment;
import com.tll.smartphonebook.fragments.ContactsFragment;
import com.tll.smartphonebook.fragments.HistoryCallsFragment;
import com.tll.smartphonebook.helpers.SharedPreferenceUtils;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String CALL_FRAGMENT = "CALL_FRAGMENT";
    private static final String CONTACT_FRAGMENT = "CONTACT_FRAGMENT";
    private static final String CONTACT_HISTORY_FRAGMENT = "CONTACT_HISTORY_FRAGMENT";
    private static final String SMS_FRAGMENT = "SMS_FRAGMENT";
    private Fragment[] fragments; // arama 1, contacts 2 , history 3, sms 4
    private Button btnCall,btnContacts,btnHistory,btnSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new Fragment[4];
        fragments[0] = new CallFragment();
        fragments[1] = new ContactsFragment();
        fragments[2] = new HistoryCallsFragment();

        init();
        // default olarak atanacak olanlar
        addFragment(CALL_FRAGMENT);
        btnCall.setSelected(true);
        btnCall.setTextColor(Color.BLACK);
        if(!SharedPreferenceUtils.readBoolean(getApplicationContext(),BaseConstants.IS_APP_STARTED_FIRST)){
            BaseDialogs.getContactExportDialog(this).show();
        }
    }
    private void init(){
        btnCall = (Button)findViewById(R.id.activity_main_tab_call);
        btnContacts = (Button)findViewById(R.id.activity_main_contacts);
        btnHistory = (Button)findViewById(R.id.activity_main_contact_history);
        btnSms = (Button)findViewById(R.id.activity_main_contact_messages);
        btnCall.setOnClickListener(this);
        btnContacts.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnSms.setOnClickListener(this);
    }
    private void setButtonDefaultColors(){
        btnCall.setSelected(false);
        btnContacts.setSelected(false);
        btnSms.setSelected(false);
        btnHistory.setSelected(false);
        btnCall.setTextColor(Color.WHITE);
        btnHistory.setTextColor(Color.WHITE);
        btnSms.setTextColor(Color.WHITE);
        btnContacts.setTextColor(Color.WHITE);
    }
    private boolean isFragmentVisible(String tag){
        Fragment myFragment = getFragmentManager().findFragmentByTag(tag);
        if (myFragment != null && myFragment.isVisible()) {
            return true;
        }
        return false;
    }
    private void addFragment(String tag){
        Fragment fragment = null;
        switch (tag){
            case CALL_FRAGMENT:
                fragment = fragments[0];
                break;
            case CONTACT_FRAGMENT:
                fragment = fragments[1];
                break;
            case CONTACT_HISTORY_FRAGMENT:
                fragment = fragments[2];
                break;
            case SMS_FRAGMENT:
                fragment = fragments[3];
                break;
            default:
                break;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity_fragment_area, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }
    @Override
    public void onClick(View view) {
        String fragmentTag ="";
        switch (view.getId()){
            case R.id.activity_main_tab_call:
                fragmentTag = CALL_FRAGMENT;
                break;
            case R.id.activity_main_contacts:
                fragmentTag = CONTACT_FRAGMENT;
                break;
            case R.id.activity_main_contact_history:
                fragmentTag = CONTACT_HISTORY_FRAGMENT;
                break;
            case R.id.activity_main_contact_messages:
                fragmentTag = SMS_FRAGMENT;
                break;
            default:
                return;
        }

        if (!fragmentTag.equals("") && !isFragmentVisible(fragmentTag)) {
            setButtonDefaultColors();
            view.setSelected(true);
            ((Button)view).setTextColor(Color.BLACK);
            addFragment(fragmentTag);
        }
    }
}
