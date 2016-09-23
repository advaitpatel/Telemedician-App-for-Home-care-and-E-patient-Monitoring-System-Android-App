package info.androidhive.slidingmenu;

import android.os.Bundle;
import android.app.Activity;  
import android.content.Intent;  
import android.graphics.Bitmap;  
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;
import android.widget.Button;  
import android.widget.EditText;
import android.widget.ImageView;  
import android.widget.Toast;

public class ScreenImageCapture extends Activity
{  
	
	Button save,photoButton;
    EditText caption;
	
	private static final int CAMERA_REQUEST = 1888;  
    ImageView imageView;
    
    public void onCreate(Bundle savedInstanceState)
    {  
 
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.screen_image_capture);  
 
        imageView = (ImageView) this.findViewById(R.id.iv_image_capture);  
        photoButton = (Button) this.findViewById(R.id.btn_capture);  
        save=(Button)this.findViewById(R.id.btn_save_image);
        caption=(EditText)this.findViewById(R.id.et_image_caption);
        
        save.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v)
			{
				// Inflate the Layout
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.custom_toast,
						(ViewGroup) findViewById(R.id.custom_toast_layout_id));

				// Create Custom Toast
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setView(layout);
				toast.show();
				//Toast.makeText(getApplicationContext(), "Your Image is Saved Successfully.....", Toast.LENGTH_LONG).show();
				
			}
		});
        
        photoButton.setOnClickListener(new View.OnClickListener()
        {  
 
	        @Override  
	        public void onClick(View v)
	        {  
	             Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);  
	             startActivityForResult(cameraIntent, CAMERA_REQUEST);  
	        }  
	       });  
	  	}  
    
    	protected void onActivityResult(int requestCode, int resultCode, Intent data)
    	{  
		     if (requestCode == CAMERA_REQUEST)
		     {  
		    	 Bitmap photo = (Bitmap) data.getExtras().get("data");  
		    	 imageView.setImageBitmap(photo);  
		     }  
    	}  
 
	  
	}  