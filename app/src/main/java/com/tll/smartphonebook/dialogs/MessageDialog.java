package com.tll.smartphonebook.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.models.Contact;

/**
 * Created by abdullahtellioglu on 06/01/16.
 */
public class MessageDialog extends Dialog implements View.OnClickListener {
    private EditText editText;
    private Button sendButton;
    private TextView txtView;
    private String phoneNumber;
    DatabaseHelper helper ;
    public MessageDialog(Context context,String phoneNumber) {
        super(context);
        setContentView(R.layout.dialog_message);
        editText = (EditText)findViewById(R.id.dialog_message_edit_text);
        txtView = (TextView)findViewById(R.id.dialog_message_text);
        sendButton = (Button)findViewById(R.id.dialog_message_send_button);
        sendButton.setOnClickListener(this);
        helper = new DatabaseHelper(context);
        Contact contact = helper.getContact(phoneNumber);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.phoneNumber = phoneNumber;
        if(contact==null)
            this.setTitle(phoneNumber);
        else
            this.setTitle(contact.getName()+" "+contact.getSurname());
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                txtView.setText(editText.getText());
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null,txtView.getText().toString(), null, null);
    }
}
