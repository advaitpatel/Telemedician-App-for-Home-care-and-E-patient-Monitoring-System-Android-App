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

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ScreenFindMedicalLabs extends Fragment
{
	Button btn_search_labs;
	Spinner loc;
	List<String> values;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState)
	{

       View rootView = inflater.inflate(R.layout.screen_find_labs, container, false);
       
       btn_search_labs=(Button)rootView.findViewById(R.id.btn_find_medical_labs);
       loc=(Spinner)rootView.findViewById(R.id.spn_loc);
       
       btn_search_labs.setOnClickListener(new View.OnClickListener()
       {
		
		@Override
		public void onClick(View v)
		{
			String labs_loc=loc.getSelectedItem().toString();
			
			if(labs_loc.equals("Click Here - To Choose Location"))
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
				 
				// set title
				alertDialogBuilder.setTitle("Please Select Your Location");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("You have to select a single location")
					.setCancelable(false)
					
					.setNegativeButton("Yes Got it !!",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
			}
			else
			{
				new FindMedicalLabs(labs_loc).execute();
			}
			
		}
	});
       
       
       return rootView;
   }
	
	class FindMedicalLabs extends AsyncTask<String, Void, String>
	{
		
		String findmydoc;
		
		public FindMedicalLabs(String doc_loc)
		{
			findmydoc=doc_loc;
		}
		

		@Override
		protected String doInBackground(String... arg0)
		{
			
			String data="";
			InputStream is=null;
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
			//	param.add(new BasicNameValuePair("col_name",stringname));	
				param.add(new BasicNameValuePair("area",findmydoc));
				
				
				try
				{
					HttpClient client=new DefaultHttpClient();
					HttpPost post=new HttpPost("http://herry.cuccfree.com/searchlabs.php");
						
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
			
			if(result.equals("Doctor not found"))
			{
				Toast.makeText(getActivity(), "not found", Toast.LENGTH_LONG).show();
				//Intent ii=new Intent(getActivity(),ScreenLogin.class);
				//startActivity(ii);
			}
			else
			{
//				Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
				values=Arrays.asList(result.split("\\*"));
				int i=0;
				Intent ii = new Intent(getActivity(), FindDoctorResultLV.class);
				
				try
				{
					ArrayList<String> ss=new ArrayList<String>();
					while(i<(values.size()-1))
					{
					//	Toast.makeText(getActivity(), ""+ss[i].length(), Toast.LENGTH_LONG).show();
						
						ss.add(values.get(i));
						i++;
					}
					for(int j=0;j<(ss.size());j++)
					{
					//	Toast.makeText(getActivity(), ss.get(j), Toast.LENGTH_LONG).show();						
					}
					ii.putStringArrayListExtra("doctor", ss);
					startActivity(ii);
					
					
				}
				catch(Exception e)
				{
					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
	
				
			}
		}
		
	}
   
}