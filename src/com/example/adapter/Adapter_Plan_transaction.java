package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.CustomAdapter.ViewHolder;
import com.example.calendar_japan.R;
import com.example.model.Plan_Transaction;

public class Adapter_Plan_transaction extends ArrayAdapter<Plan_Transaction> {
	
	private ArrayList<Plan_Transaction> array_plan_transaction;
	private LayoutInflater inlafe;
	private ViewHolder holder;
	
	public Adapter_Plan_transaction(Context context, int resource,
			ArrayList<Plan_Transaction> objects) {
		super(context, resource, objects);

		array_plan_transaction = objects;
		inlafe = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v=convertView;
		if(v==null){
			holder = new ViewHolder();
			v= inlafe.inflate(R.layout.item_plan_transaction,null);
			holder.pic = (ImageView) v.findViewById(R.id.item_pic);
			holder.alerm = (ImageView) v.findViewById(R.id.item_alerm);
			holder.time = (TextView) v.findViewById(R.id.item_time);
			holder.title = (TextView) v.findViewById(R.id.item_title);
			holder.content = (TextView) v.findViewById(R.id.item_content);
			v.setTag(holder);
		}
		else {
			holder= (ViewHolder) v.getTag();
		}
		String startHour  = array_plan_transaction.get(position).getStarttime();
		String endHour =array_plan_transaction.get(position).getEndtime();
		holder.time.setText(startHour+" ~ "+endHour);
		holder.title.setText(array_plan_transaction.get(position).getTitle());
		return v;
	}

	public class ViewHolder{
		ImageView pic,alerm;
		TextView time,title,content;
		
	}
	
}
