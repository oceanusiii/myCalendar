package com.tiendd90.mycalendalapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FragmentIcon01 extends Fragment
{
	
//	private ImageView iv1;
//	private ImageView iv2;
//	private ImageView iv3;
//	private ImageView iv4;
//	private ImageView iv5;
//	private ImageView iv6;
//	private ImageView iv7;
//	private ImageView iv8;
//	private ImageView iv9;
//	private ImageView iv10;
	
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
//		iv1 = (ImageView) getActivity().findViewById(R.id.imageView1);
//		iv2 = (ImageView) getActivity().findViewById(R.id.imageView2);
//		iv3 = (ImageView) getActivity().findViewById(R.id.imageView3);
//		iv4 = (ImageView) getActivity().findViewById(R.id.imageView4);
//		iv5 = (ImageView) getActivity().findViewById(R.id.imageView5);
//		iv6 = (ImageView) getActivity().findViewById(R.id.imageView6);
//		iv7 = (ImageView) getActivity().findViewById(R.id.imageView7);
//		iv8 = (ImageView) getActivity().findViewById(R.id.imageView8);
//		iv9 = (ImageView) getActivity().findViewById(R.id.imageView9);
//		iv10 = (ImageView) getActivity().findViewById(R.id.imageView10);
		
	}

	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(
				R.layout.layout_icon_chose, container, false);
	}
	
	
//	public void iconChose(View v)
//	{
//		Intent i = getActivity().getIntent();
//		Bundle b = new Bundle();
//		
//		if(v==iv1)
//		{
//			b.putString("icon", "pic1");
//		}
//		else if(v==iv2)
//		{
//			b.putString("icon", "pic2");
//		}
//		else if(v==iv3)
//		{
//			b.putString("icon", "pic3");
//		}
//		else if(v==iv4)
//		{
//			b.putString("icon", "pic4");
//		}
//		else if(v==iv5)
//		{
//			b.putString("icon", "pic5");
//		}
//		else if(v==iv6)
//		{
//			b.putString("icon", "pic7");
//		}
//		else if(v==iv7)
//		{
//			b.putString("icon", "pic8");
//		}
//		else if(v==iv8)
//		{
//			b.putString("icon", "pic9");
//		}
//		else if(v==iv9)
//		{
//			b.putString("icon", "pic10");
//		}
//		else if(v==iv10)
//		{
//			b.putString("icon", "pic11");
//		}
//		
//		i.putExtra("dataIcon", b);
//		getActivity().setResult(
//				PlanDetailActivity.RESULT_CODE_CHOSEICON, i);
//		getActivity().finish();
//	}
	
}
