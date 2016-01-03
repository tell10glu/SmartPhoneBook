package com.tll.smartphonebook.helpers;

import com.tll.smartphonebook.models.Contact;

import java.util.Comparator;

/**
 * Created by abdullahtellioglu on 30/12/15.
 */
public class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact contact, Contact t1) {
        String c1FullName = contact.getName()+" "+contact.getSurname();
        String c2FullName = t1.getName()+" "+t1.getSurname();
        int res = String.CASE_INSENSITIVE_ORDER.compare(c1FullName, c2FullName);
        if (res == 0) {
            res = c1FullName.compareTo(c2FullName);
        }
        return res;
    }
}
