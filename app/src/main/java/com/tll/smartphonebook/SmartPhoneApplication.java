package com.tll.smartphonebook;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.exceptions.DialogException;
import com.tll.smartphonebook.helpers.ContactComparator;
import com.tll.smartphonebook.models.Contact;
import com.tll.smartphonebook.models.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by abdullahtellioglu on 27/12/15.
 */
public class SmartPhoneApplication extends Application {
    private DatabaseHelper databaseHelper=null;
    private List<Contact> contactList;
    private String myPhoneNumber = null;
    public String getMyPhoneNumber(){
        if(myPhoneNumber==null){
            TelephonyManager tMgr =(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();
            myPhoneNumber = mPhoneNumber;
        }
        return myPhoneNumber;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(getApplicationContext());
            contactList = databaseHelper.getContacts();
            Collections.sort(this.contactList,new ContactComparator());
        }
    }
    public List<Contact> getContactList(){
        if(contactList.size()==0){
            contactList = null;
            contactList = databaseHelper.getContacts();
        }
        return contactList;
    }
    public void addContact(Contact contact) throws DialogException{
        databaseHelper.addContact(contact);
        this.contactList.add(contact);
        Collections.sort(this.contactList,new ContactComparator());
    }
}
