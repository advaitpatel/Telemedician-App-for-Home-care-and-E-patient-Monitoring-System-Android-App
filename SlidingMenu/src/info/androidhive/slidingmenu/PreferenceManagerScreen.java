package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class PreferenceManagerScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		if (savedInstanceState == null)
			// Set title
			this.getActionBar().setTitle("Settings Screen");
		getFragmentManager().beginTransaction()
				.add(android.R.id.content, new PrefFragment()).commit();
	}

	public static class PrefFragment extends PreferenceFragment implements
			OnSharedPreferenceChangeListener {
		Context cont;
		Preference list_time, edt_user, buttonrate, buttonplay, buttonfeedback,
				cbp1, cbp2, cbp3, cbp4, cbp5, verison_date;
		ArrayAdapter<String> adaptertime;
		SharedPreferences prefs;

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			cont = getActivity();

			addPreferencesFromResource(R.xml.user_setting);
			list_time = (Preference) findPreference("buttonTime");
			buttonrate = (Preference) findPreference("buttonRate");
			buttonplay = (Preference) findPreference("buttonPlay");
			buttonfeedback = (Preference) findPreference("buttonFeedback");
			edt_user = (Preference) findPreference("username");
			cbp1 = (Preference) findPreference("NotiOnOff");
			cbp2 = (Preference) findPreference("VibrateOnOff");
			cbp3 = (Preference) findPreference("LightOnOff");
			cbp4 = (Preference) findPreference("SoundOnOff");
			cbp5 = (Preference) findPreference("isAutoStartEnabled");

			prefs = PreferenceManager.getDefaultSharedPreferences(cont);
			prefs.registerOnSharedPreferenceChangeListener(this);

			String[] timings = { "2 hour once", "3 hour once", "4 hour once" };

			adaptertime = new ArrayAdapter<String>(cont, R.layout.preference_manager_list_item,
					timings);
			adaptertime
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			list_time
					.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

						@Override
						public boolean onPreferenceChange(
								Preference preference, Object newValue) {
							// TODO Auto-generated method stub
							int newVal = Integer.parseInt((String) newValue);

							Toast.makeText(cont, "Alarm Set to " + newVal,
									Toast.LENGTH_SHORT).show();

							return true;
						}
					});

			cbp1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference,
						Object newValue) 
				{
					// TODO Auto-generated method stub
					if ((Boolean) newValue) 
					{
						int newVal = Integer.parseInt(Read(cont, "buttonTime"));
						cbp1.setSummary("Notification to remind you - ON");
						Toast.makeText(cont, "Alarm Set to " + newVal,
								Toast.LENGTH_SHORT).show();
					} 
					else 
					{
						cbp1.setSummary("Notification to remind you - OFF (Not Recommended)");
					}
					return true;
				}
			});

			cbp2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() 
			{

				@Override
				public boolean onPreferenceChange(Preference preference,
						Object newValue) 
				{
					// TODO Auto-generated method stub
					if ((Boolean) newValue) 
					{
						Toast.makeText(getActivity(), "Vibration On", Toast.LENGTH_SHORT).show();
						cbp2.setSummary("Vibration On");
					} else 
					{
						Toast.makeText(getActivity(), "Vibration Off", Toast.LENGTH_SHORT).show();
						cbp2.setSummary("Vibration Off");
					}
					return true;
				}
			});

			cbp3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference,
						Object newValue) {
					// TODO Auto-generated method stub
					if ((Boolean) newValue) {
						cbp3.setSummary("LED On");
					} else {
						cbp3.setSummary("LED Off");
					}
					return true;
				}
			});

			cbp4.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference,
						Object newValue) {
					// TODO Auto-generated method stub
					if ((Boolean) newValue) {
						cbp4.setSummary("Sound On");
					} else {
						cbp4.setSummary("Sound Off");
					}
					return true;
				}
			});

			cbp5.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() 
			{

				@Override
				public boolean onPreferenceChange(Preference preference,
						Object newValue) 
				{
					// TODO Auto-generated method stub
					if ((Boolean) newValue) 
					{
						cbp5.setSummary("Start Notifications after Rebooting Phone - ON");
					} 
					else 
					{
						cbp5.setSummary("Start Notifications after Reboot - OFF (Not Recommended)");
					}
					return true;
				}
			});

			buttonfeedback
					.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
						@Override
						public boolean onPreferenceClick(Preference arg0) {
							// code for what you want it to do

							String to = "advaitpa93@gmail.com";
							Intent email = new Intent(Intent.ACTION_SEND);
							email.putExtra(Intent.EXTRA_EMAIL,
									new String[] { to });
							email.putExtra(Intent.EXTRA_SUBJECT,
									"Hey !! This is Advait Patel from Vision Medicare");
							email.putExtra(Intent.EXTRA_TEXT, " ");

							// we need setType to prompts only email clients.
							email.setType("message/rfc822");

							startActivity(Intent.createChooser(email,
									"Choose an Email client :"));

							return true;
						}
					});

			
					
		}

		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			// TODO Auto-generated method stub
			getNotifiSetting();

			Editor editor = PreferenceManager.getDefaultSharedPreferences(cont)
					.edit();
			editor.putBoolean("NotifiOnOff",
					prefs.getBoolean("NotiOnOff", true));
			editor.commit();
		}

		private void getNotifiSetting() {
			// TODO Auto-generated method stub

			if (prefs.getBoolean("isAutoStartEnabled", true)) {
				cbp5.setSummary("Start Notifications after Rebooting Phone - ON");
			} else {
				cbp5.setSummary("Start Notifications after Reboot - OFF (Not Recommended)");
			}

			if (prefs.getBoolean("NotiOnOff", true)) {
				cbp1.setSummary("Notification to remind you - ON");
			} else {
				cbp1.setSummary("Notification to remind you - OFF (Not Recommended)");
			}

			if (prefs.getBoolean("VibrateOnOff", true)) {
				cbp2.setSummary("Vibration On");
			} else {
				cbp2.setSummary("Vibration Off");
			}

			if (prefs.getBoolean("LightOnOff", true)) {
				cbp3.setSummary("LED On");
			} else {
				cbp3.setSummary("LED Off");
			}

			if (prefs.getBoolean("SoundOnOff", true)) {
				cbp4.setSummary("Sound On");
			} else {
				cbp4.setSummary("Sound Off");
			}
		}
	}

	// String
	public static String Read(Context context, final String key) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		return pref.getString(key, "");
	}

	public static void Write(Context context, final String key,
			final String value) {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	// Boolean
	public static boolean ReadBoolean(Context context, final String key,
			final boolean defaultValue) {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		return settings.getBoolean(key, defaultValue);
	}

	public static void WriteBoolean(Context context, final String key,
			final boolean value) {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}