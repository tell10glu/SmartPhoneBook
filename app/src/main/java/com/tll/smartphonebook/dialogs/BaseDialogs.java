package com.tll.smartphonebook.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.tll.smartphonebook.R;
import com.tll.smartphonebook.SmartPhoneApplication;
import com.tll.smartphonebook.constants.BaseConstants;
import com.tll.smartphonebook.database.DatabaseHelper;
import com.tll.smartphonebook.helpers.SharedPreferenceUtils;
import com.tll.smartphonebook.models.Contact;

public class BaseDialogs {
    public static Dialog getContactExportDialog(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setCancelable(true)
                .setTitle(activity.getString(R.string.export_contacts))
                .setCancelable(true)
                .setPositiveButton(R.string.export, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferenceUtils.saveBoolean(activity, BaseConstants.IS_APP_STARTED_FIRST,true);
                                ContentResolver cr = activity.getContentResolver();
                                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                                        null, null, null, null);
                                if (cur.getCount() > 0) {
                                    while (cur.moveToNext()) {
                                        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                        if (Integer.parseInt(cur.getString(
                                                cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                                            Cursor pCur = cr.query(
                                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                    null,
                                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                                    new String[]{id}, null);
                                            if (pCur.moveToNext()) {
                                                String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                                String[] splitted = name.split(" ");
                                                try {
                                                    ((SmartPhoneApplication)activity.getApplication()).addContact(new Contact(splitted[0], splitted[1], "", "", phoneNo, ""));
                                                } catch (Exception ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                            pCur.close();
                                        }
                                    }
                                }
                            }
                        }
                )
                            .

                    setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    SharedPreferenceUtils.saveBoolean(activity, BaseConstants.IS_APP_STARTED_FIRST,true);
                                }
                            }

                    );

                    return builder.create();
                }
    }
