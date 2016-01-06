package com.tll.smartphonebook.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.helpers.PhoneUtils;
import com.tll.smartphonebook.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdullahtellioglu on 29/12/15.
 */
public class ContactListAdapter extends ArrayAdapter<Contact> implements Filterable {
    private List<Contact> listContact;
    private Context context;

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Contact> FilteredArrayNames = new ArrayList<>();
                listContact = ((SmartPhoneApplication)(((Activity)context)).getApplication()).getContactList();
                constraint = constraint.toString().toLowerCase();
                for(int i =0;i<listContact.size();i++){
                    Contact c = listContact.get(i);
                    String name = c.getName()+" "+c.getSurname();
                    if(name.toLowerCase().contains(constraint)){
                        FilteredArrayNames.add(c);
                    }else{
                        if(c.getMobileNumber().contains(constraint)){
                            FilteredArrayNames.add(c);
                        }
                    }
                }
                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listContact = (List<Contact>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    @Override
    public Contact getItem(int position) {
        return listContact.get(position);
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    public ContactListAdapter(Context context,int resource,List<Contact> listContact) {
        super(context, resource,listContact);
        this.listContact = listContact;
        this.context = context;
    }
    private static class ViewHolder{
        private TextView txtName,txtNumber,txtId;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if(v==null){
            ViewHolder holder = new ViewHolder();
            v =  LayoutInflater.from(context).inflate(R.layout.row_contact,parent,false);
            holder.txtName = (TextView)v.findViewById(R.id.row_contact_name);
            holder.txtNumber = (TextView)v.findViewById(R.id.row_contact_number);
            holder.txtId = (TextView)v.findViewById(R.id.row_contact_hidden_id);
            v.setTag(holder);
        }
        ViewHolder holder = (ViewHolder)v.getTag();
        try{
            holder.txtName.setText(listContact.get(position).getName()+" "+listContact.get(position).getSurname());
            holder.txtNumber.setText(listContact.get(position).getMobileNumber());
            holder.txtId.setText(String.valueOf(listContact.get(position).getId()));
        }catch (Exception ex){

        }


        return v;
    }
}
