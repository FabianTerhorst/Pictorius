package de.fterhorst.pictoriusvertretungsplan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.support.v4.widget.DrawerLayout;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.haarman.listviewanimations.itemmanipulation.AnimateDismissAdapter;
import com.haarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.haarman.listviewanimations.itemmanipulation.SwipeDismissAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import de.fterhorst.pictoriusvertretungsplan.adapter.SimpleArrayadapter;
import de.fterhorst.pictoriusvertretungsplan.adapter.SimpleArrayadapter2;
import de.fterhorst.pictoriusvertretungsplan.adapter.SimpleArrayadapterDay;
import de.fterhorst.pictoriusvertretungsplan.adapter.SyncedScrollListener;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class VertretungsPlanActivity extends SherlockListActivity implements OnNavigationListener, com.actionbarsherlock.app.ActionBar.OnNavigationListener, OnDismissCallback {
	  // EditText inputSearch;
	ProgressDialog prog;
	private android.support.v4.widget.DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navigationDrawerTitles;
	//ListView list2;
	boolean isLeftListEnabled = true;
	boolean isRightListEnabled = true;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        if (width > 1123){
            //code for big screen (like tablet)
         Intent i = new Intent(this,VertretungsPlanActivity_landscape.class);
         startActivity(i);
         }else{
            //code for small screen (like smartphone)
         }
        
        String urls[] ={"123"};
        if(isNetworkAvailable() == true){
        //new Stuff().execute();
        new HtmlTask().execute(urls);
        
        }else{
       // new Stuff().execute();
        Crouton.makeText(this, "Vertretungsplan konnte nicht aktualisiert werden", Style.ALERT).show();
        }
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
       // list2 = (ListView)findViewById(R.id.listView2);
     // Hide the Scollbar
     getListView().setVerticalScrollBarEnabled(false);
     getListView().setHorizontalScrollBarEnabled(false);
     getListView().setFastScrollEnabled(false);
     getListView().setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    // list2.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    /* getListView().setOnScrollListener(new OnScrollListener() {

         @Override
         public void onScrollStateChanged(AbsListView view, int scrollState) {
             // onScroll will be called and there will be an infinite loop.
             // That's why i set a boolean value
             if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                 isRightListEnabled = false;
             } else if (scrollState == SCROLL_STATE_IDLE) {
                 isRightListEnabled = true;
             }
         }

         @Override
         public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                 int totalItemCount) {
             View c = view.getChildAt(0);
             if (c != null && isLeftListEnabled) {
                 list2.setSelectionFromTop(firstVisibleItem, c.getTop());
             }
         }
     });
     list2.setOnScrollListener(new OnScrollListener() {
         @Override
         public void onScrollStateChanged(AbsListView view, int scrollState) {
             if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                 isLeftListEnabled = false;
             } else if (scrollState == SCROLL_STATE_IDLE) {
                 isLeftListEnabled = true;
             }
         }

         @Override
         public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                 int totalItemCount) {
             View c = view.getChildAt(0);
             if (c != null && isRightListEnabled) {
                 getListView().setSelectionFromTop(firstVisibleItem, c.getTop());
             }
         }
     });*/
      //  new Stuff().execute();
        
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
     		final com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
     		actionBar.setDisplayShowTitleEnabled(false);
            getActionBar().setDisplayHomeAsUpEnabled(true);
         	getActionBar().setHomeButtonEnabled(true);
     		
     		boolean todayalwaysuppref = sharedPrefs.getBoolean("prefTodayAlwaysUp", false);
     		if(todayalwaysuppref == false){
            ArrayAdapter<String> adapter =  new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section),
								getString(R.string.title_section_today)+" ("+ Integer.toString(day)+"."+Integer.toString(month+1)+"."+")",
								getString(R.string.title_section_null)+getDayName(0)+" ("+ Integer.toString(getDayPlusX(1))+"."+Integer.toString(month+1)+"."+")",
								getString(R.string.title_section_null)+getDayName(1)+" ("+ Integer.toString(getDayPlusX(2))+"."+Integer.toString(month+1)+"."+")",
								getString(R.string.title_section_null)+getDayName(2)+" ("+ Integer.toString(getDayPlusX(3))+"."+Integer.toString(month+1)+"."+")",
								getString(R.string.title_section_null)+getDayName(3)+" ("+ Integer.toString(getDayPlusX(4))+"."+Integer.toString(month+1)+"."+")",
								getString(R.string.title_section_null)+getDayName(4)+" ("+ Integer.toString(getDayPlusX(5))+"."+Integer.toString(month+1)+"."+")",
								getString(R.string.title_section_null)+getDayName(5)+" ("+ Integer.toString(getDayPlusX(6))+"."+Integer.toString(month+1)+"."+")",});
            
            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            
            ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
     
                @Override
                public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                    
                    switch(itemPosition) {
                    case 0:
                    	new Stuff().execute();
                        break;
                    case 1:	
                 		String[] Day7 = {"today"};
                        new StuffDay().execute(Day7);
                        break;
                    case 2:
                    	String[] Day1 = {"today+1"};
                    	new StuffDay().execute(Day1);
                        break;
                    case 3:
                    	String[] Day2 = {"today+2"};
                    	new StuffDay().execute(Day2);
                        break;
                    case 4:
                    	String[] Day3 = {"today+3"};
                    	new StuffDay().execute(Day3);
                        break;
                    case 5:
                    	String[] Day4 = {"today+4"};
                    	new StuffDay().execute(Day4);
                        break;
                    case 6:
                    	String[] Day5 = {"today+5"};
                    	new StuffDay().execute(Day5);
                        break;
                    case 7:
                    	String[] Day6 = {"today+6"};
                    	new StuffDay().execute(Day6);
                        break;
                    }
                    return false;
                }
            };
     
            getActionBar().setListNavigationCallbacks(adapter, navigationListener);
            
            
     		}else{
                ArrayAdapter<String> adapter =  new ArrayAdapter<String>(actionBar.getThemedContext(),
    						android.R.layout.simple_list_item_1,
    						android.R.id.text1, new String[] {
    								getString(R.string.title_section_today)+" ("+ Integer.toString(day)+"."+Integer.toString(month+1)+"."+")",
    								getString(R.string.title_section),
    								getString(R.string.title_section_null)+getDayName(0)+" ("+ Integer.toString(getDayPlusX(1))+"."+Integer.toString(month+1)+"."+")",
    								getString(R.string.title_section_null)+getDayName(1)+" ("+ Integer.toString(getDayPlusX(2))+"."+Integer.toString(month+1)+"."+")",
    								getString(R.string.title_section_null)+getDayName(2)+" ("+ Integer.toString(getDayPlusX(3))+"."+Integer.toString(month+1)+"."+")",
    								getString(R.string.title_section_null)+getDayName(3)+" ("+ Integer.toString(getDayPlusX(4))+"."+Integer.toString(month+1)+"."+")",
    								getString(R.string.title_section_null)+getDayName(4)+" ("+ Integer.toString(getDayPlusX(5))+"."+Integer.toString(month+1)+"."+")",
    								getString(R.string.title_section_null)+getDayName(5)+" ("+ Integer.toString(getDayPlusX(6))+"."+Integer.toString(month+1)+"."+")",});
                getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
                
                ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
         
                    @Override
                    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                        
                        switch(itemPosition) {
                        case 0:
                        	String[] Day = {"today"};
                           	new StuffDay().execute(Day);
                            break;
                        case 1:
                            new Stuff().execute();
                            break;
                        case 2:
                        	String[] Day1 = {"today+1"};
                        	new StuffDay().execute(Day1);
                            break;
                        case 3:
                        	String[] Day2 = {"today+2"};
                        	new StuffDay().execute(Day2);
                            break;
                        case 4:
                        	String[] Day3 = {"today+3"};
                        	new StuffDay().execute(Day3);
                            break;
                        case 5:
                        	String[] Day4 = {"today+4"};
                        	new StuffDay().execute(Day4);
                            break;
                        case 6:
                        	String[] Day5 = {"today+5"};
                        	new StuffDay().execute(Day5);
                            break;
                        case 7:
                        	String[] Day6 = {"today+6"};
                        	new StuffDay().execute(Day6);
                            break;
                        }
                        return false;
                    }
                };
         
                getActionBar().setListNavigationCallbacks(adapter, navigationListener);
     		}
     		 //Navigation drawer
    		mTitle = mDrawerTitle = getTitle();
    	    navigationDrawerTitles = getResources().getStringArray(R.array.list_items);
    	    mDrawerLayout = (DrawerLayout) findViewById(R.id.LinearLayout1);
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
	public boolean realcolor(String color){
	if(color.length() >=3){
	return true;
	}else{
	return false;	
	}
		
	}
    public int getDayPlusX(int number){
    	 Calendar calendar2 = Calendar.getInstance();
         calendar2.add(Calendar.DAY_OF_MONTH,+number);
         int mDayplus2 = calendar2.get(Calendar.DAY_OF_MONTH);
         return mDayplus2;
    }
    public String getDayName(int number){
    	String String1 = null;
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DAY_OF_MONTH,+number);
        int dayname = c.get(Calendar.DAY_OF_WEEK);
        if(dayname == 1){
        	String1 = "Montag";
        	
        }
        if(dayname == 2){
        	String1 = "Dienstag";
        	
        }
        if(dayname == 3){
        	String1 = "Mittwoch";
        	
        }
        if(dayname == 4){
        	String1 = "Donnerstag";
        	
        }
        if(dayname == 5){
        	String1 = "Freitag";
        	
        }
        if(dayname == 6){
        	String1 = "Sammstag";
        	
        }
        if(dayname == 7){
        	String1 = "Sonntag";
        	
        }
    	return String1;
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
    	//if (mDrawerToggle.onOptionsItemSelected(item)) {
        //    return true;
       // }
		switch (item.getItemId()) {
	   case R.id.action_settings:
		   Intent i2 = new Intent(this,UserSettingActivity.class);
		   startActivity(i2);
	        return true;
	   case R.id.action_help:
		   Intent i = new Intent(this,HelpActivity.class);
		   startActivity(i);
	        return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.main,  menu);
	    return super.onCreateOptionsMenu(menu);
	}
    //menü actions erstellen
    public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
    	if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true; 
	    }
    	switch (item.getItemId()) {
 	   case R.id.action_settings:
 		   Intent i2 = new Intent(this,UserSettingActivity.class);
 		   startActivity(i2);
 	        return true;
 	   case R.id.action_help:
 		   Intent i = new Intent(this,HelpActivity.class);
 		   startActivity(i);
 	        return true;
 		}
 		
 		return super.onOptionsItemSelected(item);
	}
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager 
              = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public class Stuff extends AsyncTask<Void, Void, String[]> {
        ArrayList<String> stringArrayList2 = null;
        String[] stringArray,stringArray2,stringArray3,stringArray4,stringArray5,stringArray6,stringArray7,stringArray8,stringArray9,stringArray10,stringArray11,stringArray12 = null;

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String[] doInBackground(Void... arg0) {
            Document document;
            try {
            	String filename = "Html";
            	FileInputStream inputStream = openFileInput(filename);
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder htmlstring = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    htmlstring.append(line);
                }
                r.close();
                inputStream.close();
                String htmltostring = htmlstring.toString();
                //document = Jsoup.connect("https://dl.dropboxusercontent.com/s/2nwg6o480af4ipc/w/37/w00000.htm")
                //        .userAgent("Mozilla").timeout(10000).get();
            	document = Jsoup.parse(htmltostring);
                Elements elements = document.select("table.subst tbody tr.list.odd td.list");
                Elements elements2 = document.select("table.subst tbody tr.list.even td.list");
                if (elements.size() > 0) {
                	Elements els = document.select("table.subst tbody tr.list.even td.list").not(":contains(Next)").not(":contains(Page)");
                    Elements els2 = document.select("table.subst tbody tr.list.odd td.list").not(":contains(Next)").not(":contains(Page)");
                    Log.d("element 1&2 size", Integer.toString(els.size()+els2.size()));
                    int countstrings1 = elements.size();//els.size();
                    int countstrings2 = elements2.size();//els2.size();
                    Log.d("count1", Integer.toString(countstrings1));
                    Log.d("count2", Integer.toString(countstrings2));
                    
                    
                    ArrayList<String> stringArrayList2 = new ArrayList<String>();//klasse 4
                    stringArray = setItemToArrayList(countstrings1,countstrings2,4,elements,elements2,stringArrayList2,stringArray);
                    ArrayList<String> stringArrayList3 = new ArrayList<String>();//datum 1
                    stringArray2 = setItemToArrayList(countstrings1,countstrings2,1,elements,elements2,stringArrayList3,stringArray2);
                    ArrayList<String> stringArrayList4 = new ArrayList<String>();//fach 0
                    stringArray3 = setItemToArrayList(countstrings1,countstrings2,0,elements,elements2,stringArrayList4,stringArray3);
                    ArrayList<String> stringArrayList5 = new ArrayList<String>();//raum 5
                    stringArray4 = setItemToArrayList(countstrings1,countstrings2,5,elements,elements2,stringArrayList5,stringArray4);
                    ArrayList<String> stringArrayList6 = new ArrayList<String>();//lehrer 2
                    stringArray5 = setItemToArrayList(countstrings1,countstrings2,2,elements,elements2,stringArrayList6,stringArray5);
                    ArrayList<String> stringArrayList7 = new ArrayList<String>();//stunde 3
                    stringArray6 = setItemToArrayList(countstrings1,countstrings2,3,elements,elements2,stringArrayList7,stringArray6);
                           
                }
                }catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            return stringArray;
        }
        protected void onPostExecute( String[] result) {
        //	list2 = (ListView)findViewById(R.id.listView2);
  		  final SimpleArrayadapter adapter = new SimpleArrayadapter(VertretungsPlanActivity.this, result,stringArray2,stringArray3,stringArray4,stringArray5,stringArray6);
  	//	final SimpleArrayadapter2 adapter2 = new SimpleArrayadapter2(VertretungsPlanActivity.this, stringArray12,stringArray7,stringArray8,stringArray9,stringArray10,stringArray11);
  		  // setListAdapter(adapter); 
  		
  		 getListView().setTextFilterEnabled(true);
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter(adapter, VertretungsPlanActivity.this));
		swingBottomInAnimationAdapter.setAbsListView(getListView());
		 setListAdapter(swingBottomInAnimationAdapter);
		// list2.setAdapter(adapter2);
		// getListView().setOnScrollListener(new SyncedScrollListener(list2));
	  	// list2.setOnScrollListener(new SyncedScrollListener(getListView()));
        }
        
        public String[] setItemToArrayList(int countstrings1,int countstrings2,int number,Elements elements,Elements elements2,ArrayList<String> stringArrayList,String[]stringArray) {
            for (int i=number; i<countstrings1; i++, i++, i++, i++, i++, i++, i++, i++, i++) {
        	    String array1 = arraylistcontent(i,elements);
        	    stringArrayList.add(array1);
          }
          for (int i=number; i<countstrings2; i++, i++, i++, i++, i++, i++, i++, i++, i++) {
      	    String array1 = arraylistcontent(i,elements2);
      	  stringArrayList.add(array1);
       
           stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
  }
          
          
         
return stringArray;
        }
        
        
        
        public String arraylistcontent(int number,Elements elements){
        	String textstring = "text" + Integer.toString(number);
        	textstring = elements.get(number).text();
        	return textstring;
        }
    }
    
    public class StuffData extends AsyncTask<Void, Void, String[]> {
        ArrayList<String> stringArrayList2 = null;
        String[] stringArray,stringArray2,stringArray3,stringArray4,stringArray5,stringArray6 = null;

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String[] doInBackground(Void... arg0) {
            Document document;
            try {
            	String filename = "Html";
            	FileInputStream inputStream = openFileInput(filename);
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder htmlstring = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    htmlstring.append(line);
                }
                r.close();
                inputStream.close();
                String htmltostring = htmlstring.toString();
                //document = Jsoup.connect("https://dl.dropboxusercontent.com/s/2nwg6o480af4ipc/w/37/w00000.htm")
                //        .userAgent("Mozilla").timeout(10000).get();
            	document = Jsoup.parse(htmltostring);
                Elements elements = document.select("table.subst tbody tr.list.odd td.list");
                Elements elements2 = document.select("table.subst tbody tr.list.even td.list");
                if (elements.size() > 0) {
                	Elements els = document.select("table.subst tbody tr.list.even td.list").not(":contains(Next)").not(":contains(Page)");
                    Elements els2 = document.select("table.subst tbody tr.list.odd td.list").not(":contains(Next)").not(":contains(Page)");
                    Log.d("element 1&2 size", Integer.toString(els.size()+els2.size()));
                    int countstrings1 = elements.size();//els.size();
                    int countstrings2 = elements2.size();//els2.size();
                    Log.d("count1", Integer.toString(countstrings1));
                    Log.d("count2", Integer.toString(countstrings2));
                    
                    
                    ArrayList<String> stringArrayList2 = new ArrayList<String>();//klasse 4
                    stringArray = setItemToArrayList(countstrings1,countstrings2,4,elements,elements2,stringArrayList2,stringArray);
                    ArrayList<String> stringArrayList3 = new ArrayList<String>();//datum 1
                    stringArray2 = setItemToArrayList(countstrings1,countstrings2,1,elements,elements2,stringArrayList3,stringArray2);
                    ArrayList<String> stringArrayList4 = new ArrayList<String>();//fach 0
                    stringArray3 = setItemToArrayList(countstrings1,countstrings2,0,elements,elements2,stringArrayList4,stringArray3);
                    ArrayList<String> stringArrayList5 = new ArrayList<String>();//raum 5
                    stringArray4 = setItemToArrayList(countstrings1,countstrings2,5,elements,elements2,stringArrayList5,stringArray4);
                    ArrayList<String> stringArrayList6 = new ArrayList<String>();//lehrer 2
                    stringArray5 = setItemToArrayList(countstrings1,countstrings2,2,elements,elements2,stringArrayList6,stringArray5);
                    ArrayList<String> stringArrayList7 = new ArrayList<String>();//stunde 3
                    stringArray6 = setItemToArrayList(countstrings1,countstrings2,3,elements,elements2,stringArrayList7,stringArray6);
                           
                }
                }catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            return stringArray;
        }
        protected void onPostExecute( String[] result) {
  		  final SimpleArrayadapter adapter = new SimpleArrayadapter(VertretungsPlanActivity.this, result,stringArray2,stringArray3,stringArray4,stringArray5,stringArray6);
  		  // setListAdapter(adapter); 
  		 getListView().setTextFilterEnabled(true);
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter(adapter, VertretungsPlanActivity.this));
		swingBottomInAnimationAdapter.setAbsListView(getListView());
		 setListAdapter(swingBottomInAnimationAdapter);
        }
        
        public String[] setItemToArrayList(int countstrings1,int countstrings2,int number,Elements elements,Elements elements2,ArrayList<String> stringArrayList,String[]stringArray) {
            for (int i=number; i<countstrings1; i++, i++, i++, i++, i++, i++, i++, i++, i++) {
        	    String array1 = arraylistcontent(i,elements);
        	    stringArrayList.add(array1);
          }
          for (int i=number; i<countstrings2; i++, i++, i++, i++, i++, i++, i++, i++, i++) {
      	    String array1 = arraylistcontent(i,elements2);
      	  stringArrayList.add(array1);
       
           stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
  }
         
return stringArray;
        }
        public String arraylistcontent(int number,Elements elements){
        	String textstring = "text" + Integer.toString(number);
        	textstring = elements.get(number).text();
        	return textstring;
        }
    }
    
    public class HtmlTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
        	prog = new ProgressDialog(VertretungsPlanActivity.this);
            prog.setMessage("Lade....");
            prog.show();
        }
    	
        public String doInBackground(String... urls){
           String html = null;              
            try {
                Log.i("Log1","Parse");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                Calendar c = Calendar.getInstance();
                int woj = c.get(Calendar.WEEK_OF_YEAR);
                HttpPost httpPost = new HttpPost("https://dl.dropboxusercontent.com/s/2nwg6o480af4ipc/w/"+ Integer.toString(woj) +"/w00000.htm");

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                html = EntityUtils.toString(httpEntity);
                String filename = "Html";
                String outputString = html;
                FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(outputString.getBytes());
                outputStream.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
            	 Crouton.makeText(VertretungsPlanActivity.this, "Vertretungsplan konnte nicht aktualisiert werden", Style.ALERT).show();
                e.printStackTrace();
            }
            return html;
        }

        public void onPostExecute(String xml){
        	prog.dismiss();
        }
        
    
    
}
    
    public class StuffDay extends AsyncTask<String[], Void, String[]> {
        ArrayList<String> stringArrayList2 = null;
        String[] stringArray,stringArray2,stringArray3,stringArray4,stringArray5,stringArray6,daystring = null;

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String[] doInBackground(String[]... day) {
            Document document;
            daystring = day[0];
            try {
            	String filename = "Html";
            	FileInputStream inputStream = openFileInput(filename);
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder htmlstring = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    htmlstring.append(line);
                }
                r.close();
                inputStream.close();
                String htmltostring = htmlstring.toString();
            	document = Jsoup.parse(htmltostring);
                
                Elements elements = document.select("table.subst tbody tr.list.odd td.list");
                Elements elements2 = document.select("table.subst tbody tr.list.even td.list");
                if (elements.size() > 0) {
                	Elements els = document.select("table.subst tbody tr.list.even td.list").not(":contains(Next)").not(":contains(Page)");
                    Elements els2 = document.select("table.subst tbody tr.list.odd td.list").not(":contains(Next)").not(":contains(Page)");
                    Log.d("element 1&2 size", Integer.toString(els.size()+els2.size()));
                    int countstrings1 = elements.size();//els.size();
                    int countstrings2 = elements2.size();//els2.size();
                    
                    
                    ArrayList<String> stringArrayList2 = new ArrayList<String>();//klasse
                    stringArray = setItemToArrayList(countstrings1,countstrings2,4,elements,elements2,stringArrayList2,stringArray);
                    ArrayList<String> stringArrayList3 = new ArrayList<String>();//datum
                    stringArray2 = setItemToArrayList(countstrings1,countstrings2,1,elements,elements2,stringArrayList3,stringArray2);
                    ArrayList<String> stringArrayList4 = new ArrayList<String>();//fach
                    stringArray3 = setItemToArrayList(countstrings1,countstrings2,0,elements,elements2,stringArrayList4,stringArray3);
                    ArrayList<String> stringArrayList5 = new ArrayList<String>();//raum
                    stringArray4 = setItemToArrayList(countstrings1,countstrings2,5,elements,elements2,stringArrayList5,stringArray4);
                    ArrayList<String> stringArrayList6 = new ArrayList<String>();//lehrer
                    stringArray5 = setItemToArrayList(countstrings1,countstrings2,2,elements,elements2,stringArrayList6,stringArray5);
                    ArrayList<String> stringArrayList7 = new ArrayList<String>();//stunde
                    stringArray6 = setItemToArrayList(countstrings1,countstrings2,3,elements,elements2,stringArrayList7,stringArray6);
                    /*int i[] = {1,2,3,4,5};
                    for (int j = i.length-1; j >= 0; j--) {
                    	stringArrayList2.remove(i[j]);
                    }*/
                    
                }
                }catch (IOException e) {
            	 Crouton.makeText(VertretungsPlanActivity.this, "Vertretungsplan wir aktualisiert...", Style.INFO).show();
               // text = "Error:" + e;
                e.printStackTrace();
            }
            return daystring;
        }
        protected void onPostExecute( String[] result) {
        	ListView lv = getListView();  
            lv.setFastScrollEnabled(true); 
        	LinkedList<String> mLinked = new LinkedList<String>();  
            for (int i = 0; i < stringArray.length; i++) {  
                 mLinked.add(stringArray[i]);  
            }  
  		  final SimpleArrayadapterDay adapter = new SimpleArrayadapterDay(VertretungsPlanActivity.this,mLinked,daystring, stringArray,stringArray2,stringArray3,stringArray4,stringArray5,stringArray6);
  		 getListView().setTextFilterEnabled(true);
  		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter(adapter, VertretungsPlanActivity.this));
		swingBottomInAnimationAdapter.setAbsListView(getListView());
		 setListAdapter(swingBottomInAnimationAdapter);
  		/* inputSearch = (EditText) findViewById(R.id.inputSearch);
  		 
         // Capture Text in EditText
   		inputSearch.addTextChangedListener(new TextWatcher() {
  
             @Override
             public void afterTextChanged(Editable arg0) {
                 // TODO Auto-generated method stub
                 String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                 adapter.getFilter().filter(text);
             }
  
             @Override
             public void beforeTextChanged(CharSequence arg0, int arg1,
                     int arg2, int arg3) {
                 // TODO Auto-generated method stub
             }
  
             @Override
             public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                     int arg3) {
                 // TODO Auto-generated method stub
             }
         });*/
        	

        }
        public String[] setItemToArrayList(int countstrings1,int countstrings2,int number,Elements elements,Elements elements2,ArrayList<String> stringArrayList,String[]stringArray) {
            for (int i=number; i<countstrings1; i++, i++, i++, i++, i++, i++, i++, i++, i++) {
        	    String array1 = arraylistcontent(i,elements);
        	    stringArrayList.add(array1);
          }
          for (int i=number; i<countstrings2; i++, i++, i++, i++, i++, i++, i++, i++, i++) {
      	    String array1 = arraylistcontent(i,elements2);
      	  stringArrayList.add(array1);
      	  
           stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
  }
         
return stringArray;
        }
        public String arraylistcontent(int number,Elements elements){
        	String textstring = "text" + Integer.toString(number);
        	textstring = elements.get(number).text();
        	return textstring;
        }
    }

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		Log.d("appdismiss", "ok");
		
	}
	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	//set Navigation drawer OnItemClickListener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

      switch(position) {
        case 0:   
        	 Intent a2 = new Intent(getBaseContext(), VertretungsPlanActivity.class);
             startActivity(a2);
        break;
        case 1:
        	Intent a = new Intent(VertretungsPlanActivity.this, PlanActivity.class);
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
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
      }
    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            Log.d("app123", "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            Log.d("app123", "portrait");
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        if (width > 1123){
            //code for big screen (like tablet)
         Intent i = new Intent(this,VertretungsPlanActivity_landscape.class);
         startActivity(i);
         }else{
            //code for small screen (like smartphone)
         	Intent i = new Intent(this,VertretungsPlanActivity.class);
             startActivity(i);
         }
        mDrawerToggle.onConfigurationChanged(newConfig);
    }*/
}