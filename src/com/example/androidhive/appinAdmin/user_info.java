package com.example.androidhive.appinAdmin;

import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidhive.R;
import com.example.androidhive.library.UserFunction;

public class user_info extends Activity implements OnClickListener {
	TextView t1;
	Button submit,back;private ProgressDialog pDialog;


JSONObject json;UserFunction userFunction;
	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.info);
	    initalize();
	}

	private void initalize()
	{
		userFunction=new UserFunction();
		t1=(TextView)findViewById(R.id.textView1);
		submit=(Button)findViewById(R.id.btn_exit);
		submit.setOnClickListener(this);
		back=(Button)findViewById(R.id.btn_back);
		back.setOnClickListener(this);
		Bundle getUnique=getIntent().getExtras();
        id=getUnique.getString("name");
	    t1.setText("Name  :  "+getUnique.getString("name")+"\n"+"Number  : "+getUnique.getString("no")+"\n"+"Email Id : "+getUnique.getString("eid")+"\n"+"Course"+getUnique.getString("cid"));
	}

	@Override
	public void onClick(View arg0) {
	Intent intent;
		switch (arg0.getId()) 
	   {
	
		case R.id.btn_back:
			intent=new Intent(getApplicationContext(),admin_list.class);
			finish();
			startActivity(intent);
			break;
		case R.id.btn_exit:
			finish();
			break;
		}
	}

	
}