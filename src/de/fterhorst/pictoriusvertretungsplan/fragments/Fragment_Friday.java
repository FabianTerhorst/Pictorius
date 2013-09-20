package de.fterhorst.pictoriusvertretungsplan.fragments;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import de.fterhorst.pictoriusvertretungsplan.HelpActivity;
import de.fterhorst.pictoriusvertretungsplan.R;
import de.fterhorst.pictoriusvertretungsplan.UserSettingActivity;
import de.fterhorst.pictoriusvertretungsplan.adapter.MyBaseAdapter;
import de.fterhorst.pictoriusvertretungsplan.adapter.MySimpleArrayAdapter;

public class Fragment_Friday extends SherlockFragment{
ArrayAdapter<String> adapter1;
MyBaseAdapter adapter3;
EditText EditText1,room,hourfrom,hourto,timefrom,timeto;
Button Button1,houradd,cancel;
ListView List;
public static String ARG_SECTION_NUMBER;
String[] stringArray,stringArray_room,stringArray2,stringArray3,stringArray4,stringArray5,stringArray1,stringArray_hour,stringArray_time;
public static final String PREFS_NAME = "timetablearraylist";
ArrayList<String> listItems5=new ArrayList<String>(); 
ArrayList<String> listItems_room5=new ArrayList<String>(); 
ArrayList<String> listItems_hour5=new ArrayList<String>();
ArrayList<String> listItems_time5=new ArrayList<String>();
int count5 = 1;
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	 View V = inflater.inflate(R.layout.fragment_monday_stundenplan, container, false);
	 setHasOptionsMenu(true);
	 listItems5.clear();
	 listItems_room5.clear();
	 listItems_hour5.clear();
	 listItems_time5.clear();
	 try {
		   FileInputStream input = getActivity().openFileInput("lines5.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems5.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
	 try {
		   FileInputStream input = getActivity().openFileInput("lines5_room.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems_room5.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
	 try {
		   FileInputStream input = getActivity().openFileInput("lines5_hour.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems_hour5.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
	 try {
		   FileInputStream input = getActivity().openFileInput("lines5_time.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems_time5.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
  
//List = getListView();
List = (ListView)V.findViewById(R.id.listView1);
List.setDivider(null);
//Button1 = (Button)V.findViewById(R.id.Button01);
EditText1 = (EditText)V.findViewById(R.id.from);
room = (EditText)V.findViewById(R.id.room);
hourfrom = (EditText)V.findViewById(R.id.hourfrom);
hourto = (EditText)V.findViewById(R.id.hourto);
timefrom = (EditText)V.findViewById(R.id.timefrom);
timeto = (EditText)V.findViewById(R.id.timeto);
houradd = (Button)V.findViewById(R.id.addHour);
cancel = (Button)V.findViewById(R.id.cancel);
List.setOnItemClickListener(new OnItemClickListener(){
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		listItems5.remove(position);
		listItems_room5.remove(position);
		listItems_hour5.remove(position);
		listItems_time5.remove(position);
		adapter1.notifyDataSetChanged();
		stringArray = listItems5.toArray(new String[listItems5.size()]);
		stringArray_room = listItems_room5.toArray(new String[listItems_room5.size()]);
		stringArray_hour = listItems_hour5.toArray(new String[listItems_hour5.size()]);
		stringArray_time = listItems_time5.toArray(new String[listItems_time5.size()]);
	    adapter1=new MySimpleArrayAdapter(getActivity(),stringArray,stringArray_room,stringArray_hour,stringArray_time);
		 List.setAdapter(adapter1);
	    adapter1.notifyDataSetChanged();
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines5.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems5.size()); // Save line count
			   for(String line : listItems5) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines5_room.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_room5.size()); // Save line count
			   for(String line : listItems_room5) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines5_time.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_time5.size()); // Save line count
			   for(String line : listItems_time5) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines5_hour.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_hour5.size()); // Save line count
			   for(String line : listItems_hour5) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	}
});
stringArray = listItems5.toArray(new String[listItems5.size()]);
stringArray_room = listItems_room5.toArray(new String[listItems_room5.size()]);
stringArray_hour = listItems_hour5.toArray(new String[listItems_hour5.size()]);
stringArray_time = listItems_time5.toArray(new String[listItems_time5.size()]);
adapter1=new MySimpleArrayAdapter(getActivity(),stringArray,stringArray_room,stringArray_hour,stringArray_time);
List.setAdapter(adapter1);
List.setEmptyView(V.findViewById(R.id.emptyView));
/*Button1.setOnClickListener(new View.OnClickListener() {
  public void onClick(View v) {
  	listItems.add(EditText1.getText().toString());
  	adapter1.notifyDataSetChanged();
  }
});*/

   return V;
}

@Override
public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main2, menu);
    super.onCreateOptionsMenu(menu, inflater);
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	   case R.id.action_add:
		   
			//   Intent i = new Intent(getActivity(),MainActivityAdd.class);
			//   startActivity(i);
			   EditText1.setVisibility(View.VISIBLE);
			   room.setVisibility(View.VISIBLE);
			   hourfrom.setVisibility(View.VISIBLE);
			   hourto.setVisibility(View.VISIBLE);
			   timefrom.setVisibility(View.VISIBLE);
			   timeto.setVisibility(View.VISIBLE);
			   houradd.setVisibility(View.VISIBLE);
			   cancel.setVisibility(View.VISIBLE);
			   cancel.setOnClickListener(new OnClickListener(){
			        @Override
			        public void onClick(View view) {
			        	 EditText1.setVisibility(View.GONE);
			  		   room.setVisibility(View.GONE);
			  		   hourfrom.setVisibility(View.GONE);
			  		   hourto.setVisibility(View.GONE);
			  		   timefrom.setVisibility(View.GONE);
			  		   timeto.setVisibility(View.GONE);
			  		   houradd.setVisibility(View.GONE);
			  		   cancel.setVisibility(View.GONE);
			        }
			   });
			   houradd.setOnClickListener(new OnClickListener(){
			        @Override
			        public void onClick(View view) {

			 		   listItems5.add(EditText1.getText().toString());
					   listItems_room5.add("Raum "+room.getText().toString());
					   listItems_hour5.add(hourfrom.getText().toString()+ "." + " - " +hourto.getText().toString()+ "." + " Stunde");
					   listItems_time5.add(" "+timefrom.getText().toString()+ " Uhr" + " - " +timeto.getText().toString()+ " Uhr");
						adapter1.notifyDataSetChanged();
						stringArray = listItems5.toArray(new String[listItems5.size()]);
						stringArray_room = listItems_room5.toArray(new String[listItems_room5.size()]);
						stringArray_hour = listItems_hour5.toArray(new String[listItems_hour5.size()]);
						stringArray_time = listItems_time5.toArray(new String[listItems_time5.size()]);
						 adapter1=new MySimpleArrayAdapter(getActivity(),stringArray,stringArray_room,stringArray_hour,stringArray_time);
						 List.setAdapter(adapter1);
					    adapter1.notifyDataSetChanged();
					   
					   try {
						   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
						   FileOutputStream output = getActivity().openFileOutput("lines5.txt",getActivity().MODE_WORLD_READABLE);
						   DataOutputStream dout = new DataOutputStream(output);
						   dout.writeInt(listItems5.size());
						   for(String line : listItems5)
						   dout.writeUTF(line);
						   dout.flush(); // Flush stream ...
						   dout.close(); // ... and close.
						}
						catch (IOException exc) { exc.printStackTrace(); }
					   try {
						   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
						   FileOutputStream output = getActivity().openFileOutput("lines5_room.txt",getActivity().MODE_WORLD_READABLE);
						   DataOutputStream dout = new DataOutputStream(output);
						   dout.writeInt(listItems_room5.size());// Save line count
						   for(String line : listItems_room5)
						   dout.writeUTF(line);
						   dout.flush(); // Flush stream ...
						   dout.close(); // ... and close.
						}
						catch (IOException exc) { exc.printStackTrace(); }
					   try {
						   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
						   FileOutputStream output = getActivity().openFileOutput("lines5_hour.txt",getActivity().MODE_WORLD_READABLE);
						   DataOutputStream dout = new DataOutputStream(output);
						   dout.writeInt(listItems_hour5.size());// Save line count
						   for(String line : listItems_hour5)
						   dout.writeUTF(line);
						   dout.flush(); // Flush stream ...
						   dout.close(); // ... and close.
						}
						catch (IOException exc) { exc.printStackTrace(); }
					   try {
						   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
						   FileOutputStream output = getActivity().openFileOutput("lines5_time.txt",getActivity().MODE_WORLD_READABLE);
						   DataOutputStream dout = new DataOutputStream(output);
						   dout.writeInt(listItems_time5.size());// Save line count
						   for(String line : listItems_time5)
						   dout.writeUTF(line);
						   dout.flush(); // Flush stream ...
						   dout.close(); // ... and close.
						}
						catch (IOException exc) { exc.printStackTrace(); }
					   EditText1.setText("");
					   EditText1.clearComposingText();
					   room.setText("");
					   room.clearComposingText();
					   hourfrom.setText("");
					   hourfrom.clearComposingText();
					   hourto.setText("");
					   hourto.clearComposingText();
					   timefrom.setText("");
					   timefrom.clearComposingText();
					   timeto.setText("");
					   timeto.clearComposingText();
					   adapter1.notifyDataSetChanged();
			        	
			        	
			        	
			        }
			    });
	        return true;
	   case R.id.action_save:
		   try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines5.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems5.size()); // Save line count
			   for(String line : listItems5) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
		   
		   return true;
	   case R.id.action_settings:
		   Intent i2 = new Intent(getActivity(),UserSettingActivity.class);
		   startActivity(i2);
	        return true;
	   case R.id.action_help:
		   Intent i3 = new Intent(getActivity(),HelpActivity.class);
		   startActivity(i3);
	        return true;
		}
		
		return super.onOptionsItemSelected(item);
}
}
