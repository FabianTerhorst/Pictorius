package de.fterhorst.pictoriusvertretungsplan;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.icechen1.microwavetimepicker.TimePickerDialogFragment;

import de.fterhorst.pictoriusvertretungsplan.adapter.MySimpleArrayAdapter;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivityAdd extends FragmentActivity implements TimePickerDialogFragment.TimePickerDialogHandler {
Spinner spinnerhour1,spinnerhour2,spinnerday,spinnertimefrom,spinnertimeto;
String dayString,hourString1,hourString2;
EditText editTextName,editTextRoom;
ArrayList<String> listItems=new ArrayList<String>(); 
ArrayList<String> listItems_room=new ArrayList<String>(); 
ArrayList<String> listItems_from=new ArrayList<String>(); 
ArrayList<String> listItems_to=new ArrayList<String>(); 
ArrayList<String> listItems_hour1=new ArrayList<String>();
ArrayList<String> listItems_hour2=new ArrayList<String>();
ArrayAdapter<String> adapter1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
		setTheme(R.style.Sherlock___Theme);
		
		
		
		Button button = (Button) findViewById(R.id.timefrom);
		Button button2 = (Button) findViewById(R.id.timeto);
		 spinnerhour1 = (Spinner) findViewById(R.id.hour1);
		 spinnerhour2 = (Spinner) findViewById(R.id.hour2);
		 spinnerday = (Spinner) findViewById(R.id.day);
			List<String> listhour1 = new ArrayList<String>();
			List<String> listhour2 = new ArrayList<String>();
			List<String> listday = new ArrayList<String>();
			listhour1.add("Stunde 1");
			listhour1.add("Stunde 2");
			listhour1.add("Stunde 3");
			listhour1.add("Stunde 4");
			listhour1.add("Stunde 5");
			listhour1.add("Stunde 6");
			listhour1.add("Stunde 7");
			listhour1.add("Stunde 8");
			
			listhour2.add("Stunde 1");
			listhour2.add("Stunde 2");
			listhour2.add("Stunde 3");
			listhour2.add("Stunde 4");
			listhour2.add("Stunde 5");
			listhour2.add("Stunde 6");
			listhour2.add("Stunde 7");
			listhour2.add("Stunde 8");
			
			listday.add("Montag");
			listday.add("Dienstag");
			listday.add("Mitttwoch");
			listday.add("Donnerstag");
			listday.add("Freitag");
			ArrayAdapter<String> dataAdapterhour1 = new ArrayAdapter<String>(this, R.layout.spinner_item,listhour1);
			ArrayAdapter<String> dataAdapterhour2 = new ArrayAdapter<String>(this, R.layout.spinner_item,listhour2);
			ArrayAdapter<String> dataAdapterday = new ArrayAdapter<String>(this, R.layout.spinner_item,listday);//simple_spinner_item
			dataAdapterhour1.setDropDownViewResource(R.layout.spinner_dropdown_item);
			dataAdapterhour2.setDropDownViewResource(R.layout.spinner_dropdown_item);
			dataAdapterday.setDropDownViewResource(R.layout.spinner_dropdown_item);//simple_spinner_dropdown_item
			spinnerhour1.setAdapter(dataAdapterhour1);
			spinnerhour2.setAdapter(dataAdapterhour2);
			spinnerday.setAdapter(dataAdapterday);
			
			String.valueOf(spinnerhour1.getSelectedItem());
			String.valueOf(spinnerhour2.getSelectedItem());
			String.valueOf(spinnerday.getSelectedItem());
			hourString1 = String.valueOf(spinnerhour1.getSelectedItem());
			hourString2 = String.valueOf(spinnerhour2.getSelectedItem());
			dayString = String.valueOf(spinnerday.getSelectedItem());
			Button add = (Button) findViewById(R.id.add);
			editTextName = (EditText) findViewById(R.id.editTextName);
			editTextRoom = (EditText) findViewById(R.id.editTextRoom);
			add.setOnClickListener(new OnClickListener(){
		        public void onClick(View view) {
		        String Name = editTextName.getText().toString();
		        String roomNumber = editTextRoom.getText().toString();
		        int position = 0;
		         if(hourString1 == "Stunde 1"){
		         position = 1;
		         }else if(hourString1 == "Stunde 2"){
		         position = 2;   
			     }else if(hourString1 == "Stunde 3"){
			     position = 3; 
				 }else if(hourString1 == "Stunde 4"){
			     position = 4;     
				 }else if(hourString1 == "Stunde 5"){
			     position = 5;    
				 }else if(hourString1 == "Stunde 6"){
				 position = 6;  
				 }else if(hourString1 == "Stunde 7"){
				 position = 7;    
				 }else if(hourString1 == "Stunde 8"){
			     position = 8;       
				 }
				 else{
				  	 Toast.makeText(MainActivityAdd.this, "ungültige Stunde", Toast.LENGTH_LONG).show();
				 }
		         
		         if(hourString2 == "Stunde 1"){
			         
		         }else if(hourString2 == "Stunde 2"){
			         
			     }else if(hourString2 == "Stunde 3"){
				   
				 }else if(hourString2 == "Stunde 4"){
				        
				 }else if(hourString2 == "Stunde 5"){
				      
				 }else if(hourString2 == "Stunde 6"){
				      
				 }else if(hourString2 == "Stunde 7"){
				      
				 }else if(hourString2 == "Stunde 8"){
				      
				 }
				 else{
				  	 Toast.makeText(MainActivityAdd.this, "ungültige Stunde", Toast.LENGTH_LONG).show();
				 }
		        
		        	
		         if(dayString == "Montag") {
		         
		         }else if(dayString == "Dienstag"){
		         
		         }else if(dayString == "Mittwoch"){
			      
			     }else if(dayString == "Donnerstag"){
			        
			     }else if(dayString == "Freitag"){
			      
			     }else{
			    	 Toast.makeText(MainActivityAdd.this, "ungültiger Tag", Toast.LENGTH_LONG).show();
			     }
		         
		         
		         listItems.add(editTextName.getText().toString());
		         listItems_room.add(roomNumber);
		         listItems_from.add(hourString1);
		         listItems_to.add(hourString2);
		         listItems_hour1.add(hourString1);
		         listItems_hour2.add(hourString2);
				   
				   try {
					   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
					   FileOutputStream output = MainActivityAdd.this.openFileOutput("lines.txt",MainActivityAdd.this.MODE_WORLD_READABLE);
					   DataOutputStream dout = new DataOutputStream(output);
					   dout.writeInt(listItems.size()); // Save line count
					   for(String line : listItems) // Save lines
					      dout.writeUTF(line);
					   dout.flush(); // Flush stream ...
					   dout.close(); // ... and close.
					}
					catch (IOException exc) { exc.printStackTrace(); }
				   Intent i = new Intent(MainActivityAdd.this,PlanActivity.class);
				   startActivity(i);
					   listItems.add(Name);//EditText1.getText().toString()
		        }
		        
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void viewonly(View v){
	    Intent intent = new Intent(this, FullSizePickerActivityAdd.class);
	    startActivity(intent);
	}
	
	/**
	 * Opens a DialogFragment with a AM/PM(12 Hours) TimePicker
	 * @param v
	 */
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
