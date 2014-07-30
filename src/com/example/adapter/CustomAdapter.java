package com.example.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.calendar_japan.R;
import com.example.model.Object_Father;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
	
	private ArrayList<String> array;
	private LayoutInflater inlafe;
	private ViewHolder holder;
	
	public CustomAdapter(Context context, int resource, ArrayList<String> objects) {
		super(context, resource, objects);
		
		array =objects;
		inlafe = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if(v==null)
		{
			holder = new ViewHolder();
			v= inlafe.inflate(R.layout.item_father,null);
			holder.tvDay = (TextView) v.findViewById(R.id.id);
			v.setTag(holder);
		}
		else {
			holder = (ViewHolder) v.getTag();
		}
		holder.tvDay.setText(array.get(position));
		return v;
	}
	class ViewHolder{
		TextView tvDay;
//		LinearLayout ll;
	}
}
