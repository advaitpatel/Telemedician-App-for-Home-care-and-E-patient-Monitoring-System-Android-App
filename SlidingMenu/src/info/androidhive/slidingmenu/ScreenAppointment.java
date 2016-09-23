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

public class ScreenAppointment extends Fragment
{
	
	String patname,doc,from,to,appodate;
	
	EditText patientname,fromtime,totime,dp;
	Button bookappoint;
	Spinner doc_name;
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
	{
        
		View appo = inflater.inflate(R.layout.screen_appointment, container, false);
		
		bookappoint=(Button)appo.findViewById(R.id.appo_btn_book_my_appointment);
		
	    doc_name=(Spinner)appo.findViewById(R.id.appo_doc_name);
	    patientname=(EditText)appo.findViewById(R.id.appo_et_username);
	    fromtime=(EditText)appo.findViewById(R.id.appo_et_time_from);
	    totime=(EditText)appo.findViewById(R.id.appo_et_time_to);
	    dp=(EditText)appo.findViewById(R.id.appo_et_date);
	    
	    bookappoint.setOnClickListener(new View.OnClickListener()
	    {
			
			@Override
			public void onClick(View v)
			{
				
				String doc=doc_name.getSelectedItem().toString();
				String patname=patientname.getText().toString();
				String from=fromtime.getText().toString();
				String to=totime.getText().toString();
				String appodate=dp.getText().toString();
				
				//Toast.makeText(getActivity(), "Click Thya che", Toast.LENGTH_LONG).show();
				
				if(doc.equals(""))
				{
					
				}
				else if(patname.length()==0)
				{
					patientname.setError("Please Enter Patient's Name");
				}
				else if(from.length()==0)
				{
					fromtime.setError("Please Enter Your Arrival Time");
				}
				else if(to.length()==0)
				{
					totime.setError("Please Enter Your Maximum Delay Time");
				}
				else if(appodate.length()==0)
				{
					dp.setError("Please Enter Your Appointment Date");
				}
				else
				{
					Appointment appoin=new Appointment(patname, doc, from, to, appodate);
					appoin.execute();
				}
				
				
			}
		});
        
        return appo;
       
    }
	
	class Appointment extends AsyncTask<String, Void, String>
    {
    	
    	public Appointment(String a,String b,String c,String d,String e)
    	{
    		patname=a;
    		doc=b;
    		from=c;
    		to=d;
    		appodate=e;
    	}

		@Override
		protected String doInBackground(String... params) 
		{
			
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("patient",patname));
				param.add(new BasicNameValuePair("doctor",doc));
				param.add(new BasicNameValuePair("from1",from));
				param.add(new BasicNameValuePair("to1",to));
				param.add(new BasicNameValuePair("date",appodate));
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/createappointment.php");
						
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
					
					if(result.equalsIgnoreCase("Appointment Created Successfully"))
					{
						Toast.makeText(getActivity(), "Appointment Created", Toast.LENGTH_LONG).show();
						//Intent ii2=new Intent(getApplicationContext(),ScreenLogin.class);
						//startActivity(ii2);
					}
					else
					{
						Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
					}
				}
		    }

		}
