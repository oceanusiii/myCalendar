package com.tiendd90.mycalendalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


public class ChoseIconActivity extends FragmentActivity
{
	
	private ChoseIconAdapter adapter;
	private ViewPager vpIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_icon);
		
		// get viewPager
		vpIcon = (ViewPager) findViewById(R.id.vpIconChose);
		
		// create new adapter
		adapter = new ChoseIconAdapter(
					getSupportFragmentManager());
		
		// set adapter
		vpIcon.setAdapter(adapter);
	}
	
	
	
	public static class ChoseIconAdapter 
					extends FragmentPagerAdapter
	{
		public ChoseIconAdapter(FragmentManager fm)
		{
			super(fm);
		}

		
		@Override
		public Fragment getItem(int arg0)
		{
			switch(arg0)
			{
			case 0:
				return new FragmentIcon01();
			case 1:
				return new FragmentIcon02();
			case 2:
				return new FragmentIcon03();
			default:
				return null;
			}
		}
		

		@Override
		public int getCount()
		{
			return 3;
		}
		
	}
	
	
	public void iconChose(View v)
	{
		Intent i = getIntent();
		Bundle b = new Bundle();
		
		if(v==findViewById(R.id.imageView1))
		{
			b.putString("icon", "pic1");
		}
		else if(v==findViewById(R.id.imageView2))
		{
			b.putString("icon", "pic2");
		}
		else if(v==findViewById(R.id.imageView3))
		{
			b.putString("icon", "pic3");
		}
		else if(v==findViewById(R.id.imageView4))
		{
			b.putString("icon", "pic4");
		}
		else if(v==findViewById(R.id.imageView5))
		{
			b.putString("icon", "pic5");
		}
		else if(v==findViewById(R.id.imageView6))
		{
			b.putString("icon", "pic7");
		}
		else if(v==findViewById(R.id.imageView7))
		{
			b.putString("icon", "pic8");
		}
		else if(v==findViewById(R.id.imageView8))
		{
			b.putString("icon", "pic9");
		}
		else if(v==findViewById(R.id.imageView9))
		{
			b.putString("icon", "pic10");
		}
		else if(v==findViewById(R.id.imageView10))
		{
			b.putString("icon", "pic11");
		}
		
		i.putExtra("dataIcon", b);
		setResult(PlanDetailActivity.RESULT_CODE_CHOSEICON, i);
		finish();
	}
	
}
