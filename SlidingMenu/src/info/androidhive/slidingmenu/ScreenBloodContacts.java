package info.androidhive.slidingmenu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



public class ScreenBloodContacts extends Activity
{
	ListView list;
	 String[] itemname =
		 {
			 "Vision Medicare",
			 "Help2Baroda.in",
			 "Indian Red Cross Society",
			 "SSG Hospital",
			 "Vision Medicare",
			 "Help2Baroda.in",
			 "Indian Red Cross Society",
			 "SSG Hospital",
			 "Vision Medicare",
			 "Help2Baroda.in",
			 "Indian Red Cross Society",
			 "SSG Hospital"
			 
		 };
	 
	  Integer[] imgid=
		 {
			 R.drawable.doc_ask_quest,
			 R.drawable.doc_emerg,
			 R.drawable.doc_find_best_doc,
			 R.drawable.doc_home,
			 R.drawable.doc_make_appointment,
			 R.drawable.doc_make_appointment,
			 R.drawable.doc_medicine_search,
			 R.drawable.doc_notification,
			 R.drawable.doc_search_labs,
			 R.drawable.doc_ask_quest,
			 R.drawable.doc_emerg,
			 R.drawable.doc_find_best_doc,
		 };
	 
	 
	 protected void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.screen_blood_contacts);
		 
		 CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
		 list=(ListView)findViewById(R.id.list);
		 list.setAdapter(adapter);
	 
		 // Register the ListView  for Context menu
	     // registerForContextMenu(list);
		 
		 
		 list.setOnItemClickListener(new OnItemClickListener()
		 {
	 
			 @Override
			 public void onItemClick(AdapterView<?> parent, View view,
			 int position, long id)
			 {
				 
				 String Selecteditem= itemname[+position];
				 //Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_LONG).show();
				 
				 if(Selecteditem.equalsIgnoreCase("vision medicare"))
				 {
					 Intent i1=new Intent(getApplicationContext(),EmergencyVisionMedicare.class);
					 startActivityForResult(i1, 500);
				     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
					 startActivity(i1);
				 }
				 else if(Selecteditem.equalsIgnoreCase("Indian Red Cross Society"))
				 {
					 Intent i3=new Intent(getApplicationContext(),EmergencyIndianRedcross.class);
					 startActivityForResult(i3, 500);
				     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
					 startActivity(i3);	 
				 }
				 else if(Selecteditem.equalsIgnoreCase("ssg hospital"))
				 {
					 Intent i4=new Intent(getApplicationContext(),EmergencySSG.class);
					 startActivityForResult(i4, 500);
				     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
					 startActivity(i4);
				 }
				 else if(Selecteditem.equalsIgnoreCase("help2baroda.in"))
				 {
					 Intent i2=new Intent(getApplicationContext(),EmergencyHelp2Baroda.class);
					 startActivityForResult(i2, 500);
				     overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
					 startActivity(i2);
				 }
				 
				 
			 }
		 });
		 
	 }
   
}