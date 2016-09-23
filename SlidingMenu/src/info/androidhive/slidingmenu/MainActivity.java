package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity
{
	ActionBar actionbar;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
			
		// Launch RateUs.java class
		RateUs.app_launched(this);
		
		/*
		 * 
		 * 
		 * Space For "Rate Us" dialog box
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		//actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#26ce61")));
		
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		
		// Home Screen
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		
		// 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		
		// 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		
		//
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		
		// Find Doctor
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		
		// Find Medical Labs
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		
		// Find Medical Store
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
				
		// User History
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
						
		// Notification, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
				
		
						
		
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) 
		{
			public void onDrawerClosed(View view) 
			{
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView)
			{
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) 
		{
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements	ListView.OnItemClickListener 
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) 
		{
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		
		// Handle action bar actions click
		switch (item.getItemId())
		{
		
			case R.id.action_my_profile:
				Intent i1 = new Intent(getApplicationContext(), ScreenUserProfile.class);
				startActivityForResult(i1, 500);
		        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(i1);
				//Toast.makeText(this, "Account Profile", Toast.LENGTH_SHORT).show();
				return true;
			
	            
	        case R.id.action_faqs: 
	        	Intent i4 = new Intent(getApplicationContext(), ScreenFAQ.class);
	        	startActivityForResult(i4, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(i4); 
	            return true;
	            
	        case R.id.action_ediagnosis: 
	        	Intent i9 = new Intent(getApplicationContext(), ScreenEDiagnosisHome.class);
	        	startActivityForResult(i9, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(i9);
	        	//Toast.makeText(this, "Visit Website", Toast.LENGTH_SHORT).show(); 
	            return true;
	        
	        case R.id.action_doc_home:
	        	Intent i5 = new Intent(getApplicationContext(), ScreenDoctorHomeVisit.class);
	        	startActivityForResult(i5, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
				startActivity(i5); 
	            return true;
	         
	        case R.id.action_doc_lv:
	        	Intent i6=new Intent(getApplicationContext(), ScreenDoctorListView.class);
	        	startActivityForResult(i6, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
	        	startActivity(i6);
	        	return true;
	        /*    	
	        case R.id.action_login:
	        	Intent i7=new Intent(getApplicationContext(), LoginClass.class);
	        	startActivityForResult(i7, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
	        	startActivity(i7);
	        	return true;
	        	
	        case R.id.action_reg:
	        	Intent i8=new Intent(getApplicationContext(), ScreenRegistration.class);
	        	startActivityForResult(i8, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
	        	startActivity(i8);
	        	return true;
	        	*/
	        case R.id.action_trial_notification:
	        	Intent i11=new Intent(getApplicationContext(), UserNotificationAnswer.class);
	        	startActivityForResult(i11, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
	        	startActivity(i11);
	        	return true;
	        	
	        case R.id.action_preference_manager:
	        	Intent i12=new Intent(getApplicationContext(), PreferenceManagerScreen.class);
	        	startActivityForResult(i12, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
	        	startActivity(i12);
	        	return true;
	        	
	        case R.id.action_contact_us:
	        	Intent i13=new Intent(getApplicationContext(), ScreenContactUs.class);
	        	startActivityForResult(i13, 500);
	            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_right);
	        	startActivity(i13);
	        	return true;
	        
	       
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_my_profile).setVisible(!drawerOpen);
		//jyare navigation drawer open hoy tyare option menu ne hide karvu hoy to
		//menu.findItem(R.id.action_profile).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) 
	{
		// update the main content by replacing fragments
		
		Fragment fragment = null;
		
		switch (position) 
		{
		
			case 0:
				fragment = new ScreenHome();
				break;
			case 1:
				fragment = new ScreenNotification();
				break;
			case 2:
				fragment = new ScreenAppointment();
				break;
			case 3:
				fragment = new ScreenFindDoctor();
				break;
			case 4:
				fragment = new ScreenFindMedicalStore();
				break;
			case 5:
				fragment = new ScreenFindMedicalLabs();
				break;
			case 6:
				fragment = new ScreenAskQuestion();
				break;
			case 7:
				fragment = new ScreenEmergency();
				break;
		case 8:
				fragment = new ScreenDonateBodyPart();
				break;			
			
		default:
			break;
		}

		if (fragment != null)
		{
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
		else
		{
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void onBackPressed()
	{
		//Display alert message when back button has been pressed
		backButtonHandler();
		return;
	}
	
	public void backButtonHandler()
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
		
		// Setting Dialog Title
		alertDialog.setTitle("Leave application?");
		
		// Setting Dialog Message
		alertDialog.setMessage("Are you sure you want to leave the application?");
		
		// Setting Icon to Dialog
		alertDialog.setIcon(R.drawable.dialog_icon);
		
		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						finish();
					}
				});
		
		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("NO",
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						// Write your code here to invoke NO event
						dialog.cancel();
					}
				});
		// Showing Alert Message
		alertDialog.show();
	}

	
}
