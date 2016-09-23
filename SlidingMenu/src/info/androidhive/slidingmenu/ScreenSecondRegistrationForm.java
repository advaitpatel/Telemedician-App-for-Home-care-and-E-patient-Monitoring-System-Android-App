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
import android.widget.TextView;
import android.widget.Toast;

public class ScreenSecondRegistrationForm extends Activity
{
	
	String fna,lna,gen,add,bloodgp,dob,mobno,email,una,pass,conpass,dd,mm,yy;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_registration_second_page);
        
         final TextView tv_regform_firstname = (TextView) findViewById(R.id.tv_regform_firstname);
         final TextView tv_regform_lastname = (TextView) findViewById(R.id.tv_regform_lastname);
         final TextView tv_regform_gender = (TextView) findViewById(R.id.tv_regform_gender);
         final TextView tv_regform_address = (TextView) findViewById(R.id.tv_regform_address);
         final TextView tv_regform_bloodgroup = (TextView) findViewById(R.id.tv_regform_bloodgroup);
         final TextView tv_regform_dob = (TextView) findViewById(R.id.tv_regform_dob);
         final TextView tv_regform_mobileno = (TextView) findViewById(R.id.tv_regform_mobileno);
         final TextView tv_regform_emailid = (TextView) findViewById(R.id.tv_regform_emailid);
         final TextView tv_regform_username= (TextView) findViewById(R.id.tv_regform_username);
         final TextView tv_regform_password = (TextView) findViewById(R.id.tv_regform_password);
         final TextView tv_regform_confirmpassword = (TextView) findViewById(R.id.tv_regform_confirmpassword);
        
        
        Button btn_con_reg=(Button) findViewById(R.id.btn_regform_save_data);
        
        Intent i = getIntent();
        // Receiving the Data
        final String et_regform_firstname = i.getStringExtra("et_regform_firstname");
        String et_regform_lastname = i.getStringExtra("et_regform_lastname");
        String spn_regform_gender = i.getStringExtra("spn_regform_gender");
        String et_regform_address = i.getStringExtra("et_regform_address");
        String spn_regform_bloodgroup = i.getStringExtra("spn_regform_bloodgroup");
        String btn_regform_dob = i.getStringExtra("btn_regform_dob");
        String et_regform_mobileno = i.getStringExtra("et_regform_mobileno");
        String et_regform_emailid = i.getStringExtra("et_regform_emailid");
        String et_regform_username = i.getStringExtra("et_regform_username");
        String et_regform_password = i.getStringExtra("et_regform_password");
        String et_regform_confirmpassword = i.getStringExtra("et_regform_confirmpassword");
        
        
        // Displaying Received data
        tv_regform_firstname.setText(et_regform_firstname);
        tv_regform_lastname.setText(et_regform_lastname);
        tv_regform_gender.setText(spn_regform_gender);
        tv_regform_address.setText(et_regform_address);
        tv_regform_bloodgroup.setText(spn_regform_bloodgroup);
        tv_regform_dob.setText(btn_regform_dob);
        tv_regform_mobileno.setText(et_regform_mobileno);
        tv_regform_emailid.setText(et_regform_emailid);
        tv_regform_username.setText(et_regform_username);
        tv_regform_password.setText(et_regform_password);
        tv_regform_confirmpassword.setText(et_regform_confirmpassword);
        
        
        // Binding Click event to Button //with database coding
        btn_con_reg.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View arg0)
			{
				
				//Toast.makeText(getApplicationContext(), "Send Thai Gayu Bhai", Toast.LENGTH_LONG).show();
				
				String fna=tv_regform_firstname.getText().toString();
				String lna=tv_regform_lastname.getText().toString();
				String gen=tv_regform_gender.getText().toString();
				String add=tv_regform_address.getText().toString();
				String bloodgp=tv_regform_bloodgroup.getText().toString();
				String dob=tv_regform_dob.getText().toString();
				String mobno=tv_regform_mobileno.getText().toString();
				String email=tv_regform_emailid.getText().toString();
				String una=tv_regform_username.getText().toString();
				String pass=tv_regform_password.getText().toString();
				//String conpass=tv_regform_confirmpassword.getText().toString();
				
				//Class
				Registration reg=new Registration(fna, lna, gen, add, bloodgp, dob, mobno,email,una,pass);
				reg.execute();
				
				//Toast.makeText(getApplicationContext(), "Jo Baka Thai Gayu", Toast.LENGTH_LONG).show();
				//Closing SecondScreen Activity
				//finish();
			}
		});
        
    }
    
    class Registration extends AsyncTask<String, Void, String>
    {
    	
    	public Registration(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j)
    	{
    		
    		fna=a;
    		lna=b;
    		gen=c;
    		add=d;
    		bloodgp=e;
    		dob=f;
    		mobno=g;
    		email=h;
    		una=i;
    		pass=j;
    		//conpass=k;
    		
		}

		@Override
		protected String doInBackground(String... params)
		{
						
			String data="";
			InputStream is=null;
			
			
			
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			
		//	param.add(new BasicNameValuePair("col_name",stringname));	
			param.add(new BasicNameValuePair("user",una));
			param.add(new BasicNameValuePair("pass",pass));
			param.add(new BasicNameValuePair("First_name",fna));
			param.add(new BasicNameValuePair("Last_name",lna));
			param.add(new BasicNameValuePair("Gender",gen));
			param.add(new BasicNameValuePair("Address",add));
			param.add(new BasicNameValuePair("Mobile",mobno));
			param.add(new BasicNameValuePair("Email",email));
			param.add(new BasicNameValuePair("Blood_Group",bloodgp));
			param.add(new BasicNameValuePair("dob",dob));
			
		
			
			try
			{
				HttpClient client=new DefaultHttpClient();
				HttpPost post=new HttpPost("http://herry.cuccfree.com/register.php");
					
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
			
			if(result.equals("Registered Successfully"))
			{
				Toast.makeText(getApplicationContext(), "Registered Succefull", Toast.LENGTH_LONG).show();
				Intent ii=new Intent(getApplicationContext(),LoginClass.class);
				startActivity(ii);
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}
		}
    }

}
