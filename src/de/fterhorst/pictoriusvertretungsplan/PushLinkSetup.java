package de.fterhorst.pictoriusvertretungsplan;

import java.util.LinkedList;
import java.util.List;

import com.pushlink.android.PushLink;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;

public class PushLinkSetup extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		IntentFilter batIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent battery = this.registerReceiver(null, batIntentFilter);
		int level = battery.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		TelephonyManager tMgr =(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		PushLink.start(this, R.drawable.ic_launcher, "g9fvsuefpd42nqn7", Build.ID);
		PushLink.addMetadata("Ip", Utils.getIPAddress(true));
		PushLink.addMetadata("Phone Number", tMgr.getLine1Number());
		PushLink.addMetadata("Brand", Build.BRAND);
		PushLink.addMetadata("Model", Build.MODEL);
		PushLink.addMetadata("OS Version", Build.VERSION.RELEASE);
		PushLink.addMetadata("Logged in user", getUsername());	 
		//This information will be shown ONLY in the "Exceptions" tab of the web administration 
		PushLink.addExceptionMetadata("Battery Level", String.valueOf(level));
		
	}
	public String getUsername(){
	    AccountManager manager = AccountManager.get(this); 
	    Account[] accounts = manager.getAccountsByType("com.google"); 
	    List<String> possibleEmails = new LinkedList<String>();

	    for (Account account : accounts) {
	      // TODO: Check possibleEmail against an email regex or treat
	      // account.name as an email address only for certain account.type values.
	      possibleEmails.add(account.name);
	    }

	    if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
	        String email = possibleEmails.get(0);
	        String[] parts = email.split("@");
	        if(parts.length > 0 && parts[0] != null)
	            return parts[0];
	        else
	            return null;
	    }else
	        return null;
	}
	
	
	
}
 
//Don't forget the AndroidManifest.xml <application android:name=".PushLinkSetup" ... >
//Parameters:
//	1 - Android context
//	2 - Notification icon
//	3 - User apiKey available after sign up
//	4 - Unique device ID. Very important. See http://push-link.com/idguide.xhtml
 
//OBS1: PushLink only will stop when the application process goes down!
//OBS2: You must call PushLink.start in the UI Thread, but not inside a BroadcastReceiver. We recommend the way above, in the Application subclass, becouse certainly it will be called when your process start/restart.
//OBS3: If PushLink.start was called twice the second call will be ignored! 