package com.tll.smartphonebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.helpers.NumberUtils;
import com.tll.smartphonebook.models.Message;

import java.util.List;

/**
 * Created by abdullahtellioglu on 30/12/15.
 */
public class SmsListAdapter extends ArrayAdapter<Message> {
    private Context context;
    private List<Message> objects;
    public SmsListAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }


    private static class ViewHolder{
        private TextView smsFrom,smsTo;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if(v==null){
            v = LayoutInflater.from(context).inflate(R.layout.row_sms,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.smsFrom = (TextView)v.findViewById(R.id.row_sms_from);
            holder.smsTo = (TextView)v.findViewById(R.id.row_sms_to);
            v.setTag(holder);
        }
        ViewHolder holder =(ViewHolder) v.getTag();
        String myPhoneNumber = NumberUtils.clearPhoneNumber(((SmartPhoneApplication) context.getApplicationContext()).getMyPhoneNumber());
        // bu kısım eksik
       // holder.smsFrom.setText();


        return v;
    }
}
