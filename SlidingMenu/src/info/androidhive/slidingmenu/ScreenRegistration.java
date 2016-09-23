package info.androidhive.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;

public class ScreenRegistration extends Activity
{
	 
	Button openRegForm;
	TextView linktologinacc;
	TextView clicklink,clickimage;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
	  
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_registration);
		 
		 openRegForm=(Button)findViewById(R.id.btn_open_registration_page);
		 linktologinacc=(TextView)findViewById(R.id.linkForLogin);
		 
		 //clicklink=(TextView)findViewById(R.id.linkForLogin);
		 clickimage=(TextView)findViewById(R.id.click_on_image);
		 
		 //custom fonts
		 Typeface custom_font2 = Typeface.createFromAsset(getAssets(),"AUGUST Regular.ttf");
		 linktologinacc.setTypeface(custom_font2);
		 
		 Typeface custom_font21 = Typeface.createFromAsset(getAssets(),"AUGUST Regular.ttf");
		 clickimage.setTypeface(custom_font21);
		 
		 
		 openRegForm.setOnClickListener(new View.OnClickListener()
		 {
			
			@Override
			public void onClick(View v)
			{
				//Toast.makeText(getApplicationContext(), "Click To thay che", Toast.LENGTH_LONG).show();
				Intent go=new Intent(getApplicationContext(),ScreenRegistrationForm.class);
				startActivity(go);
				
			}
		});
		 
		 linktologinacc.setOnClickListener(new View.OnClickListener()
		 {
			
			@Override
			public void onClick(View v)
			{
				
				Intent gotologin=new Intent(getApplicationContext(),LoginClass.class);
				startActivity(gotologin);
			}
		});
		 
	 }
}	 
	 
	 
	    				    	
	