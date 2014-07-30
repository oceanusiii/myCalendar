package com.example.calendar_japan;

import java.util.ArrayList;

import com.example.adapter.CustomAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Fragmenttwo extends Fragment {
	
	private CustomAdapter adapter;
	private ListView lv;
	ArrayList<String> data;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		data = new ArrayList<String>();
		for(int i=1;i<31;i++)
		{
			data.add(""+i);
		}
		View v = inflater.inflate(R.layout.layout_fragment2,container, false);
		adapter = new CustomAdapter(getActivity(), R.layout.item_father,data);
		lv =(ListView) v.findViewById(R.id.listView);
		lv.setAdapter(adapter);
		return v;
	}

}
