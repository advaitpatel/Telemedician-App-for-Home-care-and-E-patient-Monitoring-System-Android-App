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

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class ScreenDonateBodyPart extends Fragment
{
	String username,contno,day,reason,typeofdonation;
	
	
	Button makedonation;
	EditText uname,contactno,donatday,reasonfordonation;
	Spinner donattype;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState)
	{

       View rootView = inflater.inflate(R.layout.screen_donate, container, false);
       
       makedonation=(Button)rootView.findViewById(R.id.btn_donate_make_donation);
       
       uname=(EditText)rootView.findViewById(R.id.don_et_donate_username);
       contactno=(EditText)rootView.findViewById(R.id.don_et_donate_mobno);
       donatday=(EditText)rootView.findViewById(R.id.donate_et_day);
       reasonfordonation=(EditText)rootView.findViewById(R.id.don_et_donate_reason_for_donation);
       
       donattype=(Spinner)rootView.findViewById(R.id.don_spn_part);
        
       makedonation.setOnClickListener(new View.OnClickListener()
       {
		
		@Override
		public void onClick(View v)
		{
			
			String typeofdonation=donattype.getSelectedItem().toString();
			String username=uname.getText().toString();
			String cont=contactno.getText().toString();
			String day=donatday.getText().toString();
			String reason=reasonfordonation.getText().toString();
			
			if(username.length()==0)
			{
				uname.setError("Please Enter Name");
			}
			else if(cont.length()==0)
			{
				contactno.setError("Please Enter Contact Number");
			}
			else if(day.length()==0)
			{
				donatday.setError("Please Enter Donation Day");
			}
			else if(reason.length()==0)
			{
				reasonfordonation.setError("Please Enter Your Reason");
			}
			else
			{
				Donate don=new Donate(username, cont, typeofdonation, reason, day);
				don.execute();
			}
			
		}
	});
       
       
              
       return rootView;
   }
	 
	class Donate extends AsyncTask<String, Void, String>
    {
    	
    	public Donate(String a,String b,String c,String d,String e)
    	{
    		username=a;
    		contno=b;
    		typeofdonation=c;
    		reason=d;
    		day=e;
    		
    		
    	}

		@Override
		protected String doInBackground(String... params) 
		{
			
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("name",username));
				param.add(new BasicNameValuePair("number",contno));
				param.add(new BasicNameValuePair("type",typeofdonation));
				param.add(new BasicNameValuePair("reason",reason));
				param.add(new BasicNameValuePair("date",day));
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/donation.php");
						
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
					
					if(result.equalsIgnoreCase("Your Donation is Successfully Saved"))
					{
						Toast.makeText(getActivity(), "Donation is Saved", Toast.LENGTH_LONG).show();
						//Intent ii2=new Intent(getApplicationContext(),ScreenLogin.class);
						//startActivity(ii2);
					}
					else
					{
						Toast.makeText(getActivity(), "Please Try Again", Toast.LENGTH_LONG).show();
					}
				}
		   }

	}


