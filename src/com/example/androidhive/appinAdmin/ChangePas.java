package com.example.androidhive.appinAdmin;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidhive.R;
import com.example.androidhive.library.UserFunction;

public class ChangePas  extends Activity{
    TextView t1,t2,t3;
    EditText e1,e2;
	String a,b,c,email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepass);
		
		Bundle neb = getIntent().getExtras();
		a = neb.getString("name").toString();
		b=neb.getString("id").toString();
		c=neb.getString("bg");
		Log.e("email", a);
		email=b;
		Toast.makeText(getApplicationContext(), a, 500).show();
		t1=(TextView) findViewById(R.id.t1);
		t2=(TextView) findViewById(R.id.t2);
		t3=(TextView) findViewById(R.id.t3);
		e1 = (EditText) findViewById(R.id.e1);
		e2 = (EditText) findViewById(R.id.e2);
		t1.setText(b);
		t2.setText(b);
		t3.setText(c);
		
	}
	
public void changeit(View v)
{  
	
	JSONObject json=new JSONObject();
	UserFunction userFunction=new UserFunction();
	Log.e("email",email);
	Toast.makeText(getApplicationContext(),email,500).show();

	String s2 = e1.getText().toString();
	String s3 = e2.getText().toString();
	try
	{
	json = userFunction.Load_Change_Detail(email, s2, s3); 
	
	int success = json.getInt("success");
	 if (success == 1) 
	 {
		 Toast.makeText(getApplicationContext(), "Password updated Successfully", 500).show();
	 }
	 else
	 {
		 Toast.makeText(getApplicationContext(), "Password not changed sorry", 500).show();
		 
	 }

	}
	catch(Throwable ex)
	{
		
	}
	
}
}
