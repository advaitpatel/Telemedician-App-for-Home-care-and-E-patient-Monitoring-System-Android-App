package info.androidhive.slidingmenu;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenRegistrationForm extends Activity
{
	Button gotoverificationscreen,clearfields,btn_date_of_birth;
	private EditText firstname,lastname,address,mobileno,emailid,username,password,confirmpassword;
	Spinner gender,bloodgroup;
	TextView tv_reg_string_for_custom_font;
	
	static final int DATE_DIALOG_ID = 0;
	
	// variables to save user selected date and time
    public  int year,month,day,hour,minute;  
    
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mYear, mMonth, mDay;
    
    // Alert Dialog Manager
 	AlertDialogManager alert = new AlertDialogManager();
	
    public ScreenRegistrationForm()
    {
    	// Assign current Date and Time Values to Variables
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        
        
	}
	
	@Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_registration_form);
		
		//Binding Buttons
		gotoverificationscreen=(Button)findViewById(R.id.btn_regform_go_to_verification);
		Typeface custom_font5 = Typeface.createFromAsset(getAssets(),"DS-DIGIT.TTF");
		gotoverificationscreen.setTypeface(custom_font5);
		
		clearfields=(Button)findViewById(R.id.btn_regform_clear_fields);
		Typeface custom_font51 = Typeface.createFromAsset(getAssets(),"DS-DIGIT.TTF");
		gotoverificationscreen.setTypeface(custom_font51);
		
		btn_date_of_birth=(Button)findViewById(R.id.btn_regform_dob);
		
		//Custom Fonts
		tv_reg_string_for_custom_font=(TextView)findViewById(R.id.tv_registration_string);
		Typeface custom_font6= Typeface.createFromAsset(getAssets(),"Face Your Fears.ttf");
		tv_reg_string_for_custom_font.setTypeface(custom_font6);
		//tv_reg_string_for_custom_font.setShadowLayer(30, 0, 0, Color.WHITE);
		
		
		btn_date_of_birth.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v)
            {
              // Show the DatePickerDialog
                 showDialog(DATE_DIALOG_ID);
                 
            }
        });
		
		//Binding Components of Registration Form
		firstname=(EditText)findViewById(R.id.et_regform_firstname);
		lastname=(EditText)findViewById(R.id.et_regform_lastname);
		gender=(Spinner)findViewById(R.id.spn_regform_gender);
		address=(EditText)findViewById(R.id.et_regform_address);
		bloodgroup=(Spinner)findViewById(R.id.spn_regform_bloodgroup);
		btn_date_of_birth=(Button)findViewById(R.id.btn_regform_dob);
		mobileno=(EditText)findViewById(R.id.et_regform_mobileno);
		emailid=(EditText)findViewById(R.id.et_regform_emailid);
		username=(EditText)findViewById(R.id.et_regform_username);
		password=(EditText)findViewById(R.id.et_regform_password);
		confirmpassword=(EditText)findViewById(R.id.et_regform_confirmpassword);
		
		clearfields.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				firstname.setText("");
				lastname.setText("");
				//gender.setSelection(null);
				address.setText("");
				//bloodgroup.setText("");
				btn_date_of_birth.setText("");
				mobileno.setText("");
				emailid.setText("");
				username.setText("");
				password.setText("");
				confirmpassword.setText("");
			}
		});
		
		gotoverificationscreen.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//Code To Validate Our Fields
				//Convert Them to 'string'
				
				final String fna = firstname.getText().toString();
				final String lna = lastname.getText().toString();
				final String gen = gender.getSelectedItem().toString();
				final String add = address.getText().toString();
				final String bg = bloodgroup.getSelectedItem().toString();
				final String dob = btn_date_of_birth.getText().toString();
				final String mob = mobileno.getText().toString();
				final String email = emailid.getText().toString();
				final String una = username.getText().toString();
				final String pass = password.getText().toString();
				final String conpass = confirmpassword.getText().toString();
				
				if(fna.length()==0 || lna.length()==0 || dob.length()==0 || mob.length()==0 || email.length()==0 || una.length()==0 || pass.length()==0 || conpass.length()==0)
				{
					alert.showAlertDialog(ScreenRegistrationForm.this, "Registration Failed..", "All Fields are Required ", false);
				}
				else if(fna.length()==0)
				{
					firstname.setError("Please Enter First Name");
				}
				else if(!fna.matches("[a-zA-Z]+"))
				{
	
					firstname.setError("Name Field Contains only Alphabetical Characters");
				}
				else if(lna.length()==0)
				{
					lastname.setError("Please Enter Last Name");
				}
				else if(gen.length()==0)
				{
					
				}
				else if(add.length()==0)
				{
					address.setError("Address is required");
				}
				else if(bg.length()==0)
				{
					
				}
				else if(dob.length()==0)
				{
					btn_date_of_birth.setError("Birth Date is Important for us");
				}
				else if(mob.length()==0)
				{
					mobileno.setError("Please Enter Mobile Number");
				}
				else if(!mob.matches("[0-9]{10}"))
				{
					mobileno.setError("Enter Valid Phone Number");
				}
				else if(email.length()==0)
				{
					emailid.setError("Please Enter Email Id");
				}
				else if(!isValidEmail(email))
				{
					emailid.requestFocus();
					emailid.setError("Invalid Email");
				}
				else if(una.length()==0)
				{
					username.setError("Please Enter Username");
				}
				else if(pass.length()==0)
				{
					password.setError("Please Enter Password");
				}
				else if(conpass.length()==0)
				{
					confirmpassword.setError("Please Confirm Your Password");
				}
				else if(!conpass.equals(pass))
				{
					
					alert.showAlertDialog(ScreenRegistrationForm.this, "Password Failed..", "Your Confirm Password and Password must be same ", false);
					//confirmpassword.setError("Password and Confirm Password are different");
				}
				else
				{
					Intent date_send=new Intent(getApplicationContext(),ScreenSecondRegistrationForm.class);
					
					//Sending data to another Activity
					date_send.putExtra("et_regform_firstname", firstname.getText().toString());
					date_send.putExtra("et_regform_lastname", lastname.getText().toString());
					date_send.putExtra("spn_regform_gender", gender.getSelectedItem().toString());
					date_send.putExtra("et_regform_address", address.getText().toString());
					date_send.putExtra("spn_regform_bloodgroup", bloodgroup.getSelectedItem().toString());
					date_send.putExtra("btn_regform_dob", btn_date_of_birth.getText().toString());
					date_send.putExtra("et_regform_mobileno", mobileno.getText().toString());
					date_send.putExtra("et_regform_emailid", emailid.getText().toString());
					date_send.putExtra("et_regform_username", username.getText().toString());
					date_send.putExtra("et_regform_password", password.getText().toString());
					date_send.putExtra("et_regform_confirmpassword", confirmpassword.getText().toString());
					
					startActivity(date_send);
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
            
            if(year>2015)
            {
            	// Inflate the Layout
	            LayoutInflater inflater = getLayoutInflater();
	            View layout = inflater.inflate(R.layout.custom_toast_cancel,
	                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
	 
	            // Create Custom Toast
	            Toast toast = new Toast(getApplicationContext());
	            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	            toast.setDuration(Toast.LENGTH_LONG);
	            toast.setView(layout);
	            toast.show();
            	//Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else if(year>=2015)
            {
            	if(month==5)
            	{
            		if(day>29)
            		{
            			LayoutInflater inflater = getLayoutInflater();
        	            View layout = inflater.inflate(R.layout.custom_toast_cancel,
        	                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
        	 
        	            // Create Custom Toast
        	            Toast toast = new Toast(getApplicationContext());
        	            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        	            toast.setDuration(Toast.LENGTH_LONG);
        	            toast.setView(layout);
        	            toast.show();
            		}
            		else
            		{
            			btn_date_of_birth.setText(day+"-"+(month+1)+"-"+year);
            		}
            	}
            	else
            	{
            		btn_date_of_birth.setText(day+"-"+(month+1)+"-"+year);
            	}
            }
            
            else
            {
            	// Set the Selected Date in Select date Button
                btn_date_of_birth.setText(day+"-"+(month+1)+"-"+year);
            }
            
            
            
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
           return new DatePickerDialog(this,mDateSetListener,mYear, mMonth, mDay);
           
                           
      }
   	 	return null;
    }
    
		// validating email id
		private boolean isValidEmail(String email)
		{
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
			
}