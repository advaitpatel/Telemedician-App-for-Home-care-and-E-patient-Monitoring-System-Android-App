package info.androidhive.slidingmenu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScreenContactUs extends Activity
{
	
	EditText fna,lna,mno,email,detail;
	Button sendmail;
	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_contact_us);
		 
		 fna=(EditText)findViewById(R.id.contact_firstname);
		 lna=(EditText)findViewById(R.id.contact_lastname);
		 mno=(EditText)findViewById(R.id.contact_contactno);
		 email=(EditText)findViewById(R.id.contact_emailid);
		 detail=(EditText)findViewById(R.id.contact_desc);
		 
		 sendmail=(Button)findViewById(R.id.contact_send_my_email);
		 
		 sendmail.setOnClickListener(new View.OnClickListener() 
		 {
			
			@Override
			public void onClick(View v) 
			{
				String firstname = fna.getText().toString();
				String lastname = lna.getText().toString();
				String mobile = mno.getText().toString();
				//String mail = email.getText().toString();
				String desc = detail.getText().toString();
				
				if(firstname.length()==0)
				{
					fna.setError("Please Enter First Name");
				}
				else if(lastname.length()==0)
				{
					lna.setError("Please Enter Last Name");
				}
				else if(!mobile.matches("[0-9]{10}"))
				{
					mno.setError("Enter Valid Phone Number");
				}
				else if(mobile.length()==0)
				{
					mno.setError("Please Enter Mobile Number");
				}
				else if(desc.length()==0)
				{
					detail.setError("Please Enter Some Details");
				}
				else
				{
					Intent email = new Intent(Intent.ACTION_SEND);
					email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "rajeshp412@gmail.com"});
					email.putExtra(Intent.EXTRA_SUBJECT, "I have some doubts about Vision Medicare.");
					email.putExtra(Intent.EXTRA_TEXT, "Hello Vision Medicare. I am "+firstname+" "+lastname+"." + " My Contact Number is "+mobile+"."+""+desc);
					 
					//need this to prompts email client only
					email.setType("message/rfc822");
					 
					startActivity(Intent.createChooser(email, "Choose an Email client :"));
				}
				
			}
		});
	}
			
		
}
