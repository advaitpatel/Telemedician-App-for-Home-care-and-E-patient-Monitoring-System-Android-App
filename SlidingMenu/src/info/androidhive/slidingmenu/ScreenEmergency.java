package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScreenEmergency extends Fragment
{
	Button myloc,blood_req,ambu;
	
	public ScreenEmergency(){}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState)
	{
       View rootView = inflater.inflate(R.layout.screen_emergency, container, false);
       
       myloc=(Button) rootView.findViewById(R.id.btn_current_loc);
       blood_req=(Button) rootView.findViewById(R.id.btn_blood_cont);
       ambu=(Button) rootView.findViewById(R.id.btn_ambu);
       
       myloc.setOnClickListener(new View.OnClickListener()
       {
		
			@Override
			public void onClick(View v)
			{
				Intent i=new Intent(getActivity(),ScreenCurrentLocation.class);
				startActivityForResult(i, 500);
			    overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(i);
			}
       }); 
       
       blood_req.setOnClickListener(new View.OnClickListener()
       {
		
			@Override
			public void onClick(View v)
			{
				Intent ii=new Intent(getActivity(),ScreenBloodContacts.class);
				startActivityForResult(ii, 500);
			     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(ii);
				
			}
       });
       
       ambu.setOnClickListener(new View.OnClickListener()
       {
		
			@Override
			public void onClick(View v)
			{
				Intent ii=new Intent(getActivity(),ScreenCallAmbulance.class);
				startActivityForResult(ii, 500);
			     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(ii);
				
			}
       });
       
       return rootView;
	}


	protected void overridePendingTransition(int slideInTop, int slideOutRight) {
		// TODO Auto-generated method stub
		
	}
   
}
