package de.fterhorst.pictoriusvertretungsplan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import de.fterhorst.pictoriusvertretungsplan.adapter.MyBaseAdapter;
import de.fterhorst.pictoriusvertretungsplan.adapter.MySimpleArrayAdapter;

public class StundenPlanActivity extends SherlockListActivity{
ArrayAdapter<String> adapter1;
MyBaseAdapter adapter3;
EditText EditText1;
Button Button1;
ListView List;
String[] stringArray,stringArray2,stringArray3,stringArray4,stringArray5,stringArray1;
private static final String MYLISTKEY = "timetable";
public static final String PREFS_NAME = "timetablearraylist";
ArrayList<String> listItems=new ArrayList<String>();
ArrayList<String> listItems1=new ArrayList<String>();
ArrayList<String> listItems2=new ArrayList<String>();
ArrayList<String> listItems3=new ArrayList<String>();
ArrayList<String> listItems4=new ArrayList<String>();
ArrayList<String> listItems5=new ArrayList<String>();
private android.support.v4.widget.DrawerLayout mDrawerLayout;
private ListView mDrawerList;
private ActionBarDrawerToggle mDrawerToggle;
private CharSequence mDrawerTitle;
private CharSequence mTitle;
private String[] navigationDrawerTitles;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stundenplan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
        String actionbarcolorpref = sharedPrefs.getString("prefActionBar", "0");
        if(actionbarcolorpref == "0"){
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
        }else if(actionbarcolorpref == "1"){
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(actionbarcolorpref)));
        }else if(realcolor(actionbarcolorpref)== true){
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(actionbarcolorpref)));
        }
        

        
        
        
        
        
        
        //Set<String> set = new HashSet<String>();
        //Set<String> set = myScores.getStringSet("key", null);
        //ArrayList<String> list=new ArrayList<String>(set);
        try {
			   FileInputStream input = openFileInput("lines.txt"); // Open input stream
			   DataInputStream din = new DataInputStream(input);
			   int sz = din.readInt(); // Read line count
			   for (int i=0;i<sz;i++) { // Read lines
			      String line = din.readUTF();
			      listItems.add(line);
			   }
			   din.close();
			   }catch (IOException exc) { exc.printStackTrace(); }
        
	List = getListView();
	List.setDivider(null);
	Button1 = (Button)findViewById(R.id.Button01);
    EditText1 = (EditText)findViewById(R.id.from);
    List.setOnItemClickListener(new OnItemClickListener(){
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			listItems.remove(position);
			adapter1.notifyDataSetChanged();
			stringArray = listItems.toArray(new String[listItems.size()]);
			 adapter1=new MySimpleArrayAdapter(StundenPlanActivity.this,stringArray,stringArray,stringArray,stringArray);
		        setListAdapter(adapter1);
		    adapter1.notifyDataSetChanged();
		}
    });
    stringArray = listItems.toArray(new String[listItems.size()]);
	/*adapter1=new ArrayAdapter<String>(this,
            R.layout.arrayadapter_item,listItems);//android.R.layout.simple_list_item_1*/
    adapter1=new MySimpleArrayAdapter(this,stringArray,stringArray,stringArray,stringArray);
        setListAdapter(adapter1);
	List.setEmptyView(findViewById(R.id.emptyView));
	Button1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	listItems.add(EditText1.getText().toString());
        	adapter1.notifyDataSetChanged();
        	 stringArray = listItems.toArray(new String[listItems.size()]);
        	 adapter1=new MySimpleArrayAdapter(StundenPlanActivity.this,stringArray,stringArray,stringArray,stringArray);
             setListAdapter(adapter1);
        }
    });
	
    final com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
    
    ArrayAdapter<String> adapter =  new ArrayAdapter<String>(actionBar.getThemedContext(),
			android.R.layout.simple_list_item_1,
			android.R.id.text1, new String[] {
					getString(R.string.title_section_null)+"Montag",
					getString(R.string.title_section_null)+"Dienstag",
					getString(R.string.title_section_null)+"Mittwoch",
					getString(R.string.title_section_null)+"Donnerstag",
					getString(R.string.title_section_null)+"Freitag",});

getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {

   @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        
        switch(itemPosition) {
        case 0:
        	 try {
  			   FileInputStream input = openFileInput("lines1.txt"); // Open input stream
  			   DataInputStream din = new DataInputStream(input);
  			   int sz = din.readInt(); // Read line count
  			   for (int i=0;i<sz;i++) { // Read lines
  			      String line = din.readUTF();
  			      listItems1.add(line);
  			   }
  			   din.close();
  			   }catch (IOException exc) { exc.printStackTrace(); }
      //  stringArray1 = listItems1.toArray(new String[listItems1.size()]);
      //  adapter1=new MySimpleArrayAdapter(getBaseContext(),stringArray1,stringArray1,stringArray1,stringArray1);
      //  	setListAdapter(adapter1);
        	//List.setEmptyView(findViewById(R.id.emptyView));
            break;
        case 1:	
        	try {
   			   FileInputStream input = openFileInput("lines2.txt"); // Open input stream
   			   DataInputStream din = new DataInputStream(input);
   			   int sz = din.readInt(); // Read line count
   			   for (int i=0;i<sz;i++) { // Read lines
   			      String line = din.readUTF();
   			      listItems2.add(line);
   			   }
   			   din.close();
   			   }catch (IOException exc) { exc.printStackTrace(); }
       	stringArray2 = listItems2.toArray(new String[listItems2.size()]);
 	    adapter1=new MySimpleArrayAdapter(getBaseContext(),stringArray2,stringArray2,stringArray2,stringArray2);
 	        setListAdapter(adapter1);
 		List.setEmptyView(findViewById(R.id.emptyView));
            break;
        case 2:
        	try {
   			   FileInputStream input = openFileInput("lines3.txt"); // Open input stream
   			   DataInputStream din = new DataInputStream(input);
   			   int sz = din.readInt(); // Read line count
   			   for (int i=0;i<sz;i++) { // Read lines
   			      String line = din.readUTF();
   			      listItems3.add(line);
   			   }
   			   din.close();
   			   }catch (IOException exc) { exc.printStackTrace(); }
       	stringArray3 = listItems3.toArray(new String[listItems3.size()]);
 	    adapter1=new MySimpleArrayAdapter(getBaseContext(),stringArray3,stringArray3,stringArray3,stringArray3);
 	        setListAdapter(adapter1);
 		    List.setEmptyView(findViewById(R.id.emptyView));
            break;
        case 3:
        	try {
   			   FileInputStream input = openFileInput("lines4.txt"); // Open input stream
   			   DataInputStream din = new DataInputStream(input);
   			   int sz = din.readInt(); // Read line count
   			   for (int i=0;i<sz;i++) { // Read lines
   			      String line = din.readUTF();
   			      listItems4.add(line);
   			   }
   			   din.close();
   			   }catch (IOException exc) { exc.printStackTrace(); }
       	stringArray4 = listItems4.toArray(new String[listItems4.size()]);
 	    adapter1=new MySimpleArrayAdapter(getBaseContext(),stringArray4,stringArray4,stringArray4,stringArray4);
 	        setListAdapter(adapter1);
 		    List.setEmptyView(findViewById(R.id.emptyView));
            break;
        case 4:
        	try {
   			   FileInputStream input = openFileInput("lines5.txt"); // Open input stream
   			   DataInputStream din = new DataInputStream(input);
   			   int sz = din.readInt(); // Read line count
   			   for (int i=0;i<sz;i++) { // Read lines
   			      String line = din.readUTF();
   			      listItems5.add(line);
   			   }
   			   din.close();
   			   }catch (IOException exc) { exc.printStackTrace(); }
       	stringArray5 = listItems5.toArray(new String[listItems5.size()]);
 	    adapter1=new MySimpleArrayAdapter(getBaseContext(),stringArray5,stringArray5,stringArray5,stringArray5);
 	        setListAdapter(adapter1);
 		    List.setEmptyView(findViewById(R.id.emptyView));
            break;
        }
        return false;
    }
};

