package com.example.androidhive.appinAdmin;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidhive.R;
import com.example.androidhive.library.UserFunction;

public class MainClass extends Activity implements OnClickListener, OnItemSelectedListener {
    String ubg;
	EditText e1,et2,ep2;
	Button b1;
	Spinner s;
	String bg[] = {"A+","B+","A-","B-","O+","O-"};
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.send);
		e1=(EditText)findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.et2);
		b1=(Button)findViewById(R.id.button1);
	    ep2=(EditText)findViewById(R.id.ep2);
		b1.setOnClickListener(this);
		s=(Spinner) findViewById(R.id.sp1);
		s.setOnItemSelectedListener(this);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bg);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(aa);
	}
	
	@Override
	public void onClick(View arg0) 
	{
		JSONObject json=new JSONObject();
		UserFunction userFunction=new UserFunction();
		String email = et2.getText().toString().trim();
		if(e1.getText().toString()=="" || e1.getText().length()==0||e1.getText().toString().matches("[a-zA-Z][a-zA-Z]*")==false
				||email.matches( "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")== false && email.length()>0||ep2.getText().toString()==""||ep2.getText().length()==0 ||
				ep2.getText().length()<=6)
		{
		if(e1.getText().toString()=="" || e1.getText().length()==0)
		{
			e1.setError("please fill your name ");
		}
			if(e1.getText().toString().matches("[a-zA-Z][a-zA-Z]*")==false)
			{
				e1.setError("invalid name");
			}
			if(email.matches( "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")== false && email.length()>0)
			{
				et2.setError("please enter a valid email");
			}
			if(ep2.getText().toString()==""||ep2.getText().length()==0  )
			{
				ep2.setError("please enter password first");
			}
			if( ep2.getText().length()<=6)
			{
				ep2.setError("too short password");
			}
		
		}
		
		
		//Boolean b = e1.getText().toString().matches("[a-zA-Z][a-zA-Z]*");
		//Toast.makeText(getApplicationContext(),"ans-"+b,500).show();
		
		
		
		
		
		else
		{
		String s1=e1.getText().toString();
		String s2 = et2.getText().toString();
		String s3=ep2.getText().toString();
		String s4 =ubg;
		try
		{
		json = userFunction.Load_Detail(s1,s2,s3,s4);   
		
		int success = json.getInt("success");
		 if (success == 1) 
		 {
			 Bundle ab = new Bundle();
			 ab.putString("email",s2);
		   Intent intent=new Intent(getApplicationContext(),Addphoto.class);
		   intent.putExtras(ab);
		   startActivity(intent);
		   Toast.makeText(getApplicationContext(), "Your data submittd", 3000).show();	
		 }
		 else {
			 Toast.makeText(getApplicationContext(), "id already Exist", 3000).show();
			 
		 }
		}catch(Throwable e)
		{
			
		}
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
	 ubg = bg[arg2];
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
