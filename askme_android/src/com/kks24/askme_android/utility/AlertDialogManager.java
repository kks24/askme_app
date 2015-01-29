package com.kks24.askme_android.utility;


import com.kks24.askme_android.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
 
public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    public static void showAlertDialog(Context context, String title, String message,
            Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
			}}).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        alertDialog.setCancelable(false);
        
        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
        
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
        
        // Showing Alert Message
        alertDialog.show();
    }
    
    
    public static void showDialog(Context context, String title, String message,
            Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
			}}).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        alertDialog.setCancelable(false);
        
        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
        
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
        
        // Showing Alert Message
        alertDialog.show();
    }
    
}