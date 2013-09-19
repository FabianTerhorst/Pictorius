package de.fterhorst.pictoriusvertretungsplan;

import java.util.ArrayList;
import java.util.List;

import com.icechen1.microwavetimepicker.TimePickerDialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class ActivityAdd extends FragmentActivity implements TimePickerDialogFragment.TimePickerDialogHandler{
	Spinner spinner,spinner2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        
        
        spinner = (Spinner) findViewById(R.id.fromhour);
        spinner2 = (Spinner) findViewById(R.id.tohour);
		List<String> list = new ArrayList<String>();
		list.add("Stunde 1");
		list.add("Stunde 2");
		list.add("Stunde 3");
		list.add("Stunde 4");
		list.add("Stunde 5");
		list.add("Stunde 6");
		list.add("Stunde 7");
		list.add("Stunde 8");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		spinner2.setAdapter(dataAdapter);
        

}
	public void opendialog_am(View v){
		FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction ft = manager.beginTransaction();
        //Close pre-existing dialogs if any
        final Fragment prev = manager.findFragmentByTag("time_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        final TimePickerDialogFragment fragment = TimePickerDialogFragment.newInstance();
        fragment.show(ft, "time_dialog");
	}
	/**
	 * Opens a DialogFragment with a 24 Hours TimePicker
	 * @param v
	 */
	public void opendialog(View v){
		FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction ft = manager.beginTransaction();
        //Close pre-existing dialogs if any
        final Fragment prev = manager.findFragmentByTag("time_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        final TimePickerDialogFragment fragment = TimePickerDialogFragment.newInstance();
        Bundle b = new Bundle();
        b.putBoolean("hours_24", true);
        fragment.setArguments(b);
        fragment.show(ft, "time_dialog");
	}
	
	/**
	 * Opens a DialogFragment with a preset time
	 * @param v
	 */
	public void opendialog_preset(View v){
		FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction ft = manager.beginTransaction();
        //Close pre-existing dialogs if any
        final Fragment prev = manager.findFragmentByTag("time_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        final TimePickerDialogFragment fragment = TimePickerDialogFragment.newInstance();
        Bundle b = new Bundle();
        b.putBoolean("hours_24", true);
        b.putInt("hour", 12);
        b.putInt("minute", 12);
        fragment.setArguments(b);
        fragment.show(ft, "time_dialog");
	}
	@Override
	public void onDialogTimeSet(int hourOfDay, int minute) {
		Toast.makeText(getApplicationContext(), hourOfDay+":" +minute, Toast.LENGTH_LONG).show();
		
	}
	
}
