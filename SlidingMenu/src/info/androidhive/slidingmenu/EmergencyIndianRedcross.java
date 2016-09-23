package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EmergencyIndianRedcross extends Activity
{
	static final LatLng Defloc = new LatLng(22.302443, 73.197582);
	private GoogleMap googleMap;
	
	protected void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.emergency_indianredcross);
		 
		 Button startBtn = (Button) findViewById(R.id.btn_call_indianredcrosssociety);
	      startBtn.setOnClickListener(new View.OnClickListener()
	      {
	         public void onClick(View view)
	         {
	        	 makeCall();
	         }
	      });
	      
	      try 
			{
				
				if (googleMap == null) 
				{
					googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
				}
				
				googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
				
				Marker TP = googleMap.addMarker(new MarkerOptions().position(Defloc).title("Location Maps"));

				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
				TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
				//TP.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
		
				CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(22.302443, 73.197582)).zoom(12).build();
				
		 
				googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				
				googleMap.getUiSettings().setZoomControlsEnabled(true);// true to enable
				//googleMap.getUiSettings().setZoomGesturesEnabled(false);

				googleMap.getUiSettings().setMyLocationButtonEnabled(true);
				
				googleMap.getUiSettings().setRotateGesturesEnabled(true);
				
				//to set traffic 
				googleMap.setTrafficEnabled(true);
				
				

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
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
	         Toast.makeText(EmergencyIndianRedcross.this, 
	         "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
	      }
	   }
}
