package info.androidhive.slidingmenu;

import android.app.Activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;

public class ScreenShowDoctorClinic extends Activity
{
	
	static final LatLng KOHLI = new LatLng(22.342847, 73.183145);
	static final LatLng DHONI = new LatLng(22.344851, 73.176016);
	static final LatLng RAINA =new LatLng(22.340773, 73.173806);
	static final LatLng JADEJA = new LatLng(22.339963, 73.178897);
	static final LatLng ASHWIN = new LatLng(22.334079, 73.179431);
	
	private GoogleMap googleMap;
	

	protected void onCreate(Bundle savedInstanceState)
	{
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_show_doctor_clinic);
		 

			try 
			{
				
				if (googleMap == null) 
				{
					googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.doc_clinic)).getMap();
				}
				
				googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				//googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
				
				Marker tp1 = googleMap.addMarker(new MarkerOptions().position(KOHLI).title("Dr. Virat Kohli's Clinic"));
				Marker tp2 = googleMap.addMarker(new MarkerOptions().position(DHONI).title("Dr. MS Dhoni's Clinic"));
				Marker tp3 = googleMap.addMarker(new MarkerOptions().position(RAINA).title("Dr. Suresh Raina's Clinic"));
				Marker tp4 = googleMap.addMarker(new MarkerOptions().position(JADEJA).title("Dr. Ravindra Jadeja's Clinic"));
				Marker tp5 = googleMap.addMarker(new MarkerOptions().position(ASHWIN).title("Dr. Ravinchandran Ashwin's Clinic"));

				tp1.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
				tp2.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				tp3.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
				tp4.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				tp5.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
				
		
				CameraPosition cameraPosition = new CameraPosition.Builder().target(
		                new LatLng(22.342847, 73.183145)).zoom(12).build();
		 
				googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				
				googleMap.getUiSettings().setZoomControlsEnabled(true);// true to enable
				//googleMap.getUiSettings().setZoomGesturesEnabled(false);

				googleMap.getUiSettings().setMyLocationButtonEnabled(true);
				
				googleMap.getUiSettings().setRotateGesturesEnabled(true);

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		 
	      
	 }
}