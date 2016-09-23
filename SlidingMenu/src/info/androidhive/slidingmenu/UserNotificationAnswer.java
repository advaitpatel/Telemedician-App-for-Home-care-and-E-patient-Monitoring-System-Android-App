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

public class UserNotificationAnswer extends Activity
{
	
	TextView title,name,reason,note,way,days,time,size;
	List<String> values;
	Button btn_noti;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.user_notifiation_page);
        
        title=(TextView)findViewById(R.id.noti_reply_title);
        name=(TextView)findViewById(R.id.noti_reply_medi_name);
        reason=(TextView)findViewById(R.id.noti_reply_reason);
        note=(TextView)findViewById(R.id.noti_reply_doc_note);
        way=(TextView)findViewById(R.id.noti_reply_byway);
        days=(TextView)findViewById(R.id.noti_reply_days);
        time=(TextView)findViewById(R.id.noti_reply_time);
        size=(TextView)findViewById(R.id.noti_reply_pill_size);
        
        btn_noti=(Button)findViewById(R.id.notification_generate);
        new FindNotification("5").execute();
        
	}
	
	class FindNotification extends AsyncTask<String, Void, String>
	{
		
		String findnotification;
		
		public FindNotification(String doc_loc)
		{
			findnotification=doc_loc;
		}
		

		@Override
		protected String doInBackground(String... arg0)
		{
			
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("time",findnotification));
				
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/findnotification.php");
						
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
					Toast.makeText(getApplicationContext(), "error"+e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
				
			return data;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			
			if(result.equals(""))
			{
				Toast.makeText(getApplicationContext(), "No notification", Toast.LENGTH_LONG).show();
				
			}
			else
			{
				values = Arrays.asList(result.split(","));
				
				title.setText("Title : "+values.get(0));
				name.setText("Name : "+values.get(1));
				reason.setText("Reason : "+values.get(2));
				note.setText("Note : "+values.get(3));
				way.setText("Way for Medicine : "+values.get(4));
				days.setText("Days : "+values.get(5));
				time.setText("Time : "+values.get(6));
				size.setText("Size of pill :"+values.get(7));
				
				//resul.setText(result.toString());
				//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}
		}
	}
}
