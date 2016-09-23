package info.androidhive.slidingmenu;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
 

public class ScreenDoctorListView extends Activity
{
	Button call,book,map;
	
	//for admob
		private InterstitialAd interstitial;
	
	 ListView list;
	 String[] itemname =
		 {
			 "Dr. Virat Kohli",
			 "Dr. Mahendrasingh Dhoni",
			 "Dr. Suresh Raina",
			 "Dr. Ravichandran Ashwin",
			 "Dr. Sir Ravindra Jadeja",
			 "Dr. Virat Kohli",
			 "Dr. Mahendrasingh Dhoni",
			 "Dr. Suresh Raina",
			 "Dr. Ravichandran Ashwin",
			 "Dr. Sir Ravindra Jadeja",
			 "Dr. Virat Kohli",
			 "Dr. Mahendrasingh Dhoni"
		 };
	 
	  Integer[] imgid=
		 {
			 R.drawable.doc_ask_quest,
			 R.drawable.doc_emerg,
			 R.drawable.doc_find_best_doc,
			 R.drawable.doc_home,
			 R.drawable.doc_make_appointment,
			 R.drawable.doc_make_appointment,
			 R.drawable.doc_medicine_search,
			 R.drawable.doc_notification,
			 R.drawable.doc_search_labs,
			 R.drawable.doc_ask_quest,
			 R.drawable.doc_emerg,
			 R.drawable.doc_find_best_doc,
		 };
	 
	 
	 protected void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_doc_list_view);
		 
		// code for admobs start
			
					// Prepare the Interstitial Ad
					interstitial = new InterstitialAd(ScreenDoctorListView.this);
					// Insert the Ad Unit ID
					interstitial.setAdUnitId("ca-app-pub-8482190490613592/4323793061");
			 
					//Locate the Banner Ad in activity_main.xml
					AdView ad1 = (AdView) this.findViewById(R.id.adView1);
			 
					// Request for Ads
					AdRequest adRequest = new AdRequest.Builder()
			 
					// Add a test device to show Test Ads
					 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
					 .addTestDevice("5F3605E44ED056A99D12B71AB517FEFF")
							.build();
			 
					// Load ads into Banner Ads
					ad1.loadAd(adRequest);
			 
					// Load ads into Interstitial Ads
					interstitial.loadAd(adRequest);
			 
					// Prepare an Interstitial Ad Listener
					interstitial.setAdListener(new AdListener() {
						public void onAdLoaded() {
							// Call displayInterstitial() function
							displayInterstitial();
						}
					});
			
			// code for admobs is ended
		 
		 CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
		 list=(ListView)findViewById(R.id.list);
		 list.setAdapter(adapter);
	 
		 
		 
		 list.setOnItemClickListener(new OnItemClickListener()
		 {
	 
			 @Override
			 public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			 {
								 
				 String Selecteditem= itemname[+position];
				 //Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
				 
				 if(Selecteditem=="Dr. Virat Kohli")
				 {
					 Dialog dialog = new Dialog(ScreenDoctorListView.this);
		             dialog.setContentView(R.layout.doctor_kohli);
		             dialog.setCancelable(true);
		             dialog.onBackPressed();
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle(Selecteditem);//Set Title
		             
		             call=(Button)dialog.findViewById(R.id.dialog_btn_call_doctor);
		             book=(Button)dialog.findViewById(R.id.dialog_btn_book_appointment);
		             map=(Button)dialog.findViewById(R.id.dialog_btn_find_clinic);
		             
		             map.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenShowDoctorClinic.class);
							startActivityForResult(i1, 500);
						     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
							startActivity(i1);
						}
					});
		             
		             
		             call.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							//Intent i1=new Intent(getApplicationContext(),ScreenContactUs.class);
							//startActivity(i1);
							makeCall();
						}
					});
		             
		             book.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i2=new Intent(getApplicationContext(),ScreenDoctorHomeVisit.class);
							startActivity(i2);
						}
					});
		            
		             
		             //now that the dialog is set up, it's time to show it man !!! :)    
		             dialog.show();
		             
				 }
				 else if(Selecteditem=="Dr. Mahendrasingh Dhoni")
				 {
					 Dialog dialog = new Dialog(ScreenDoctorListView.this);
		             dialog.setContentView(R.layout.doctor_dhoni);
		             dialog.setCancelable(true);
		             dialog.onBackPressed();
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle(Selecteditem);//Set Title
		             
		             call=(Button)dialog.findViewById(R.id.dialog_btn_call_doctor);
		             book=(Button)dialog.findViewById(R.id.dialog_btn_book_appointment);
		             map=(Button)dialog.findViewById(R.id.dialog_btn_find_clinic);
		             
		             map.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i3=new Intent(getApplicationContext(),ScreenShowDoctorClinic.class);
							startActivityForResult(i3, 500);
						     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
							startActivity(i3);
						}
					});
		             
		             call.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							//Intent i1=new Intent(getApplicationContext(),ScreenContactUs.class);
							//startActivity(i1);
							makeCall();
						}
					});
		             
		             book.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenDoctorHomeVisit.class);
							startActivity(i1);
						}
					});
		            
		          
		             //now that the dialog is set up, it's time to show it man !!! :)    
		             dialog.show();
				 }
				 else if(Selecteditem=="Dr. Suresh Raina")
				 {
					 Dialog dialog = new Dialog(ScreenDoctorListView.this);
		             dialog.setContentView(R.layout.doctor_raina);
		             dialog.setCancelable(true);
		             dialog.onBackPressed();
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle(Selecteditem);//Set Title
		             
		             call=(Button)dialog.findViewById(R.id.dialog_btn_call_doctor);
		             book=(Button)dialog.findViewById(R.id.dialog_btn_book_appointment);
		             map=(Button)dialog.findViewById(R.id.dialog_btn_find_clinic);
		             
		             map.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenShowDoctorClinic.class);
							startActivityForResult(i1, 500);
						     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
							startActivity(i1);
						}
					});
		             
		             call.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							//Intent i1=new Intent(getApplicationContext(),ScreenContactUs.class);
							//startActivity(i1);
							makeCall();
						}
					});
		             
		             book.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenDoctorHomeVisit.class);
							startActivity(i1);
						}
					});
		             
		             //now that the dialog is set up, it's time to show it man !!! :)    
		             dialog.show();
				 }
				 else if(Selecteditem=="Dr. Ravichandran Ashwin")
				 {
					 Dialog dialog = new Dialog(ScreenDoctorListView.this);
		             dialog.setContentView(R.layout.doctor_ashwin);
		             dialog.setCancelable(true);
		             dialog.onBackPressed();
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle(Selecteditem);//Set Title
		             
		             call=(Button)dialog.findViewById(R.id.dialog_btn_call_doctor);
		             book=(Button)dialog.findViewById(R.id.dialog_btn_book_appointment);
		             map=(Button)dialog.findViewById(R.id.dialog_btn_find_clinic);
		             
		             map.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenShowDoctorClinic.class);
							startActivityForResult(i1, 500);
						     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
							startActivity(i1);
						}
					});
		             
		             call.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							//Intent i1=new Intent(getApplicationContext(),ScreenContactUs.class);
							//startActivity(i1);
							makeCall();
						}
					});
		             
		             book.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenDoctorHomeVisit.class);
							startActivity(i1);
						}
					});
		             
		             //now that the dialog is set up, it's time to show it man !!! :)    
		             dialog.show();
				 }
				 else if(Selecteditem=="Dr. Sir Ravindra Jadeja")
				 {
					 Dialog dialog = new Dialog(ScreenDoctorListView.this);
		             dialog.setContentView(R.layout.doctor_jadeja);
		             dialog.setCancelable(true);
		             dialog.onBackPressed();
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle(Selecteditem);//Set Title
		            
		             call=(Button)dialog.findViewById(R.id.dialog_btn_call_doctor);
		             book=(Button)dialog.findViewById(R.id.dialog_btn_book_appointment);
		             map=(Button)dialog.findViewById(R.id.dialog_btn_find_clinic);
		             
		             map.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenShowDoctorClinic.class);
							startActivityForResult(i1, 500);
						     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
							startActivity(i1);
						}
					});
		             
		             call.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							//Intent i1=new Intent(getApplicationContext(),ScreenContactUs.class);
							//startActivity(i1);
							makeCall();
						}
					});
		             
		             book.setOnClickListener(new View.OnClickListener()
		             {
						
						@Override
						public void onClick(View v) 
						{
							//Toast.makeText(getApplicationContext(), "Click Thay Che", Toast.LENGTH_LONG).show();
							Intent i1=new Intent(getApplicationContext(),ScreenDoctorHomeVisit.class);
							startActivity(i1);
						}
					});
		             
		             //now that the dialog is set up, it's time to show it man !!! :)    
		             dialog.show();
				 }
				 
			 }
			 
		 });
		 
		
	 }
	 
	 protected void makeCall()
		{
		      Log.i("Make call", "");

		      Intent phoneIntent = new Intent(Intent.ACTION_CALL);
		      phoneIntent.setData(Uri.parse("tel:8128413177"));

		      try
		      {
		         startActivity(phoneIntent);
		         finish();
		         Log.i("Finished making a call...", "");
		      }
		      catch (android.content.ActivityNotFoundException ex)
		      {
		         Toast.makeText(ScreenDoctorListView.this, 
		         "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
		      }
		 }

	//code for admob
		public void displayInterstitial() {
			// If Ads are loaded, show Interstitial else show nothing.
			if (interstitial.isLoaded()) {
				interstitial.show();
			}
		}
}