getActionBar().setListNavigationCallbacks(adapter, navigationListener);
	
	
	 //Navigation drawer
	mTitle = mDrawerTitle = getTitle();
    navigationDrawerTitles = getResources().getStringArray(R.array.list_items);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout1);
    mDrawerList = (ListView) findViewById(R.id.left_drawer);
    ColorDrawable sage= new ColorDrawable(0xdddddddd);

    mDrawerList.setDivider(sage);
    mDrawerList.setDividerHeight(2);
    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    mDrawerList.setAdapter(new ArrayAdapter<String>(this,
            R.layout.drawer_list_item, navigationDrawerTitles));
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    if (savedInstanceState == null) {
        selectItem(0);}
    
    if(actionbarcolorpref == "1"){//|actionbarcolorpref == "1"){
    mDrawerToggle = new ActionBarDrawerToggle(
            this,                 
            mDrawerLayout,         
            R.drawable.ic_drawer,  
            R.string.app_name,  
            R.string.app_name  
            ){
        public void onDrawerClosed(View view) {
            getActionBar().setTitle(mTitle);
            invalidateOptionsMenu(); 
        }

        public void onDrawerOpened(View drawerView) {
            getActionBar().setTitle(mDrawerTitle);
            invalidateOptionsMenu(); 
        }
    };
    }else{
    mDrawerToggle = new ActionBarDrawerToggle(
            this,                 
            mDrawerLayout,         
            R.drawable.ic_list_view,  
            R.string.app_name,  
            R.string.app_name  
            ){
        public void onDrawerClosed(View view) {
            getActionBar().setTitle(mTitle);
            invalidateOptionsMenu(); 
        }

        public void onDrawerOpened(View drawerView) {
            getActionBar().setTitle(mDrawerTitle);
            invalidateOptionsMenu(); 
        }
    };
    }
    mDrawerLayout.setDrawerListener(mDrawerToggle);
	
	}
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
	    super.onSaveInstanceState(outState);
	    outState.putStringArrayList(MYLISTKEY, listItems);
	}
	
	 @Override
		public boolean onOptionsItemSelected(MenuItem item)
		{
		 
		// final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
			//final EditText EditText1 = (EditText)findViewById(R.id.editText1);
			switch (item.getItemId()) {
		   case R.id.action_add:
			   listItems.add(EditText1.getText().toString());
			   adapter1.notifyDataSetChanged();
				stringArray = listItems.toArray(new String[listItems.size()]);
				 adapter1=new MySimpleArrayAdapter(StundenPlanActivity.this,stringArray,stringArray,stringArray,stringArray);
			        setListAdapter(adapter1);
			    adapter1.notifyDataSetChanged();
			   
			   try {
				   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
				   FileOutputStream output = openFileOutput("lines.txt",MODE_WORLD_READABLE);
				   DataOutputStream dout = new DataOutputStream(output);
				   dout.writeInt(listItems.size()); // Save line count
				   for(String line : listItems) // Save lines
				      dout.writeUTF(line);
				   dout.flush(); // Flush stream ...
				   dout.close(); // ... and close.
				}
				catch (IOException exc) { exc.printStackTrace(); }
			   EditText1.setText("");
			   EditText1.clearComposingText();
			   adapter1.notifyDataSetChanged();
			   
			   //Intent i = new Intent(StundenPlanActivity.this,StundenPlanActivity.class);
			   //startActivity(i);
			   
			   //adapter.add(EditText1.getText().toString());
		        return true;
		   case R.id.action_save:
			 //Set the values
			//   Set<String> set = new HashSet<String>();
			//   set.addAll(listItems);
			 /*  SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			      SharedPreferences.Editor editor = settings.edit();
			      editor.putStringSet("timetablearray", set);
			      editor.commit(); */
			   try {
				   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
				   FileOutputStream output = openFileOutput("lines.txt",MODE_WORLD_READABLE);
				   DataOutputStream dout = new DataOutputStream(output);
				   dout.writeInt(listItems.size()); // Save line count
				   for(String line : listItems) // Save lines
				      dout.writeUTF(line);
				   dout.flush(); // Flush stream ...
				   dout.close(); // ... and close.
				}
				catch (IOException exc) { exc.printStackTrace(); }
			   
			   return true;
		   case R.id.action_settings:
	 		   Intent i2 = new Intent(this,UserSettingActivity.class);
	 		   startActivity(i2);
	 	        return true;
	 	   case R.id.action_help:
	 		   Intent i3 = new Intent(this,HelpActivity.class);
	 		   startActivity(i3);
	 	        return true;
			}
			
			return super.onOptionsItemSelected(item);
		}
	  public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
	    	if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true; 
		    }
	    	switch (item.getItemId()) {
			   case R.id.action_add:
				   listItems.add(EditText1.getText().toString());
				   adapter1.notifyDataSetChanged();
					stringArray = listItems.toArray(new String[listItems.size()]);
					 adapter1=new MySimpleArrayAdapter(StundenPlanActivity.this,stringArray,stringArray,stringArray,stringArray);
				        setListAdapter(adapter1);
				    adapter1.notifyDataSetChanged();
				   
				   try {
					   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
					   FileOutputStream output = openFileOutput("lines.txt",MODE_WORLD_READABLE);
					   DataOutputStream dout = new DataOutputStream(output);
					   dout.writeInt(listItems.size()); // Save line count
					   for(String line : listItems) // Save lines
					      dout.writeUTF(line);
					   dout.flush(); // Flush stream ...
					   dout.close(); // ... and close.
					}
					catch (IOException exc) { exc.printStackTrace(); }
				   EditText1.setText("");
				   EditText1.clearComposingText();
				   adapter1.notifyDataSetChanged();
				   
				   //Intent i = new Intent(StundenPlanActivity.this,StundenPlanActivity.class);
				   //startActivity(i);
				   
				   //adapter.add(EditText1.getText().toString());
			        return true;
			   case R.id.action_save:
				 //Set the values
				//   Set<String> set = new HashSet<String>();
				//   set.addAll(listItems);
				 /*  SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				      SharedPreferences.Editor editor = settings.edit();
				      editor.putStringSet("timetablearray", set);
				      editor.commit(); */
				   try {
					   //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
					   FileOutputStream output = openFileOutput("lines.txt",MODE_WORLD_READABLE);
					   DataOutputStream dout = new DataOutputStream(output);
					   dout.writeInt(listItems.size()); // Save line count
					   for(String line : listItems) // Save lines
					      dout.writeUTF(line);
					   dout.flush(); // Flush stream ...
					   dout.close(); // ... and close.
					}
					catch (IOException exc) { exc.printStackTrace(); }
				   
				   return true;
			   case R.id.action_settings:
		 		   Intent i2 = new Intent(this,UserSettingActivity.class);
		 		   startActivity(i2);
		 	        return true;
		 	   case R.id.action_help:
		 		   Intent i3 = new Intent(this,HelpActivity.class);
		 		   startActivity(i3);
		 	        return true;
	    	}
	 		
	 		return super.onOptionsItemSelected(item);
		}
	    @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getSupportMenuInflater();
		    inflater.inflate(R.menu.main2,  menu);
		    return super.onCreateOptionsMenu(menu);
		}
	    @Override
	    public boolean dispatchKeyEvent(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
	    	
	    	listItems.add(EditText1.getText().toString());
			   adapter1.notifyDataSetChanged();
			   
	            return true;
	        }
	        return super.dispatchKeyEvent(e);
	    };
	    public boolean realcolor(String color){
	    	if(color.length() >=3){
	    	return true;
	    	}else{
	    	return false;	
	    	}
	    		
	    	}
	    
	    private class DrawerItemClickListener implements ListView.OnItemClickListener {

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	      switch(position) {
	        case 0:   
	        	 Intent a2 = new Intent(getBaseContext(), VertretungsPlanActivity.class);
	             startActivity(a2);
	        break;
	        case 1:
	        	Intent a = new Intent(StundenPlanActivity.this, PlanActivity.class);
	            startActivity(a);
	               break;
	        default:
	        }
	        }
	        }


	    
	    //do somethink when item is selceted not working 
	    public void selectItem(int position) {
	            switch(position) {
	        case 0:
	        break;
	        case 1:
	        break;
	        default:
	            }
	        }

	//set Navigationdrawer title
	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
	         	getActionBar().setTitle(title);
	            }
	    }

	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);

	        mDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);

	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
}
