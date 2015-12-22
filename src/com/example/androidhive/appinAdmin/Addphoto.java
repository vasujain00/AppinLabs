package com.example.androidhive.appinAdmin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidhive.R;
import com.example.androidhive.library.UserFunction;

public class Addphoto extends Activity implements OnClickListener {

	
	ImageView v1;
	Button b1;
	String image;
	String email;
	 private static final int SELECT_PHOTO = 100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addphoto);
		v1 = (ImageView) findViewById(R.id.iv1);
		Bundle a = getIntent().getExtras();
		v1.setOnClickListener(new btnTakePhotoClicker());
		b1 = (Button) findViewById(R.id.bu1);
		b1.setOnClickListener(this);
		email = a.getString("email");
		   
	}
	
	class btnTakePhotoClicker implements Button.OnClickListener
    {
            public void onClick(View v) {
        	Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        	photoPickerIntent.setType("image/*");
        	startActivityForResult(photoPickerIntent, SELECT_PHOTO);        
        	}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) 
		{ 
	       case SELECT_PHOTO:
	        if(resultCode == RESULT_OK)
	        {  
	            Uri selectedImage = data.getData();
	            InputStream imageStream;
				try {
					imageStream = getContentResolver().openInputStream(selectedImage);
					Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
				    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 90, stream); 
	                byte [] byte_arr = stream.toByteArray();
	                image = Base64.encodeToString(byte_arr, Base64.DEFAULT);
	                Drawable drawable = new BitmapDrawable(yourSelectedImage);
	                v1.setBackgroundDrawable(drawable);
		             
				}catch (Throwable e) {
					
				}
				
	        }
		}
	}
	
	public void onClick(View arg0) {
	{
		UserFunction userFunction = new UserFunction();
		
        JSONObject json = userFunction.ADDPhoto(image,email);
        try {
        	if (json.getString("success") != null) 
            {
                String res = json.getString("success");
                if(Integer.parseInt(res) == 1)
                {
                	//JSONObject json_user = json.getJSONObject("user");
                   // String img=json_user.getString("img").toString();
                    //Bundle ab  = new Bundle();
                    //ab.putString("image", img);
                    Toast.makeText(getApplicationContext(),"Your photo is updated",500).show();
                    Intent ch = new Intent(getApplicationContext(),First.class);
                    //ch.putExtras(ab);
                    startActivity(ch);
                    
                    
                }
                else {
                	
                     }
            }
        }
        catch (Throwable ex)
        {
           ex.printStackTrace();
        }
		
		
		
	}

}}
	
