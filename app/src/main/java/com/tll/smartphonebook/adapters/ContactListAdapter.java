package com.tll.smartphonebook.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.helpers.PhoneUtils;
import com.tll.smartphonebook.models.Contact;

import java.util.List;

/**
 * Created by abdullahtellioglu on 29/12/15.
 */
public class ContactListAdapter extends ArrayAdapter<Contact> {
    private List<Contact> listContact;
    private Context context;
    private int lastPosition;
    public ContactListAdapter(Context context,int resource,List<Contact> listContact) {
        super(context, resource,listContact);
        this.listContact = listContact;
        this.context = context;
    }
    private static class ViewHolder{
        private TextView txtName,txtNumber;
        private ImageButton imageCallButton;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if(v==null){
            ViewHolder holder = new ViewHolder();
            v =  LayoutInflater.from(context).inflate(R.layout.row_contact,parent,false);
            holder.txtName = (TextView)v.findViewById(R.id.row_contact_name);
            holder.txtNumber = (TextView)v.findViewById(R.id.row_contact_number);
            holder.imageCallButton = (ImageButton)v.findViewById(R.id.row_contact_call);
            v.setTag(holder);
        }
        lastPosition = position;
        ViewHolder holder = (ViewHolder)v.getTag();
        holder.txtName.setText(listContact.get(position).getName()+" "+listContact.get(position).getSurname());
        holder.txtNumber.setText(listContact.get(position).getMobileNumber());
        holder.imageCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUtils.call((Activity)context,listContact.get(lastPosition).getMobileNumber());
            }
        });

        return v;
    }
}
