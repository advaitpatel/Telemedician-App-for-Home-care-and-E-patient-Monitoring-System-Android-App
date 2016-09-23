package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScreenEDiagnosisHome extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
        
        setContentView(R.layout.screen_ediagnosis_home);
        
        TextView t1=(TextView)findViewById(R.id.ed_tv_btn_book_appo1);
		/*TextView t2=(TextView)findViewById(R.id.ed_tv_btn_book_appo2);
		TextView t3=(TextView)findViewById(R.id.ed_tv_btn_book_appo3);
		TextView t4=(TextView)findViewById(R.id.ed_tv_btn_book_appo4);
		*/
		TextView t5=(TextView)findViewById(R.id.ed_tv_btn_ask_quest1);
		/*TextView t6=(TextView)findViewById(R.id.ed_tv_btn_ask_quest2);
		TextView t7=(TextView)findViewById(R.id.ed_tv_btn_ask_quest3);
		TextView t8=(TextView)findViewById(R.id.ed_tv_btn_ask_quest4);
		*/
		t1.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i=new Intent(getApplicationContext(),ScreenAppointment.class);
				startActivity(i);
				
			}
		});
		
		t5.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent ii=new Intent(getApplicationContext(),ScreenAskQuestion.class);
				startActivity(ii);
				
			}
		});
		
		
	}

	
}
	