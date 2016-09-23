package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class SplashScreen extends Activity
{
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    TextView st1,st2;
 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
 
        st1=(TextView)findViewById(R.id.tv_vision_string1);
        Typeface custom_font6= Typeface.createFromAsset(getAssets(),"Face Your Fears.ttf");
        st1.setTypeface(custom_font6);
        
        
        st2=(TextView)findViewById(R.id.tv_vision_string2);
        Typeface custom_font7= Typeface.createFromAsset(getAssets(),"Face Your Fears.ttf");
        st2.setTypeface(custom_font7);
        
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginClass.class);
                startActivityForResult(i, 500);
			     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}