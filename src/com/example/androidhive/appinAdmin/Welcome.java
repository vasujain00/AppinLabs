package com.example.androidhive.appinAdmin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidhive.R;
import com.example.androidhive.appinAdmin.admin_list.setCityUser;
import com.example.androidhive.library.UserFunction;
import com.navdrawer.SimpleSideDrawer;



public class Welcome extends Activity {
   TextView t1;
   private ProgressDialog pDialog;
   ListView list;
   ArrayList<HashMap<String, String>> CityList;
   ArrayAdapter<String> dataAdapter;
   JSONObject json;UserFunction userFunction;	
   String a,b,c;
   ImageView im;
   SimpleSideDrawer slide_me;
   
   Button left_button, right_button;
   private ListView lv,lv1;
   String pic;

	private String title[] = { "Profile", "Settings", "News Feed", "Change Password",
			"Pages", "Activity Log", "Friends", "Log Out" };

	private String desc[] = { "version: 1.5", "version: 1.6",
			"version: 2.0 & 2.1", "version: 2.2", "version: 2.3",
			"version: 3.0", "version: 4.0", "version: 4.1" };

	private int thumb[] = { R.drawable.cupcake, R.drawable.donut,
			R.drawable.eclair, R.drawable.froyo, R.drawable.gingerbread,
			R.drawable.honeycomb, R.drawable.icecreamsandwich,
			R.drawable.jellybean, };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		im = (ImageView) findViewById(R.id.im);
		//Bundle ab = getIntent().getExtras();
		//pic = Abcd.getPhoto();
		//Log.e("value of pic",pic);
		
		try
		{
		String selectQuery = "SELECT  * FROM data WHERE id=1";

    	SQLiteDatabase db =openOrCreateDatabase("vrj", Context.MODE_PRIVATE,null);
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
				b=cursor.getString(1);
				c=cursor.getString(3);
				a=cursor.getString(2);
                  
				t1 =(TextView) findViewById(R.id.name);
				
		        t1.setText(a);	
		        pic = cursor.getString(5);
		        byte[] qrimage = Base64.decode(pic.getBytes(), Base64.DEFAULT);
				 Bitmap bmp = BitmapFactory.decodeByteArray(qrimage, 0,qrimage.length);
		         Drawable drawable = new BitmapDrawable(bmp);
		         im.setBackgroundDrawable(drawable);		
				Log.e("msg", a+b);
			cursor.close();
		}db.close();	
		
		}catch (Throwable e) {
			// TODO: handle exception
		}

		
	 	
		Funk();

	}


		
		
	

private void Funk() {
	slide_me = new SimpleSideDrawer(this);
	
    slide_me.setLeftBehindContentView(R.layout.left_menu);
   slide_me.setRightBehindContentView(R.layout.right_menu);
   userFunction=new UserFunction();
  	CityList=new ArrayList<HashMap<String,String>>();
     list=(ListView)findViewById(R.id.li1);

   left_button = (Button) findViewById(R.id.left_buton);
   right_button = (Button) findViewById(R.id.right_buton);
  
    left_button.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            slide_me.toggleLeftDrawer();
            TextView t1=(TextView) findViewById(R.id.t1);
            t1.setText("Left open");
            lv = (ListView) findViewById(R.id.listView);
            lv.setAdapter(new VersionAdapter(Welcome.this));
            lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int pos, long arg3) {
					// TODO Auto-generated method stub
					switch (pos) {
					case 0 : break;
						
					case 1:break;
						
					case 2 : break;
					case 3:
					{
						Intent neb = new Intent(getApplicationContext(),ChangePas.class);
						Bundle bu = new Bundle();
						bu.putString("name",c);
						bu.putString("id",a);
						bu.putString("bg",b);
						
						neb.putExtras(bu);
						startActivity(neb);
						
					}
					default :
					{
						
					}
					}
				}
			});
            
        }
    });
    right_button.setOnClickListener(new View.OnClickListener() {
          
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            slide_me.toggleRightDrawer();
            
            try {
            	new setCityUser().execute();
            	
            	
            	
            	
            }
            catch(Throwable ex)
            {
            	
            }
        }
    });
}
    
    
    public void UpdateImginlocalDatabase() {
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
      			   
      			   String name=json.getString("name");
      			   String photo=json.getString("photo");
      			   String id=json.getString("id");
      			   String email = json.getString("email");
      			   
      			   Set_Data ini = new Set_Data(getApplicationContext());
      			   ini.addValues1(id, name, email, photo);
      		      }
      	       }
      	       else
      	       {
      	    	   
      	       }
      	       setValueInList();
		}
        	catch (Throwable e){}
        	
      	       
    }
      			   
	
  


	






private void setValueInList() {
		// TODO Auto-generated method stub

		ArrayList<Contact> imageArry = new ArrayList<Contact>();
		ContactImageAdapter adapter;

		Set_Data set_City_ID = new Set_Data(
				getApplicationContext());
		List<Contact> contacts = set_City_ID.getAllContacts();
		Log.e("storelist"+list.toString(),"getlist"+ set_City_ID.getAllContacts().toString());
		for (Contact cn : contacts) {
			
			imageArry.add(cn);

		}
		
		
		adapter = new ContactImageAdapter(this, R.layout.nation_text, imageArry);
		ListView dataList = (ListView) findViewById(R.id.li1);
		dataList.setAdapter(adapter);

		/*
		 * ListAdapter adapter1 = new SimpleAdapter(getApplicationContext(),
		 * NationList,R.layout.nation_text, new String[] {"title","img"},new
		 * int[] {R.id.date,R.id.date_time}); list1.setAdapter(adapter1);
		 * setList();
		 */
	

		
	}
public void setList() {

	list.getAdapter();
	list.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view,
				int position, long arg3) {
			// nid =
			// ((TextView)view.findViewById(R.id.phch)).getText().toString();
			// displayView();
		}

	});

}











class VersionAdapter extends BaseAdapter {
     
	private LayoutInflater layoutInflater;
	
	public VersionAdapter(Welcome activity) {
		// TODO Auto-generated constructor stub
		layoutInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return title.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View listItem = arg1;
		int pos = arg0;
		if (listItem == null) {
			listItem = layoutInflater.inflate(R.layout.list_item, null);
		}

		// Initialize the views in the layout
		ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
		TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
		TextView tvDesc = (TextView) listItem.findViewById(R.id.desc);

		// set the views in the layout
		iv.setBackgroundResource(thumb[pos]);
		tvTitle.setText(title[pos]);
		tvDesc.setText(desc[pos]);

		return listItem;
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
			Set_Data user = new Set_Data(getApplicationContext());
			String id = user.getLastId();
			if(id== " ")
			{
				id="0";
				
			}
			else
			{
		 
			}
			json = userFunction.Load_All_user(id);
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




public void setprogress() {
	try {
		
		pDialog = new ProgressDialog(Welcome.this);
		pDialog.setMessage("Loading. Please wait...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);		
   	pDialog.show();
		
	} catch (Throwable e) {}
}

}
}
