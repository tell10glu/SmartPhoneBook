package com.tll.smartphonebook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tll.smartphonebook.exceptions.DialogException;
import com.tll.smartphonebook.helpers.DateUtils;
import com.tll.smartphonebook.models.Contact;
import com.tll.smartphonebook.models.ContactCalls;
import com.tll.smartphonebook.models.Message;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SMART_PHONE_BOOK";
    private static final int DB_VERSION = 1;
    //Tables
    private static final String TABLE_CONTACT = "Contacts";
    private static final String TABLE_LOCATIONS = "Locations";
    private static final String TABLE_LOCATION_CONTACT = "LocationsContacts";
    private static final String TABLE_CONTACT_HISTORY = "ContactHistory";
    private static final String TABLE_MESSAGE = "Messages";
    //Columns
    private static final String CONTACT_ID = "id";
    private static final String CONTACT_NAME = "name";
    private static final String CONTACT_SURNAME = "surname";
    private static final String CONTACT_HOMENUMBER = "homeNumber";
    private static final String CONTACT_WORKNUMBER = "workNumber";
    private static final String CONTACT_MOBILENUMBER = "mobileNumber";
    private static final String CONTACT_EMAIL = "email";


    private static final String CONTACT_HISTORY_ID = "id";
    private static final String CONTACT_HISTORY_CONTACT_NUMBER = "number";
    private static final String CONTACT_HISTORY_DURATION = "duration";
    private static final String CONTACT_HISTORY_DATE = "date";
    private static final String CONTACT_HISTORY_IS_ISMISSING = "isMissing";


    private static final String MESSAGE_ID = "id";
    private static final String MESSAGE_FROM = "from";
    private static final String MESSAGE_TO = "to";
    private static final String MESSAGE_BODY = "body";
    private static final String MESSAGE_DATE = "DATE";

    private static final String CREATE_TABLE_CONTACT = "Create table if not exists "+TABLE_CONTACT+"" +
            "("+CONTACT_ID+" Integer PRIMARY KEY ," +
            ""+CONTACT_NAME+" TEXT , " +
            ""+CONTACT_SURNAME+" TEXT ," +
            ""+CONTACT_HOMENUMBER+" TEXT," +
            ""+CONTACT_WORKNUMBER+" TEXT," +
            ""+CONTACT_MOBILENUMBER+" mobileNumber TEXT," +
            ""+CONTACT_EMAIL+" TEXT)";
    private static final String CREATE_TABLE_CONTACT_HISTORY = "Create table if not exists "+ TABLE_CONTACT_HISTORY+""+
            "( "+CONTACT_HISTORY_ID+" Integer PRIMARY KEY , "+
            ""+CONTACT_HISTORY_CONTACT_NUMBER+" text ,"+
            ""+CONTACT_HISTORY_DURATION+" Integer ,"+
            ""+CONTACT_HISTORY_DATE+" text ,"+
            ""+CONTACT_HISTORY_IS_ISMISSING+" integer )";
    private static final String CREATE_TABLE_MESSAGES = " Create table if not exists "+TABLE_MESSAGE +
            "( "+MESSAGE_ID+" Integer PRIMARY KEY ,"+
            ""+MESSAGE_FROM+" text ,"+
            ""+MESSAGE_TO+" text ,"+
            ""+MESSAGE_DATE+" text ,"+
            ""+MESSAGE_BODY+" text )";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
        db.execSQL(CREATE_TABLE_CONTACT_HISTORY);
        db.execSQL(CREATE_TABLE_MESSAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact) throws DialogException{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME,contact.getName());
        values.put(CONTACT_SURNAME,contact.getSurname());
        values.put(CONTACT_HOMENUMBER,contact.getHomeNumber());
        values.put(CONTACT_MOBILENUMBER,contact.getMobileNumber());
        values.put(CONTACT_WORKNUMBER,contact.getWorkNumber());
        values.put(CONTACT_EMAIL, contact.getEmail());
        long rowId = db.insert(TABLE_CONTACT, null, values);
        db.close();
        if(rowId ==-1)
            throw new DialogException();
        contact.setId((int) rowId);
    }
    public void removeContact(Contact contact){
        String query = "Delete from '"+TABLE_CONTACT + "' where "+CONTACT_ID+" = "+contact.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public Contact getContact(int contactId){
        Contact contact =null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Contacts where "+CONTACT_ID+" = "+contactId;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToNext()){
            contact  =new Contact(
                    cursor.getInt(cursor.getColumnIndex(CONTACT_ID)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_NAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_SURNAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_HOMENUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_WORKNUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_MOBILENUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_EMAIL))
            );

        }
        db.close();
        return contact;
    }
    public Contact getContact(String number){
        Contact contact =null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Contacts where  TRIM("+CONTACT_MOBILENUMBER+") = '"+number.trim()+"'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToNext()){
            contact  =new Contact(
                    cursor.getInt(cursor.getColumnIndex(CONTACT_ID)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_NAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_SURNAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_HOMENUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_WORKNUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_MOBILENUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_EMAIL))
            );

        }
        db.close();
        return contact;
    }
    public List<Contact> getContacts(){
        ArrayList<Contact> listContacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Contacts";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){

            Contact contact  =new Contact(
                    cursor.getInt(cursor.getColumnIndex(CONTACT_ID)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_NAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_SURNAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_HOMENUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_WORKNUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_MOBILENUMBER)),
                    cursor.getString(cursor.getColumnIndex(CONTACT_EMAIL))
                    );
            listContacts.add(contact);
        }
        db.close();
        return listContacts;
    }
    public void updateContact(Contact contact){

    }
    public void addContactCalls(ContactCalls contactCalls){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_HISTORY_CONTACT_NUMBER, contactCalls.getNumber());
        values.put(CONTACT_HISTORY_DURATION,contactCalls.getDuration());
        values.put(CONTACT_HISTORY_IS_ISMISSING,contactCalls.isMissing());
        values.put(CONTACT_HISTORY_DATE, DateUtils.getFullDate(contactCalls.getCallingDate()));
        db.insert(TABLE_CONTACT_HISTORY, null, values);
        db.close();
    }
    public ContactCalls getContactCall(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContactCalls calls = null;
        String query = "Select * from "+TABLE_CONTACT_HISTORY+" where "+CONTACT_HISTORY_ID+" = "+id;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            calls = new ContactCalls(cursor.getString(cursor.getColumnIndex(CONTACT_HISTORY_CONTACT_NUMBER))
                    ,cursor.getLong(cursor.getColumnIndex(CONTACT_HISTORY_DURATION))
                    ,DateUtils.getDateFromFullDateString(cursor.getString(cursor.getColumnIndex(CONTACT_HISTORY_DATE)))
                    ,(cursor.getInt(cursor.getColumnIndex(CONTACT_HISTORY_IS_ISMISSING)))==1 ? true : false);
        }

        return calls;
    }
    public ArrayList<ContactCalls> getContactCalls(int contactId){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ContactCalls> list = new ArrayList<>();
        String query = "Select * from "+TABLE_CONTACT_HISTORY+" where "+CONTACT_HISTORY_CONTACT_NUMBER+" = "+contactId;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToFirst()){
            ContactCalls calls = new ContactCalls(cursor.getString(cursor.getColumnIndex(CONTACT_HISTORY_CONTACT_NUMBER))
                    ,cursor.getLong(cursor.getColumnIndex(CONTACT_HISTORY_DURATION))
                    ,DateUtils.getDateFromFullDateString(cursor.getString(cursor.getColumnIndex(CONTACT_HISTORY_DATE)))
                    ,(cursor.getInt(cursor.getColumnIndex(CONTACT_HISTORY_IS_ISMISSING)))==1 ? true : false);
            list.add(calls);
        }
        return list;
    }
    public ArrayList<ContactCalls> getContactCalls(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ContactCalls> list = new ArrayList<>();
        String query = "Select * from "+TABLE_CONTACT_HISTORY;
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            ContactCalls calls = new ContactCalls(cursor.getString(cursor.getColumnIndex(CONTACT_HISTORY_CONTACT_NUMBER))
                    ,cursor.getLong(cursor.getColumnIndex(CONTACT_HISTORY_DURATION))
                    ,DateUtils.getDateFromFullDateString(cursor.getString(cursor.getColumnIndex(CONTACT_HISTORY_DATE)))
                    ,(cursor.getInt(cursor.getColumnIndex(CONTACT_HISTORY_IS_ISMISSING)))==1 ? true : false);
            list.add(calls);
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addMessage(Message message) throws DialogException{
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MESSAGE_TO,message.getTo());
        values.put(MESSAGE_FROM,message.getFrom());
        values.put(MESSAGE_BODY,message.getBody());
        values.put(MESSAGE_DATE,message.getDate());
        long rowId = db.insert(TABLE_MESSAGE, null, values);
        db.close();
        if(rowId==-1)
            throw new DialogException();
        message.setId((int) rowId);
    }
    public ArrayList<Message> getMessages(){
        ArrayList<Message> list = new ArrayList<>();
        String query = "Select * from "+TABLE_MESSAGE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            Message message = new Message(cursor.getString(cursor.getColumnIndex(MESSAGE_FROM)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_TO)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_BODY)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_DATE)));
            message.setId(cursor.getInt(cursor.getColumnIndex(MESSAGE_ID)));
            list.add(message);

        }
        return list;
    }
    public ArrayList<Message> getMessages(String to){
        ArrayList<Message> list = new ArrayList<>();
        String query = "Select * from "+TABLE_MESSAGE+" where "+MESSAGE_TO+" = "+ to;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            Message message = new Message(cursor.getString(cursor.getColumnIndex(MESSAGE_FROM)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_TO)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_BODY)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_DATE)));
            message.setId(cursor.getInt(cursor.getColumnIndex(MESSAGE_ID)));
            list.add(message);

        }
        return list;
    }

}
