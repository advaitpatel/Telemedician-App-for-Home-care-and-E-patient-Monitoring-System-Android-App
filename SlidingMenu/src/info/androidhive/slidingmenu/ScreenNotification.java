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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ScreenNotification extends Fragment
{
	
	Button setalarm;
	
	
	//private static final String ALARM_SERVICE = null;

	String title,name,reason,doc_note,way,days,pill_size,time;
		
	EditText medi_title,medi_name,medi_reason,medi_doc_note,medi_time;
	
	Button setnotif;
	Spinner medi_way,medi_days,medi_pill_size;
	ImageView iv;
	Button b[]=new Button[7];
	//int bid[]={R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7};
	
	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
	{
 
        View root= inflater.inflate(R.layout.screen_make_notification, container, false);
        
     
        
        medi_title=(EditText)root.findViewById(R.id.noti_title);
        medi_name=(EditText)root.findViewById(R.id.noti_medi_name);
        medi_reason=(EditText)root.findViewById(R.id.noti_reason);
        medi_doc_note=(EditText)root.findViewById(R.id.noti_doc_note);
        medi_way=(Spinner)root.findViewById(R.id.noti_spn_way);
        medi_days=(Spinner)root.findViewById(R.id.noti_spn_day);
        medi_time=(EditText)root.findViewById(R.id.noti_time);
        medi_pill_size=(Spinner)root.findViewById(R.id.noti_spn_pill_size);
        
        /*
        iv = (ImageView)root.findViewById(R.id.imageView1);
		
		for(int i=0;i<7;i++)
		{
			b[i]=(Button)root.findViewById(bid[i]);
			
			b[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Button b1=(Button)v;
					iv.setImageDrawable(b1.getBackground());
					
				}
			});
		}
        */
        
        setalarm=(Button)root.findViewById(R.id.setAlarm);
        
        setalarm.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				Intent ii = new Intent(getActivity(),SetAlarm.class);
				startActivity(ii);
				
			}
		});
        
        setnotif=(Button)root.findViewById(R.id.notification_generate);
        
        setnotif.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v)
			{
				
				String title=medi_title.getText().toString();
				String name=medi_name.getText().toString();
				String reason=medi_reason.getText().toString();
				String doc_note=medi_doc_note.getText().toString();
				String way=medi_way.getSelectedItem().toString();
				String days=medi_days.getSelectedItem().toString();
				String pill_size=medi_pill_size.getSelectedItem().toString();
				String time=medi_time.getText().toString();
				
				if(title.length()==0)
				{
					//alert.showAlertDialog(getActivity(), "Your Request can not be accepted", "All Fields are required", false);
					medi_title.setError("Please Enter Medicine Title");
				}
				else if(name.length()==0)
				{
					medi_name.setError("Please Enter Medicine Name");
				}
				else if(reason.length()==0)
				{
					medi_reason.setError("Please Enter Reason for medicine");
				}
				else if(doc_note.length()==0)
				{
					medi_doc_note.setError("Please Enter Doctor's note");
				}
				else if(days.length()==0)
				{
					medi_time.setError("Please Enter Days");
				}
				else if(time.length()==0)
				{
					medi_name.setError("Please Enter Time");
				}
				
				else
				{
					Notification not=new Notification(title, name, reason, doc_note, way ,days,time, pill_size);
					not.execute();
					
					//startAlert();
				}
			
			}
			
			
		});
        
        return root;
        
    }
    
	class Notification extends AsyncTask<String, Void, String>
	{
		public Notification(String a,String b,String c,String d,String e,String f,String g, String h) 
		{
			title=a;
			name=b;
			reason=c;
			doc_note=d;
			way=e;
			days=f;
			time=g;
			pill_size=h;
			
		}

		@Override
		protected String doInBackground(String... params) 
		{
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("title",title));
				param.add(new BasicNameValuePair("med_name",name));
				param.add(new BasicNameValuePair("reason",reason));
				param.add(new BasicNameValuePair("doc_note",doc_note));
				param.add(new BasicNameValuePair("way_of_pill",way));
				param.add(new BasicNameValuePair("days",days));
				param.add(new BasicNameValuePair("time",time));
				param.add(new BasicNameValuePair("pill_size",pill_size));
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/notification.php");
						
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
					
					if(result.equalsIgnoreCase("Notification Add Successfully"))
					{
						Toast.makeText(getActivity(), "Thai Gayu Add", Toast.LENGTH_LONG).show();
						//Intent ii2=new Intent(getActivity(),Screen.class);
						//startActivity(ii2);
					}
					else
					{
						Toast.makeText(getActivity(), "Nai Thayu Add", Toast.LENGTH_LONG).show();
					}
				}
		    }
	
	
}
