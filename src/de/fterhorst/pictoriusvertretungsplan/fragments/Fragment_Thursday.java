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

public class Fragment_Thursday extends SherlockFragment{
ArrayAdapter<String> adapter1;
MyBaseAdapter adapter3;
EditText EditText1,room,hourfrom,hourto,timefrom,timeto;
Button Button1;
ListView List;
public static String ARG_SECTION_NUMBER;
String[] stringArray,stringArray_room,stringArray2,stringArray3,stringArray4,stringArray5,stringArray1,stringArray_hour,stringArray_time;
public static final String PREFS_NAME = "timetablearraylist";
ArrayList<String> listItems4=new ArrayList<String>(); 
ArrayList<String> listItems_room4=new ArrayList<String>(); 
ArrayList<String> listItems_hour4=new ArrayList<String>();
ArrayList<String> listItems_time4=new ArrayList<String>();
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	 View V = inflater.inflate(R.layout.fragment_monday_stundenplan, container, false);
	 setHasOptionsMenu(true);
	 listItems4.clear();
	 listItems_room4.clear();
	 listItems_hour4.clear();
	 listItems_time4.clear();
	 try {
		   FileInputStream input = getActivity().openFileInput("lines4.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems4.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
	 try {
		   FileInputStream input = getActivity().openFileInput("lines4_room.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems_room4.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
	 try {
		   FileInputStream input = getActivity().openFileInput("lines4_hour.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems_hour4.add(line);
		   }
		   din.close();
		   }catch (IOException exc) { exc.printStackTrace(); }
	 try {
		   FileInputStream input = getActivity().openFileInput("lines4_time.txt"); // Open input stream
		   DataInputStream din = new DataInputStream(input);
		   int sz = din.readInt(); // Read line count
		   for (int i=0;i<sz;i++) { // Read lines
		      String line = din.readUTF();
		      listItems_time4.add(line);
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
List.setOnItemClickListener(new OnItemClickListener(){
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		listItems4.remove(position);
		listItems_room4.remove(position);
		listItems_hour4.remove(position);
		listItems_time4.remove(position);
		adapter1.notifyDataSetChanged();
		stringArray = listItems4.toArray(new String[listItems4.size()]);
		stringArray_room = listItems_room4.toArray(new String[listItems_room4.size()]);
		stringArray_hour = listItems_hour4.toArray(new String[listItems_hour4.size()]);
		stringArray_time = listItems_time4.toArray(new String[listItems_time4.size()]);
	    adapter1=new MySimpleArrayAdapter(getActivity(),stringArray,stringArray_room,stringArray_hour,stringArray_time);
		 List.setAdapter(adapter1);
	    adapter1.notifyDataSetChanged();
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems4.size()); // Save line count
			   for(String line : listItems4) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4_room.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_room4.size()); // Save line count
			   for(String line : listItems_room4) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4_time.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_time4.size()); // Save line count
			   for(String line : listItems_time4) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	    try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4_hour.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_hour4.size()); // Save line count
			   for(String line : listItems_hour4) // Save lines
			      dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
	}
});
stringArray = listItems4.toArray(new String[listItems4.size()]);
stringArray_room = listItems_room4.toArray(new String[listItems_room4.size()]);
stringArray_hour = listItems_hour4.toArray(new String[listItems_hour4.size()]);
stringArray_time = listItems_time4.toArray(new String[listItems_time4.size()]);
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
		   
		   listItems4.add(EditText1.getText().toString());//EditText1.getText().toString()
		  // Toast.makeText(getActivity(), room.getText().toString(), Toast.LENGTH_LONG).show();
		   listItems_room4.add("Raum "+room.getText().toString());
		   listItems_hour4.add(hourfrom.getText().toString()+ "." + " - " +hourto.getText().toString()+ "." + " Stunde");
		   listItems_time4.add(" "+timefrom.getText().toString()+ " Uhr" + " - " +timeto.getText().toString()+ " Uhr");
			adapter1.notifyDataSetChanged();
			stringArray = listItems4.toArray(new String[listItems4.size()]);
			stringArray_room = listItems_room4.toArray(new String[listItems_room4.size()]);
			stringArray_hour = listItems_hour4.toArray(new String[listItems_hour4.size()]);
			stringArray_time = listItems_time4.toArray(new String[listItems_time4.size()]);
			//Log.d("app", stringArray_room[1]);
			// Log.d("app",stringArray_room[1]);
			 adapter1=new MySimpleArrayAdapter(getActivity(),stringArray,stringArray_room,stringArray_hour,stringArray_time);
			 List.setAdapter(adapter1);
		    adapter1.notifyDataSetChanged();
		   
		   try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems4.size());
			   for(String line : listItems4)
			   dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
		   try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4_room.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_room4.size());// Save line count
			   for(String line : listItems_room4)
			   dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
		   try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4_hour.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_hour4.size());// Save line count
			   for(String line : listItems_hour4)
			   dout.writeUTF(line);
			   dout.flush(); // Flush stream ...
			   dout.close(); // ... and close.
			}
			catch (IOException exc) { exc.printStackTrace(); }
		   try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4_time.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems_time4.size());// Save line count
			   for(String line : listItems_time4)
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
	        return true;
	   case R.id.action_save:
		   try {
			   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
			   FileOutputStream output = getActivity().openFileOutput("lines4.txt",getActivity().MODE_WORLD_READABLE);
			   DataOutputStream dout = new DataOutputStream(output);
			   dout.writeInt(listItems4.size()); // Save line count
			   for(String line : listItems4) // Save lines
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