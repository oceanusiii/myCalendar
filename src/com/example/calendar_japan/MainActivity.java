package com.example.calendar_japan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private ImageView share,shift,share2,setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initialize();
        
        share.setOnClickListener(this);
        shift.setOnClickListener(this);
        share2.setOnClickListener(this);
        setting.setOnClickListener(this);
        
        //add plan
        
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        
       ft.add(R.id.content,new Fragmentone());
       ft.commit();
    }
    
    public void initialize(){
    	share = (ImageView) findViewById(R.id.share);
        shift = (ImageView) findViewById(R.id.shift);
        share2 = (ImageView) findViewById(R.id.share2);
        setting = (ImageView) findViewById(R.id.setting);
    }
    /**
     * Method onClick
     * @param view
     */

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Fragment f = null;
		switch (v.getId()) {
		case R.id.share:
			f= new Fragmentone();
			break;
		case R.id.shift:
			f= new Fragmenttwo();
			break;
		case R.id.share2:
			f= new Fragmentthree();
			break;
		case R.id.setting:
			f= new Fragmentfor();
			break;
			
		}
		
		
		Log.e("????????????????","?????????????????????");
		// replace fragment in layout
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.content,f);
		ft.addToBackStack(null);
		ft.commit();
	}
}
