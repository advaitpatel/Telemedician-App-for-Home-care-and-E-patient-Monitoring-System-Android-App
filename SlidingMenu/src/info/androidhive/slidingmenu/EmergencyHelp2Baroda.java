package info.androidhive.slidingmenu;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EmergencyHelp2Baroda extends Activity
{
	//static final LatLng Defloc = new LatLng(22.360354, 73.163298);
	//private GoogleMap googleMap;
	
	static final LatLng HAMBURG = new LatLng(22.360354, 73.163298);
	  static final LatLng KIEL = new LatLng(22.311953, 73.197187);
	  private GoogleMap map;

	protected void onCreate(Bundle savedInstanceState)
	{
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.emergency_help2baroda);
		 
		 Button startBtn = (Button) findViewById(R.id.btn_call_help2baroda);
	      startBtn.setOnClickListener(new View.OnClickListener()
	      {
	         public void onClick(View view)
	         {
	        	 makeCall();
	         }
	      });
	      
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG).title("Help2Baroda Office").snippet("Officials"));
	    Marker kiel = map.addMarker(new MarkerOptions()
	    	        .position(KIEL)
	    	        .title("Vision Medicare")
	    	        .snippet("Our Company Location"));
	    /*
	    	        .icon(BitmapDescriptorFactory
	    	            .fromResource(R.drawable.ic_launcher)));*/

	    	    // Move the camera instantly to hamburg with a zoom of 15.
	    	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 10));
	    	    
	    	    

	    	    // Zoom in, animating the camera.
	    	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	    	  
  
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
	         Toast.makeText(EmergencyHelp2Baroda.this, 
	         "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
	      }
	 }
	
	
}
