package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;

public class ScreenAfterSelectDoctorAppointment extends Activity
{
	ListView lv;
    ArrayAdapter<String> arrayad;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_doc_searched_result);

        lv=(ListView)findViewById(R.id.lv_doc_serached_result);

        Intent i = getIntent();
   
        String showg[]=i.getStringArrayExtra("myval");
        
                
        arrayad = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, showg);

        lv.setAdapter(arrayad);

    }

}
