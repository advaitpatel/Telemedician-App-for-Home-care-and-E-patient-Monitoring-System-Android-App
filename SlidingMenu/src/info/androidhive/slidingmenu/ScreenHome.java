package info.androidhive.slidingmenu;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScreenHome extends Fragment
{
	
	Button gotochart;
	
	//for admob
			private InterstitialAd interstitial;
		
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View home = inflater.inflate(R.layout.screen_home, container, false);
        
        gotochart=(Button) home.findViewById(R.id.btn_draw_chart);
        
        gotochart.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(getActivity(),ScreenChart.class);
				startActivity(i);
				
			}
		});
        
     // code for admobs start
		
		// Prepare the Interstitial Ad
		interstitial = new InterstitialAd(getActivity());
		// Insert the Ad Unit ID
		interstitial.setAdUnitId("ca-app-pub-8482190490613592/4323793061");
 
		//Locate the Banner Ad in activity_main.xml
		AdView ad1 = (AdView) home.findViewById(R.id.adView1);
		AdView ad2 = (AdView) home.findViewById(R.id.adView2);
 
		// Request for Ads
		AdRequest adRequest = new AdRequest.Builder()
 
		// Add a test device to show Test Ads
		 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		 .addTestDevice("5F3605E44ED056A99D12B71AB517FEFF")
				.build();
 
		// Load ads into Banner Ads
		ad1.loadAd(adRequest);
		ad2.loadAd(adRequest);
 
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

        
        
        
        
        
        
        return home;
    }
	
	//code for admob
			public void displayInterstitial() {
				// If Ads are loaded, show Interstitial else show nothing.
				if (interstitial.isLoaded()) {
					interstitial.show();
				}
			}
    
}
