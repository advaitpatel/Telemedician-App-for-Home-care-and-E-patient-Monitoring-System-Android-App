package info.androidhive.slidingmenu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

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
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class ScreenDoctorHomeVisit extends Activity
{
	
	String rela,name,mobno,addr,days,time,instr;
	
	EditText una,contno,address,inst;
	
	Spinner relation;
	
	Button savevisit;
	
	Button btnSelectDate,btnSelectTime;
    
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    
    // variables to save user selected date and time
    public  int year,month,day,hour,minute;  
    
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mYear, mMonth, mDay,mHour,mMinute;
	
    // constructor
    
    public ScreenDoctorHomeVisit()
    {
    	// Assign current Date and Time Values to Variables
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
	}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home_visit);
        
        // get the references of buttons
        btnSelectDate=(Button)findViewById(R.id.book_home_date);
        btnSelectTime=(Button)findViewById(R.id.book_home_time);
        
        savevisit=(Button)findViewById(R.id.book_home_btn_book_home_visit);
        //EditText una,contno,address,bookdate,booktime;
        
        inst=(EditText)findViewById(R.id.book_home_instructions);
        
        relation=(Spinner)findViewById(R.id.book_home_spn_relation);
        una=(EditText)findViewById(R.id.book_home_et_name);
        contno=(EditText)findViewById(R.id.book_home_mobile_no);
        address=(EditText)findViewById(R.id.book_home_address);
        
       
        // Set ClickListener on btnSelectDate 
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v)
            {
                // Show the DatePickerDialog
                 showDialog(DATE_DIALOG_ID);
            }
        });
        
        // Set ClickListener on btnSelectTime
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // Show the TimePickerDialog
                 showDialog(TIME_DIALOG_ID);
            }
        });
    
        savevisit.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) 
			{
				
				String rela=relation.getSelectedItem().toString();
				String name=una.getText().toString();
				String mobno=contno.getText().toString();
				String addr=address.getText().toString();
				String instr=inst.getText().toString();
				String time=btnSelectTime.getText().toString();
				String days=btnSelectDate.getText().toString();
				
				if(name.length()==0)
				{
					una.setError("Please Enter Name");
				}
				else if(mobno.length()==0)
				{
					contno.setError("Please Enter Mobile Number");
				}
				else if(addr.length()==0)
				{
					address.setError("Please Enter Address");
				}
				else if(instr.length()==0)
				{
					inst.setError("Please Enter Instructions");
				}
				else if(time.length()==0)
				{
					btnSelectTime.setError("Please Enter Time");
				}
				else if(days.length()==0)
				{
					btnSelectDate.setError("Please Enter Date");
				}
				else
				{
					HomeVisit visit=new HomeVisit(rela,name,mobno,addr,days,time,instr);
					visit.execute();
				}
				
				
			}
		});
      
    }
    
    
    
     // Register  DatePickerDialog listener
     DatePickerDialog.OnDateSetListener mDateSetListener =new DatePickerDialog.OnDateSetListener()
     {
         // the callback received when the user "sets" the Date in the DatePickerDialog
         public void onDateSet(DatePicker view, int yearSelected,int monthOfYear, int dayOfMonth)
         {
        	 year = yearSelected;
             month = monthOfYear;
             day = dayOfMonth;
             
             // Set the Selected Date in Select date Button
             btnSelectDate.setText(day+"-"+month+"-"+year);
             //To set color 
             //btnSelectDate.setTextColor(Color.parseColor("#ff0000"));
         }
     };

     // Register  TimePickerDialog listener                 
     TimePickerDialog.OnTimeSetListener mTimeSetListener =new TimePickerDialog.OnTimeSetListener()
     {
     //the callback received when the user "sets" the TimePickerDialog in the dialog
    	 public void onTimeSet(TimePicker view, int hourOfDay, int min)
    	 {
    		 hour = hourOfDay;
             minute = min;
             // Set the Selected Date in Select date Button
             btnSelectTime.setText(hour+"-"+minute);
                                        
    	 }
     };


    // Method automatically gets Called when you call showDialog()  method
     @Override
     protected Dialog onCreateDialog(int id)
     {
    	 switch (id)
    	 {
    	 	case DATE_DIALOG_ID:
            
    	 		// create a new DatePickerDialog with values you want to show 
            return new DatePickerDialog(this,mDateSetListener,mYear, mMonth+1, mDay);
            
            // create a new TimePickerDialog with values you want to show 
            case TIME_DIALOG_ID:
            return new TimePickerDialog(this,mTimeSetListener, mHour, mMinute+2, false);
                               
          }
    	 	return null;
     }
           
     class HomeVisit extends AsyncTask<String, Void, String>
     {
     	
     	public HomeVisit(String a,String b,String c,String d,String e,String f,String g)
     	{
     		rela=a;
     		name=b;
     		mobno=c;
     		addr=d;
     		days=e;
     		time=f;
     		instr=g;
     		
     		
     	}

 		@Override
 		protected String doInBackground(String... params) 
 		{
 			
 			String data="";
 			InputStream is=null;
 			
 			List<NameValuePair> param=new ArrayList<NameValuePair>();
 			
 			//	param.add(new BasicNameValuePair("col_name",stringname));	
 				param.add(new BasicNameValuePair("relation",rela));
 				param.add(new BasicNameValuePair("name",name));
 				param.add(new BasicNameValuePair("number",mobno));
 				param.add(new BasicNameValuePair("address",addr));
 				param.add(new BasicNameValuePair("date",days));
 				param.add(new BasicNameValuePair("time",time));
 				param.add(new BasicNameValuePair("notes",instr));
 				
 				try
 				{
 					HttpClient client=new DefaultHttpClient();
 					HttpPost post=new HttpPost("http://herry.cuccfree.com/bookhomevisit.php");
 						
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
 					
 					if(result.equalsIgnoreCase("Your Request For Home Visit is Saved"))
 					{
 						Toast.makeText(getApplicationContext(), "We got your request", Toast.LENGTH_LONG).show();
 						//Intent ii2=new Intent(getApplicationContext(),ScreenLogin.class);
 						//startActivity(ii2);
 					}
 					else
 					{
 						Toast.makeText(getApplicationContext(), "Please Try Again", Toast.LENGTH_LONG).show();
 					}
 				}
 		   }

 	}



     
            

	