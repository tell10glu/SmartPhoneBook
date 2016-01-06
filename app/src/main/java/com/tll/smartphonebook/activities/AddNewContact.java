package com.tll.smartphonebook.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.exceptions.DialogException;
import com.tll.smartphonebook.models.Contact;

/**
 * Created by abdullahtellioglu on 06/01/16.
 */
public class AddNewContact extends Activity {
    private EditText name,surname,homeNumber,mobileNumber,workNumber,email;
    private Button homeLocation,workLocation,addContact;
    private Contact contact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        name = (EditText)findViewById(R.id.activity_add_new_contact_name);
        surname = (EditText)findViewById(R.id.activity_add_new_contact_surname);
        homeNumber = (EditText)findViewById(R.id.activity_add_new_contact_home_number);
        mobileNumber = (EditText)findViewById(R.id.activity_add_new_contact_mobile_number);
        workNumber = (EditText)findViewById(R.id.activity_add_new_contact_work_number);
        homeLocation = (Button)findViewById(R.id.activity_add_new_contact_add_home_location);
        workLocation = (Button)findViewById(R.id.activity_add_new_contact_add_work_location);
        email = (EditText)findViewById(R.id.activity_add_new_contact_email);
        addContact = (Button)findViewById(R.id.activity_add_new_contact_add_button);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contact==null) {
                    contact = new Contact(
                            name.getText().toString(),
                            surname.getText().toString(),
                            homeNumber.getText().toString(),
                            workNumber.getText().toString(),
                            mobileNumber.getText().toString(),
                            email.getText().toString()
                    );
                    try {
                        ((SmartPhoneApplication) getApplication()).addContact(contact);
                        setResult(RESULT_OK);
                        finish();
                    } catch (DialogException ex) {
                        ex.showDialog(AddNewContact.this);
                    }
                } else{
                    try {
                        ((SmartPhoneApplication) getApplication()).updateContact(contact);
                        setResult(RESULT_OK);
                        finish();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }


            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle.getInt("contactid",-1)!=-1){
            contact = new DatabaseHelper(getApplicationContext()).getContact(bundle.getInt("contactid"));
            name.setText(contact.getName());
            surname.setText(contact.getSurname());
            mobileNumber.setText(contact.getMobileNumber());
            homeNumber.setText(contact.getHomeNumber());
            workNumber.setText(contact.getWorkNumber());
            email.setText(contact.getEmail());
            addContact.setText(getString(R.string.update));
        }
    }
}
