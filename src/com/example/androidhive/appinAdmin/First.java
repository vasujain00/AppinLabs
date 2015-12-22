package com.example.androidhive.appinAdmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidhive.R;

public class First extends Activity{
String log;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		setLogin();
		Log.e("mash","mash");
	}
	public void setLogin(){
		
		Set_Data ab = new Set_Data(getApplicationContext());
		log = ab.getAllValues(4);
		Log.e("logvalue",log);
				if(log.equals("1"))
				{
					Intent neb = new Intent(getApplicationContext(),Welcome.class);
					startActivity(neb);
				}
				else
				{
					Intent neb = new Intent(getApplicationContext(),Login.class);
					startActivity(neb);
					Log.e("ll",log);
				}

		
		
		
	}
	

}
