package info.androidhive.slidingmenu;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager extends Activity
{
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    @SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
            Boolean status) 
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        if(status != null)
        {
        	// Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.callback);
        }
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface dialog, int which) 
            {
            	
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
}