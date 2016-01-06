package com.tll.smartphonebook.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tll.smartphonebook.R;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.helpers.PhoneUtils;
import com.tll.smartphonebook.models.Contact;

/**
 * Created by abdullahtellioglu on 06/01/16.
 */
public class ContactInfoActivity extends Activity {
    public static final int INTENT_MODIFY_CONTACT = 616161;
    public static final String INTENT_CONTACT_ID = "contactid";
    private TextView textViewNameSurname;
    private View[] numbers,locations;
    private Contact contact;
    private Button back,modify,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_info);

        textViewNameSurname = (TextView)findViewById(R.id.contact_info_name_surname);
        back = (Button)findViewById(R.id.contact_info_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        modify = (Button)findViewById(R.id.contact_info_modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactInfoActivity.this,AddNewContact.class);
                intent.putExtra(INTENT_CONTACT_ID,contact.getId());
                startActivity(intent);
            }
        });
        delete = (Button)findViewById(R.id.contact_info_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ContactInfoActivity.this).setTitle(getString(R.string.question_delete_contact))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Kayidi sil
                                dialogInterface.dismiss();

                            }
                        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setCancelable(true).create().show();
            }
        });
        numbers = new View[3];
        numbers[0] = findViewById(R.id.contact_info_mobil_number);
        numbers[1] = findViewById(R.id.contact_info_home_number);
        numbers[2] = findViewById(R.id.contact_info_work_number);
        contact = new DatabaseHelper(getApplicationContext()).getContact(getIntent().getExtras().getInt(INTENT_CONTACT_ID));
        if(contact==null){
            finish();
        }else{
            textViewNameSurname.setText(contact.getName()+" "+contact.getSurname());
        }
        if(contact.getMobileNumber()==null || contact.getMobileNumber().equals("")){
            numbers[0].setVisibility(View.GONE);
        }else{
            setPhoneInfos(contact.getMobileNumber(), getString(R.string.mobile), numbers[0]);
        }
        if(contact.getHomeNumber()==null || contact.getHomeNumber().equals("")){
            numbers[1].setVisibility(View.GONE);

        }else{
            setPhoneInfos(contact.getHomeNumber(),getString(R.string.home),numbers[1]);
        }
        if(contact.getWorkNumber()==null || contact.getWorkNumber().equals("")){
            numbers[2].setVisibility(View.GONE);
        }else{
            setPhoneInfos(contact.getWorkNumber(),getString(R.string.work),numbers[2]);
        }
    }
    private void setPhoneInfos(String phoneNumber,String type,View view){
        TextView txtView = (TextView)view.findViewById(R.id.row_contact_number_number);
        TextView txtType = (TextView)view.findViewById(R.id.row_contact_number_type);
        View viewToClick = findViewById(R.id.row_contact_number_relative_left);
        viewToClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt = (TextView)view.findViewById(R.id.row_contact_number_number);
                PhoneUtils.call(ContactInfoActivity.this,txt.getText().toString());
            }
        });
        txtView.setText(phoneNumber);
        txtType.setText(type);

    }

}
