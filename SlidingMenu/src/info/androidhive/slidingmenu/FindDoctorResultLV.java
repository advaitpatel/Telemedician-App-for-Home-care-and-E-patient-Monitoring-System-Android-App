package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FindDoctorResultLV extends Activity
{
	
	ListView listview;
	Intent i;
	int j=0;
	List<String> temp;
	ArrayAdapter<String> arrayad;
	ArrayList<String> doctor;
	ArrayList<String> doctorname=new ArrayList<String>();
	 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_doc_searched_result);
         
		 listview=(ListView)findViewById(R.id.lv_doc_serached_result);

			i = getIntent();
			doctor=i.getStringArrayListExtra("doctor");
			
			try
			{
				for(j=0;j<doctor.size();j++)
				{
					
					
					 temp=Arrays.asList(doctor.get(j).split(","));
					 doctorname.add(temp.get(0)+" "+temp.get(1));
					 
					 listview.setOnItemClickListener(new OnItemClickListener() 
					 {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) 
						{
							//String Selecteditem= doctorname[+position];
							
						}
					});
				}
				
				//Toast.makeText(getApplicationContext(), doctorname.get(0), Toast.LENGTH_LONG).show();
				arrayad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, doctorname);
				listview.setAdapter(arrayad);
			}
			catch(Exception e)
			{
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
}