package com.tll.smartphonebook.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.helpers.PhoneUtils;
import com.tll.smartphonebook.models.Contact;

import java.util.List;

/**
 * Created by abdullahtellioglu on 12/12/15.
 */
public class CallFragment extends Fragment implements View.OnClickListener {
    private String numberToCall = "";
    private Contact hintContact;
    private TextView phoneNumberTextView,phoneNumberHint;
    private Button btnCall1,btnCall2,btnCall3,btnCall4,btnCall5,btnCall6,btnCall7,btnCall8,btnCall9,btnCallstar,btnCall0,btnCallD;
    private Button btnCallNumber,btnCallRemoveNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_call,container,false);
        btnCall1 = (Button)v.findViewById(R.id.fragment_call_number_1);
        btnCall2 = (Button)v.findViewById(R.id.fragment_call_number_2);
        btnCall3 = (Button)v.findViewById(R.id.fragment_call_number_3);
        btnCall4 = (Button)v.findViewById(R.id.fragment_call_number_4);
        btnCall5 = (Button)v.findViewById(R.id.fragment_call_number_5);
        btnCall6 = (Button)v.findViewById(R.id.fragment_call_number_6);
        btnCall7 = (Button)v.findViewById(R.id.fragment_call_number_7);
        btnCall8 = (Button)v.findViewById(R.id.fragment_call_number_8);
        btnCall9 = (Button)v.findViewById(R.id.fragment_call_number_9);
        btnCall0 = (Button)v.findViewById(R.id.fragment_call_number_0);
        btnCallNumber = (Button)v.findViewById(R.id.fragment_call_call_number);
        btnCallstar = (Button)v.findViewById(R.id.fragment_call_number_star);
        btnCallD = (Button)v.findViewById(R.id.fragment_call_number_dies);
        btnCallRemoveNumber = (Button)v.findViewById(R.id.fragment_call_remove_number);
        phoneNumberTextView = (TextView)v.findViewById(R.id.fragment_call_number_area_text_view);
        phoneNumberHint = (TextView)v.findViewById(R.id.fragment_call_number_hint);
        btnCall0.setOnClickListener(this);
        btnCall1.setOnClickListener(this);
        btnCall2.setOnClickListener(this);
        btnCall3.setOnClickListener(this);
        btnCall4.setOnClickListener(this);
        btnCall5.setOnClickListener(this);
        btnCall6.setOnClickListener(this);
        btnCall7.setOnClickListener(this);
        btnCall8.setOnClickListener(this);
        btnCall9.setOnClickListener(this);
        btnCall0.setOnClickListener(this);
        btnCallstar.setOnClickListener(this);
        btnCallD.setOnClickListener(this);
        btnCallRemoveNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberToCall.length()!=0)
                    numberToCall = numberToCall.substring(0,numberToCall.length()-1);
                updatePhoneNumberTextView();
                setHint();

            }
        });
        btnCallNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callNumber();
            }
        });
        phoneNumberHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hintContact!=null){
                    phoneNumberTextView.setText(hintContact.getMobileNumber());
                    numberToCall = hintContact.getMobileNumber();
                }else{
                    phoneNumberHint.setText("");
                }
            }
        });
        return v;
    }
    private void updatePhoneNumberTextView(){
        if(Build.VERSION.SDK_INT>21) {
            phoneNumberTextView.setText(PhoneNumberUtils.formatNumber(numberToCall, "tr"));
        }
        else{
            phoneNumberTextView.setText(numberToCall);
        }
    }
    private void callNumber(){
        PhoneUtils.call(getActivity(),numberToCall);
    }
    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        numberToCall+=btn.getText();
        updatePhoneNumberTextView();
        setHint();
    }
    private void setHint(){
        Contact contact = getHint(numberToCall);
        if(contact!=null){
            phoneNumberHint.setText(contact.getName()+" "+contact.getSurname());
        }else{
            phoneNumberHint.setText("");
        }
    }
    private Contact getHint(String number){
        List<Contact> list = ((SmartPhoneApplication)getActivity().getApplication()).getContactList();
        for(int i =0;i<list.size();i++){
            Contact contact = list.get(i);
            String str = new String(contact.getMobileNumber());
            str = str.replaceAll(" ","");
            if(str.contains(number)){
                hintContact = contact;
                return contact;
            }

        }
        return null;
    }

}
