package de.fterhorst.pictoriusvertretungsplan;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class UserSettingActivity extends SherlockPreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Einstellungen");
		addPreferencesFromResource(R.xml.settings);
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
        //gute farbe = 0xff123456
        String actionbarcolorpref = sharedPrefs.getString("prefActionBar", "0");
     //   if(actionbarcolorpref == "1"){
        	//getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(null)));
    //    setTheme(R.style.Sherlock___Theme);
    //    }else{
        if(actionbarcolorpref == "0"){
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
        }else if(actionbarcolorpref == "1"){
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(actionbarcolorpref)));
        }else if(realcolor(actionbarcolorpref)== true){
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(actionbarcolorpref)));
        }
	}
	public boolean realcolor(String color){
		if(color.length() >=3){
		return true;
		}else{
		return false;	
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			Intent i = new Intent(this, VertretungsPlanActivity.class);
			startActivity(i);
			break;

		}

		return true;
	}
}
