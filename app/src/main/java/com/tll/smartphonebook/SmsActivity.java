package com.tll.smartphonebook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tll.smartphonebook.constants.BaseConstants;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.models.Message;

import java.util.ArrayList;

/**
 * Created by abdullahtellioglu on 30/12/15.
 */
public class SmsActivity extends Activity {
    private ListView smsListView;
    private ArrayList<Message> listMessages;
    private ArrayAdapter<Message> messageArrayAdapter;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Bundle bundle = getIntent().getExtras();
        int contactId = bundle.getInt(BaseConstants.CONTACT_ID,-1);
        if(contactId==-1){
            finish();
        }
        smsListView = (ListView)findViewById(R.id.activity_sms_list_view);
        helper = new DatabaseHelper(getApplicationContext());
        listMessages = helper.getMessages();

    }



}
