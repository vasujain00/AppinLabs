package com.example.androidhive.appinAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Set_Data extends SQLiteOpenHelper {
	
	ArrayList<HashMap<String, String>> CityList,List;
    
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vrj";
	
	private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BLOOD = "blood";
    private static final String KEY_LOG = "log";
    private static final String KEY_PHOTO ="photo";
    
	private SQLiteDatabase ourDataBase;
	private static final String TABLE_DATA = "data";
	private static final String TABLE_USERS = "users";

	
	public Set_Data(Context context) 
	{
	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_DATA + "("
				 + KEY_ID + " TEXT ," 
				 + KEY_NAME  + " TEXT," + KEY_EMAIL + " TEXT," + KEY_BLOOD + " TEXT,"+ 
				 KEY_LOG +" TEXT, "+KEY_PHOTO + " TEXT "+")";
		
		String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +KEY_ID + " TEXT ," 
			      + KEY_NAME  + " TEXT," + KEY_EMAIL + " TEXT," + 
			         KEY_PHOTO + " TEXT "+")";
		

		  db.execSQL(CREATE_LOGIN_TABLE);
		  Log.e(CREATE_LOGIN_TABLE, TABLE_DATA);
		  db.execSQL(CREATE_DATA_TABLE);
		  Log.e(CREATE_DATA_TABLE,TABLE_USERS);
		 
		
		  
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	}
	
	public void addValues()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id","1");
		values.put("name","");
		values.put("email","");
		values.put("blood", "");
		values.put("log","");
		values.put("photo","");
	db.insert(TABLE_DATA, null, values);
		Log.e("val",""+db.insert(TABLE_DATA, null, values));
		
		db.close();
		
		
		
	}
	
	public int Update(String id,String name,String email,String bg,String log,String photo)
	{
		
		SQLiteDatabase obj1 = this.getWritableDatabase();
		ContentValues obj2 = new ContentValues();
		obj2.put("name",name);
		obj2.put("email",email);
		obj2.put("id", id);
		obj2.put("blood", bg);
		obj2.put("log",log);
		obj2.put("photo",photo);
		
		
		int i = obj1.update(TABLE_DATA, obj2,"id="+id,null);
		Log.e(""+name+email+bg+id+log,"sss"+i);
		obj1.close();
		return i;
		
		
	}
	public String getAllValues(int row) 
	{   String val=" ";
		try
		{
		String selectQuery = "SELECT  * FROM " + TABLE_DATA;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
				 val=cursor.getString(row);
			cursor.close();
			return val;
		}db.close();	
		
		}catch (Throwable e) {
			// TODO: handle exception
		}
		return val;
		}
	public void addValues1(String id,String name,String email,String photo)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id",id);
		values.put("name",name);
		values.put("email",email);
		values.put("photo",photo);
	db.insert(TABLE_USERS, null, values);
		Log.e("val",""+db.insert(TABLE_USERS, null, values));
		
		db.close();
		
		
		
	}
	public String getLastId() 
	{   String City_id=" ";
		try
		{
		String selectQuery = "SELECT  * FROM " + TABLE_USERS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
			do 
			{
				 City_id=cursor.getString(0);
			 } while (cursor.moveToNext());
			cursor.close();
		}db.close();	
		
		}catch (Throwable e) {
			// TODO: handle exception
		}
		return City_id;
		}
	
	public List<Contact> getAllContacts() {

		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT * FROM users";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
		do 
		{
		Contact contact = new Contact();
		contact.setId(cursor.getString(0));
		contact.setName(cursor.getString(1));
		contact.setPhoto(cursor.getString(3));
		// Adding contact to list
		contactList.add(contact);
		} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		return contactList;

		}


}



