package com.tiendd90.mycalendalapp;

import java.util.Calendar;

import com.tiendd90.dataprovider.DBConfig;
import com.tiendd90.model.Day;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;




public class MainActivity extends Activity 
{
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ??
        new DBConfig(MainActivity.this);
        
        //
        Calendar calendar = Calendar.getInstance();
        
        // get today
        Day d = new Day();
        d.setD(calendar.get(Calendar.DAY_OF_MONTH) + "");
        d.setM(calendar.get(Calendar.MONTH) + "");
        d.setY(calendar.get(Calendar.YEAR) + "");
        
        // put to bundle
        Bundle b = new Bundle();
        b.putSerializable("day", d);
        
        // put to intent
        getIntent().putExtra("data", b);
        
        
        
        
        // add fragment01 first
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        
        ft.add(R.id.activity_main_fragmentHolder, new Fragment01());
        ft.commit();
        
        
    }
    
    
    
    /**
     * Method onClick
     * @param view
     */
    public void choseFragments(View view)
    {
    	Fragment f;
    	
    	// button open fragment01 onClick
    	if(view == findViewById(
    			R.id.activity_main_btnOpenFragment01))
    	{
    		f = new Fragment01();
    	}
    	// button open fragment02 onClick
    	else if(view == findViewById(
    			R.id.activity_main_btnOpenFragment02))
    	{
    		f = new Fragment02();
    	}
    	// button open fragment03 onClick
    	else if(view == findViewById(
    			R.id.activity_main_btnOpenFragment03))
    	{
    		f = new Fragment03();
    	}
    	// button open fragment04 onClick
    	else
    	{
    		f = new Fragment04();
    	}
    	
    	
    	// replace fragment in layout
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        
        ft.replace(R.id.activity_main_fragmentHolder, f);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);	//??
        ft.addToBackStack(null);
        ft.commit();
    
    
    }


}
