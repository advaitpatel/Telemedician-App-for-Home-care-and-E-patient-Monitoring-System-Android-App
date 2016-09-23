package info.androidhive.slidingmenu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenUserProfile extends Activity
{	
	
	Button mod_Acc;
	TextView fna,lna,gen,bday,mob,mail,una,pass;
	List<String> values;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.screen_user_profile);
        
        
        fna=(TextView)findViewById(R.id.tv_fna);
        lna=(TextView)findViewById(R.id.tv_ln);
        gen=(TextView)findViewById(R.id.tv_gen);
        bday=(TextView)findViewById(R.id.tv_bdate);
        mob=(TextView)findViewById(R.id.tv_mobno);
        mail=(TextView)findViewById(R.id.tv_email);
        una=(TextView)findViewById(R.id.tv_una);
        pass=(TextView)findViewById(R.id.tv_pass);
        
       
        new RetriveProfile().execute();
        
    }
	class RetriveProfile extends AsyncTask<String, Void, String>
	{
		
		String findmydoc;
		
		public RetriveProfile()
		{

		}
		

		@Override
		protected String doInBackground(String... arg0)
		{
			
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("area","u_name"));
				
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/retriveprofile.php");
						
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
			
			if(result.equals("no prifile"))
			{
				Toast.makeText(getApplicationContext(), "Mo result", Toast.LENGTH_LONG).show();
				
			}
			else
			{
				values = Arrays.asList(result.split(","));
				fna.setText("First Name : "+values.get(0));
				lna.setText("Last Name : "+values.get(1));
				gen.setText("Gender : "+values.get(2));
				bday.setText("DOB : "+values.get(3));
				mob.setText("Mobile : "+values.get(4));
				mail.setText("Email : "+values.get(5));
				una.setText("User Name : "+values.get(6));
				pass.setText("Password :"+values.get(7));
				//fna.setText(result.toString());
				//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
}