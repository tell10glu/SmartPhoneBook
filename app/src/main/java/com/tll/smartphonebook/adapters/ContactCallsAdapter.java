package com.tll.smartphonebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.models.ContactCalls;

import java.util.List;

/**
 * Created by abdullahtellioglu on 30/12/15.
 */
public class ContactCallsAdapter  extends ArrayAdapter<ContactCalls>{
    private List<ContactCalls> contactCallsList;
    private Context context;
    public ContactCallsAdapter(Context context, int resource, List<ContactCalls> objects) {
        super(context, resource, objects);
        this.context = context;
        this.contactCallsList =objects;
    }
    private static class ViewHolder{
        TextView number,date;
        ImageButton callButton;
        ImageView img;
    }
    @Override
    public View getView(int i, View v, ViewGroup parent) {
        if(v==null){
            v = LayoutInflater.from(context).inflate(R.layout.row_contact_call_history,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.number = (TextView)v.findViewById(R.id.row_contact_call_history_name_number);
            holder.date = (TextView)v.findViewById(R.id.row_contact_call_history_date);
            holder.callButton = (ImageButton)v.findViewById(R.id.row_contact_call_history_call_image_button);
            holder.img = (ImageView)v.findViewById(R.id.row_contact_call_history_image);
            v.setTag(holder);
        }
        ViewHolder holder =(ViewHolder) v.getTag();
        

        return v;
    }
}
