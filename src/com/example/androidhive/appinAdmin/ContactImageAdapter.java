package com.example.androidhive.appinAdmin;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidhive.R;


public class ContactImageAdapter extends ArrayAdapter<Contact> {
	Context context;
	ImageView iv;
	TextView tv,tv1;
	int layoutResourceId;
	ArrayList<Contact> data = new ArrayList<Contact>();

	public ContactImageAdapter(Context context, int layoutResourceId,
			ArrayList<Contact> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ImageHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			//holder = new ImageHolder();
		     iv = (ImageView) row.findViewById(R.id.imgn);
	         tv=(TextView)row.findViewById(R.id.txtTitle);

	         
		     row.setTag(iv);
		     row.setTag(tv);
		} else {
			iv = (ImageView) row.getTag();
			tv=(TextView)row.getTag();
		}
		Contact picture = data.get(position);
		String outImage = picture.photo;
		String id= picture.id;
		String name = picture.name;
		String idi = String.valueOf(id);
		byte[] qrimage = Base64.decode(outImage.getBytes(), Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(qrimage, 0,qrimage.length);
       
		iv.setImageBitmap(bmp);
        tv.setText(name);
		return row;
	}

	static class ImageHolder {
		ImageView imgIcon;
	}
}
