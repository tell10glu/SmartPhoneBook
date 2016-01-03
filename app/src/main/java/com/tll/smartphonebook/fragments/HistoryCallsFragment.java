package com.tll.smartphonebook.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.adapters.ContactCallsAdapter;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.models.ContactCalls;

import java.util.ArrayList;

/**
 * Created by abdullahtellioglu on 29/12/15.
 */
public class HistoryCallsFragment extends Fragment {
    private ListView listView;
    private ArrayList<ContactCalls> contactCalls;
    private ContactCallsAdapter contactCallsArrayAdapter;
    private DatabaseHelper helper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_history_calls,container,false);
        listView = (ListView)v.findViewById(R.id.fragment_history_calls_list_view);
        helper = new DatabaseHelper(getActivity().getApplicationContext());
        contactCalls = helper.getContactCalls();
        contactCallsArrayAdapter = new ContactCallsAdapter(getActivity(),R.layout.row_contact_call_history,contactCalls);
        listView.setAdapter(contactCallsArrayAdapter);
        return v;
    }

}
