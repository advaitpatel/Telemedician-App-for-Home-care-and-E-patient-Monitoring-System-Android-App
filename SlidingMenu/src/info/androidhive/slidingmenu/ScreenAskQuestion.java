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
import android.graphics.Bitmap;
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

public class ScreenAskQuestion extends Fragment
{
	
	String u,q,ttl,speciality,imgs;
	
	EditText username,quest_title,quest;
	Button uploadimg,askquest;
	Spinner doc_spec;
	private static final int CAMERA_REQUEST = 1898;
	ImageView imgview;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
	{
 
        View rootView = inflater.inflate(R.layout.screen_ask_question, container, false);
        
        askquest=(Button)rootView.findViewById(R.id.ask_btn_ask_quest);
        
        uploadimg=(Button) rootView.findViewById(R.id.ask_btn_uploadImage);
        
        imgview=(ImageView)rootView.findViewById(R.id.imgview_ask_quest);
        username=(EditText)rootView.findViewById(R.id.ask_et_askquest_una);
        quest=(EditText)rootView.findViewById(R.id.ask_question_whole_quest);
        quest_title=(EditText)rootView.findViewById(R.id.ask_et_askquest_quest_title);
        doc_spec=(Spinner)rootView.findViewById(R.id.ask_spn_doctor);
        
        
        uploadimg.setOnClickListener(new View.OnClickListener()
        {  
 
	        @Override  
	        public void onClick(View v)
	        {  
	             Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
	             
	             startActivityForResult(cameraIntent, CAMERA_REQUEST);  
	        }  
	       });  
        
        askquest.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v)
			{
				
				//Toast.makeText(getActivity(),"Click Thay Che",Toast.LENGTH_LONG).show();
				
				String spec=doc_spec.getSelectedItem().toString();
				String una=username.getText().toString();
				String quest_whole=quest.getText().toString();
				String title=quest_title.getText().toString();
				String img=imgview.getDrawable().toString();
				
				//Toast.makeText(getActivity(), "Click Thya che", Toast.LENGTH_LONG).show();

				if(spec.length()==0)
				{
					
				}
				else if(una.length()==0)
				{
					username.setError("Please Enter Your Name");
				}
				else if(quest_whole.length()==0)
				{
					quest.setError("Please Enter Your Question");
				}
				else if(title.length()==0)
				{
					quest_title.setError("Please Enter Question Title");
				}
				else
				{
					AskQuest ask=new AskQuest(una, spec, title, quest_whole, img);
					ask.execute();
				}
				
			}
		});
        
        return rootView;
	  	}  
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{  
	     if (requestCode == CAMERA_REQUEST)
	     {  
	    	 Bitmap photo = (Bitmap) data.getExtras().get("data");  
	    	 imgview.setImageBitmap(photo);  
	     }  
	}  
        
	
	class AskQuest extends AsyncTask<String, Void, String>
    {
    	
    	public AskQuest(String a,String b,String c,String d,String e)
    	{
    		u=a;
    		speciality=b;
    		ttl=c;
    		q=d;
    		imgs=e;
    	}

		@Override
		protected String doInBackground(String... params) 
		{
			
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("user",u));
				param.add(new BasicNameValuePair("dr_user",q));
				param.add(new BasicNameValuePair("title",speciality));
				param.add(new BasicNameValuePair("question",ttl));
				param.add(new BasicNameValuePair("image",imgs));
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/askquestion.php");
						
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
					
					if(result.equalsIgnoreCase("Your Question is Successfully Saved"))
					{
						Toast.makeText(getActivity(), "Question Asked", Toast.LENGTH_LONG).show();
						//Intent ii2=new Intent(getApplicationContext(),ScreenLogin.class);
						//startActivity(ii2);
					}
					else
					{
						Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_LONG).show();
					}
				}
		    }

		}
