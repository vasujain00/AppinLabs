package com.example.androidhive.appinAdmin;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidhive.R;
import com.example.androidhive.library.UserFunction;


public class admin_list extends Activity implements OnClickListener{
ListView list;
Button more,exit;
ImageView reloading;
int i=0;
ArrayList<HashMap<String, String>> CityList;
TextView statename;
ArrayAdapter<String> dataAdapter;
private ProgressDialog pDialog;
JSONObject json;UserFunction userFunction;	
@Override
	protected void onCreate(Bundle savedInstanceState)
   {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.all_dr_pasent);
		//startService(new Intent(admin_list.this,UpdaterServiceManager.class));
        initialize();
	}
	private void initialize() 
	{
		userFunction=new UserFunction();
		CityList=new ArrayList<HashMap<String,String>>();
	    list=(ListView)findViewById(R.id.list);
	    exit=(Button)findViewById(R.id.btn_Exit);
	    exit.setOnClickListener(this);
	    checkconnection();
	}
	private void checkconnection() {
		i=1;
		try{	
			 ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		     NetworkInfo nf=cn.getActiveNetworkInfo();
		    if(nf != null && nf.isConnected()==true )
		    {
			     //json = userFunction.Load_All_user();
			  	//UpdateImginlocalDatabase();
		    	new setCityUser().execute();
			}else
				{
				
				TextView tv1 = new TextView(this);
		    	tv1.setText("Connection Not Found");
		    	tv1.setTextSize(20);
		    	tv1.setGravity(Gravity.CENTER);
		    	ImageView iv=new ImageView(this);
		    	iv.setImageResource(R.drawable.ic_communities);
	        	LinearLayout ll = new LinearLayout(this);
		    	ll.setOrientation(LinearLayout.VERTICAL);
		    	ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		    	ll.setGravity(Gravity.CENTER);
		    	ll.addView(iv);
		    	ll.addView(tv1);
		    	iv.setOnClickListener(new OnClickListener()
		    	{
					@Override
					public void onClick(View arg0) 
					{
						checkconnection();
					}
				});
		    	setContentView(ll);
				}
		}catch (Throwable e) {}
		
	}
	public void UpdateImginlocalDatabase()
	{
		try {
      	     int success = json.getInt("success");
             String s=String.valueOf(success);
             Log.e("success",s);
      	       if(success == 1)
      	       {
      		      JSONArray products = json.getJSONArray("user");
      		      Log.e("products", products.toString());
      	          for (int i = 0; i < products.length(); i++)
      		      {
      			   json = products.getJSONObject(i);
      			   HashMap<String, String> map = new HashMap<String, String>();
      			   map.put("name",json.getString("name"));
                   map.put("id",json.getString("id"));
      			   CityList.add(map);
                   Log.e("parms", CityList.toString());
                   ListAdapter adapter1 = new SimpleAdapter(getApplicationContext(),CityList,R.layout.user_text, new String[] {"id","name"},new int[] {R.id.id,R.id.name});
      			   list.setAdapter(adapter1);
      			    Log.e("parms", adapter1.toString());
                      getAdapter();
      			         list.getAdapter();
      			       try 
      			        {
      			        	list.setOnItemClickListener(new OnItemClickListener() 
      			            {
      			               @Override
      			    			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3)
      			               {
      			            	 String	id = ((TextView)view.findViewById(R.id.id)).getText().toString();
      			            	 String	name = ((TextView)view.findViewById(R.id.name)).getText().toString();
      			            	//String	id = ((TextView)view.findViewById(R.id.id)).getText().toString();
      			    			Intent intent= new Intent(getApplicationContext(),user_info.class);

      			    			  Bundle unique=new Bundle();
      			    			  unique.putString("id", id);
      			    			  unique.putString("name",name);
      			    			  intent.putExtras(unique);
      			    			  startActivity(intent);
      			               }
      			            });	
      					} catch (Throwable e){}


      			  }
              }
      	       else{}
        	} catch (Throwable e){}
	}

	private void getAdapter() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View arg0) 
	{
	   	switch (arg0.getId())
	   	{
		
        case R.id.btn_Exit:
 		  finish();
 			break;
		}
	}
	class setCityUser extends AsyncTask<String, String, String>{
		@Override
		protected void onPreExecute() 
        {   super.onPreExecute();
			setprogress();
			
			}
		@Override
		protected String doInBackground(String... arg0) 
		{
			try
			{
		      //json = userFunction.Load_All_user();
			}catch(Throwable e){}  return null;
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			//pDialog.dismiss();
			//UpdateImginlocalDatabase();
			
			try {
					pDialog.dismiss();
					runOnUiThread(new Runnable()
					{
					  public void run()
					   {

						    UpdateImginlocalDatabase();
						 
					   }
				});
	          } catch (Throwable e){} 
		}
	
	}


	public void setprogress() {
		try {
			
			pDialog = new ProgressDialog(admin_list.this);
			pDialog.setMessage("Loading. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);		
       	pDialog.show();
			
 	} catch (Throwable e) {}
	}

}
