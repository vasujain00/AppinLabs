package com.example.androidhive.appinAdmin;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidhive.R;
import com.example.androidhive.library.UserFunction;

public class Login  extends Activity{

	EditText e1,e2;
	Button b1,b2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		e1=(EditText) findViewById(R.id.ett2);
		e2=(EditText) findViewById(R.id.p1);
		b1=(Button) findViewById(R.id.bu1);
		b2=(Button) findViewById(R.id.bu2);
		setId();
	}
	
	public void setId()
	{
		Set_Data ab = new Set_Data(getApplicationContext());
		String id = ab.getAllValues(0);
		Log.e("getid",id);
		if(id == " ")
		{
			ab.addValues();
			
		}
		else
		{
			
		}	
	}
	public void login(View v)
	{
       //Toast.makeText(getApplicationContext(), "Login", 500).show();
		
		JSONObject json=new JSONObject();
		UserFunction userFunction=new UserFunction();
	/*	
	if(e1.getText().toString().matches("a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")==false||e2.getText().toString().length()<6){
			
			if(e1.getText().toString().matches("a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")==false)
			{
				e1.setError("invalid id");
			}
			
			if(e2.getText().toString().length()<6)
			{
				e2.setError("password too short and wrong");
			}
		}
		else {
			*/
			
		
		String s1=e1.getText().toString();
		String s2= e2.getText().toString();
		//Toast.makeText(getApplicationContext(), s1, 500).show();
		
		try {
			//Toast.makeText(getApplicationContext(), s2, 500).show();
			
			json = userFunction.Load_Login_Detail(s1,s2);   
			//Toast.makeText(getApplicationContext(),json.toString(),500).show();
			int success = json.getInt("success");
			 if (success == 1) 
			 {
				 JSONObject ab = json.getJSONObject("user");
				 Log.e("error",ab.toString());
				 String name = ab.getString("name").toString();
				 String bg = ab.getString("bg").toString();
				 String id = ab.getString("id").toString();
				 String photo = ab.getString("photo").toString();
				 Abcd.setName(name);
				 Abcd.setId(id);
				 Abcd.setBg(bg);
				 Abcd.setPhoto(photo);
				 Set_Data abb = new Set_Data(getApplicationContext());
				  int a = abb.Update("1", name, id, bg, "1",photo);
				 Toast.makeText(getApplicationContext(), name+"\n"+id+"\n"+"\n"+bg, 500).show();
				 Bundle pi = new Bundle();
				 pi.putString("image", photo);
				   Intent intent=new Intent(getApplicationContext(),Welcome.class);
				   intent.putExtras(pi);
				   startActivity(intent);
				   Toast.makeText(getApplicationContext(), "Login Successfully", 3000).show();	
			 }
			 else {
				 
				 Toast.makeText(getApplicationContext(), "Sorry wrong password",500).show();
			 }
		}
		catch(Throwable ex)
		{
			
		}
		}
	//}

	public void signup(View v)
	{
		Intent ab = new Intent(getApplicationContext(),MainClass.class);
		startActivity(ab);
	}
}
