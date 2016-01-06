package com.tll.smartphonebook.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.tll.smartphonebook.R;
import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.activities.ContactInfoActivity;
import com.tll.smartphonebook.adapters.ContactListAdapter;
import com.tll.smartphonebook.models.Contact;

import java.util.List;

/**
 * Created by abdullahtellioglu on 29/12/15.
 */
public class ContactsFragment extends Fragment {
    private ListView listView;
    private List<Contact> listContacts;
    private ContactListAdapter listAdapter;
    private EditText searchText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list_view_template,container,false);
        listView = (ListView)v.findViewById(R.id.fragment_template_list_view);
        this.listContacts = ((SmartPhoneApplication)getActivity().getApplication()).getContactList();
        listAdapter = new ContactListAdapter(getActivity(),R.layout.row_contact,listContacts);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(),ContactInfoActivity.class);
                intent.putExtra("contactid",listAdapter.getItem(i).getId());
                startActivity(intent);
            }
        });
        searchText = (EditText)getActivity().findViewById(R.id.main_activity_tab_text_area);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            this.listAdapter.notifyDataSetChanged();
        }
    }
}
