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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenResetPassword extends Activity
{
	
	EditText username,oldpass,newpass,conpass;
	Button resetpass;
	TextView linktocreateacc,linktologinacc;

	@Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_reset_password);
		 
		 username=(EditText)findViewById(R.id.reset_et_uname);
		 oldpass=(EditText)findViewById(R.id.reset_et_oldpassword);
		 newpass=(EditText)findViewById(R.id.reset_et_new_password);
		 conpass=(EditText)findViewById(R.id.reset_et_confirm_password);
		 
		 linktocreateacc=(TextView)findViewById(R.id.link_to_create_account);
		 linktologinacc=(TextView)findViewById(R.id.link_to_login_account);
		 
		 linktologinacc.setOnClickListener(new View.OnClickListener() 
		 {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(getApplicationContext(),LoginClass.class);
				startActivityForResult(i, 500);
			     overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_left);
				startActivity(i);
				
			}
		});
		 
		 resetpass=(Button)findViewById(R.id.reset_pass_btn);
		 
		 resetpass.setOnClickListener(new View.OnClickListener()
		 {
			
			@Override
			public void onClick(View v) 
			{
				String opaa=oldpass.getText().toString();
				String newpa=newpass.getText().toString();
				String conpa=conpass.getText().toString();
				
				if(opaa.length()==0)
				{
					oldpass.requestFocus();
					oldpass.setError("Plese Enter Username");
				}
				else if(newpa.length()==0)
				{
					newpass.requestFocus();
					newpass.setError("Please Enter New Password");
				}
				else if(conpa.length()==0)
				{
					conpass.requestFocus();
					conpass.setError("Please Confirm Your New Password");
				}
				else if(!conpa.equals(newpa))
				{
					conpass.requestFocus();
					conpass.setError("Password does not match");
				}
				else
				{
					new ResetPass(username.getText().toString(),oldpass.getText().toString(),newpass.getText().toString()).execute();
				}
				
			}
		});
		 
		 
		 
		 linktocreateacc.setOnClickListener(new View.OnClickListener()
		 {
			
			@Override
			public void onClick(View v) 
			{
				
				Intent i=new Intent(getApplicationContext(),ScreenRegistration.class);
				startActivityForResult(i, 500);
			     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(i);
				
			}
		});
	
	 }
	 
	
	class ResetPass extends AsyncTask<String, Void, String>
	{
			
			String user,pass,newpa;
			
			public ResetPass(String user,String pass,String newpa)
			{
				this.user=user;
				this.pass=pass;
				this.newpa=newpa;
			}
			

			@Override
			protected String doInBackground(String... arg0)
			{
				
				String data="";
				InputStream is=null;
				
				List<NameValuePair> param=new ArrayList<NameValuePair>();
				
				//	param.add(new BasicNameValuePair("col_name",stringname));	
					param.add(new BasicNameValuePair("user",user));
					param.add(new BasicNameValuePair("pass", pass));
					param.add(new BasicNameValuePair("newpa", newpa));
					
					try
					{
						HttpClient client=new DefaultHttpClient();
						HttpPost post=new HttpPost("http://herry.cuccfree.com/resetpassword.php");
							
						post.setEntity(new UrlEncodedFormEntity(param));
						HttpResponse response =client.execute(post);
							
						HttpEntity entity=response.getEntity();
							
						is=entity.getContent();
							
						int ch;
							
						while((ch=is.read())!=-1)
						{
							data=data+(char)ch;
						}
					}
					catch(Exception e)
					{
						
						Log.d("Loading error", e.getMessage());
					}
					
					
				return data;
			}
			
			@Override
			protected void onPostExecute(String result)
			{
				super.onPostExecute(result);
				
				if(result.equals("not success"))
				{
					Toast.makeText(getApplicationContext(), "You have entered wronge password or user name", Toast.LENGTH_LONG).show();
					username.setText("");
					oldpass.setText("");
					newpass.setText("");
					conpass.setText("");
					//Intent ii=new Intent(getActivity(),ScreenLogin.class);
					//startActivity(ii);
				}
				else
				{
					
					
					Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
					username.setText("");
					oldpass.setText("");
					newpass.setText("");
					conpass.setText("");
					
					Intent ii=new Intent(ScreenResetPassword.this,LoginClass.class);
					startActivity(ii);
		
				}
			
			}
	}
	
}
