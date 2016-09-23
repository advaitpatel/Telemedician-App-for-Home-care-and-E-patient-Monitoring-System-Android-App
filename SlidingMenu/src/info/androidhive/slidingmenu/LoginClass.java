package info.androidhive.slidingmenu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class LoginClass extends Activity
{
	EditText user,pass;
	TextView forgetpass,reg_acc_link;
	Button click_login_button;
	TextView str;
	
	private VideoView videoView;
	
	// Alert Dialog Manager
		AlertDialogManager alert = new AlertDialogManager();
	
	@Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);        
		 
		
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.login_dialog);
		  
		  str=(TextView)findViewById(R.id.tv_string);
		  Typeface custom_font6= Typeface.createFromAsset(getAssets(),"Face Your Fears.ttf");
	      str.setTypeface(custom_font6);
		  
		  videoView = (VideoView) findViewById(R.id.videoView1);
			 
			String path = "android.resource://" + getPackageName() + "/"
					+ R.raw.video;
			
			videoView.setVideoURI(Uri.parse(path));
			
			videoView.start();
		  
			videoView.setOnCompletionListener(new OnCompletionListener() 
			{

				public void onCompletion(MediaPlayer mp)
				{
					
					jump();
				}
			
			});
			
			
		  
		  
		  click_login_button=(Button)findViewById(R.id.btn_login);
		  user=(EditText)findViewById(R.id.et_login_username);
		  pass=(EditText)findViewById(R.id.et_login_password);
		  
		  forgetpass=(TextView)findViewById(R.id.link_to_forget_password);
		  
		  reg_acc_link=(TextView)findViewById(R.id.link_to_register_account_go);
		  
		  reg_acc_link.setOnClickListener(new View.OnClickListener()
		  {
			
			@Override
			public void onClick(View v) 
			{
				
				//Toast.makeText(getApplicationContext(), "Click To Thay Che", Toast.LENGTH_LONG).show();
				
				
				Intent i7=new Intent(getApplicationContext(),ScreenRegistration.class);
				startActivity(i7);
				
			}
		});
		  
		  click_login_button.setOnClickListener(new View.OnClickListener()
		  {
			
			@Override
			public void onClick(View v)
			{
				String us=user.getText().toString();
				String pa=pass.getText().toString();
				
				if(us.length()==0 && pa.length()==0)
				{
					// user didn't entered username or password
					// Show alert asking him to enter the details
					alert.showAlertDialog(LoginClass.this, "Login failed..", "Please enter Username and Password", false);
					
				}
				else if(us.length()==0)
				{
					alert.showAlertDialog(LoginClass.this, "Login failed..", "Please Enter Username", false);
					//user.requestFocus();
					//user.setError("Please Enter Your User Name");
				}
				else if(pa.length()==0)
				{
					alert.showAlertDialog(LoginClass.this, "Login failed..", "Please Enter Password", false);
					//pass.requestFocus();
					//pass.setError("Please Enter Your Password");
				}
				else
				{
					new Login(us,pa).execute();
				}
			}
		});
		  
		  forgetpass.setOnClickListener(new View.OnClickListener()
		  {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(getApplicationContext(),ScreenResetPassword.class);
				startActivityForResult(i, 500);
		        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_left);
				startActivity(i);
				
			}
		});
		 
	 }
	 
	 class Login extends AsyncTask<String,Void,String>
		{
			String user,pass;
			
			Login(String us,String pa)
			{
				user=us;
				pass=pa;
			}

			@Override
			protected String doInBackground(String... arg0) 
			{
			
				String data="";
				InputStream is=null;
					List<NameValuePair> param = new ArrayList<NameValuePair>();
					
		            param.add(new BasicNameValuePair("user",user));
		            param.add(new BasicNameValuePair("pass",pass));
		            
		            try
		            {
		    			HttpClient httpclient = new DefaultHttpClient();
		    			HttpPost httppost = new HttpPost("http://herry.cuccfree.com/login.php");
		    			httppost.setEntity(new UrlEncodedFormEntity(param));
		    			HttpResponse response = httpclient.execute(httppost);
		    			HttpEntity entity = response.getEntity();
		    			is = entity.getContent();

		    			int ch;
						while((ch=is.read())!=-1)				
						{
							data=data+(char)ch;
						}

		    		}
		            catch(Exception e)
		            {
		    			Log.e("log_tag", "Error in http connection"+e.toString());
		    		}

				return data;
			}
			
			@Override
			protected void onPostExecute(String result)
			{
				super.onPostExecute(result);
				
				if(result.equals("Login Successfully"))
				{
					
					// Inflate the Layout
		            LayoutInflater inflater = getLayoutInflater();
		            View layout = inflater.inflate(R.layout.custom_toast_sucess,
		                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
		 
		            // Create Custom Toast
		            Toast toast = new Toast(getApplicationContext());
		            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		            toast.setDuration(Toast.LENGTH_LONG);
		            toast.setView(layout);
		            toast.show();
					
					//Toast.makeText(getApplicationContext(), "You are Authorized User", Toast.LENGTH_LONG).show();
					Intent ii=new Intent(getApplicationContext(),MainActivity.class);
					startActivityForResult(ii, 500);
			        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_bottom);
					startActivity(ii);
				}
				else
				{
					//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
					// Inflate the Layout
		            LayoutInflater inflater = getLayoutInflater();
		            View layout = inflater.inflate(R.layout.custom_toast_cancel,
		                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
		 
		            // Create Custom Toast
		            Toast toast = new Toast(getApplicationContext());
		            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		            toast.setDuration(Toast.LENGTH_LONG);
		            toast.setView(layout);
		            toast.show();
				}
			}		 
		}
		
	 @Override
		public void onBackPressed()
		{
			//Display alert message when back button has been pressed
			backButtonHandler();
			return;
		}
		
		public void backButtonHandler()
		{
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginClass.this);
			
			// Setting Dialog Title
			alertDialog.setTitle("You have to login with Us");
			
			// Setting Dialog Message
			alertDialog.setMessage("For More Access Login with Vision Medicare is Compulsary");
			
			// Setting Icon to Dialog
			alertDialog.setIcon(R.drawable.dialog_icon);
			
			// Setting Positive "Yes" Button
			alertDialog.setPositiveButton("QUIT",
					new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							
							android.os.Process.killProcess(android.os.Process.myPid());
							onDestroy();
							
							//finish();
							
						}
					});
			
			// Setting Negative "NO" Button
			alertDialog.setNegativeButton("CONTINUE",
					new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							// Write your code here to invoke NO event
							dialog.cancel();
						}
					});
			// Showing Alert Message
			alertDialog.show();
		}
		private void jump() 
		{
			//if(isFinishing())
				//return;
			//startActivity(new Intent(this, MainActivity.class));
			//finish();
			videoView.start();
		}
}


