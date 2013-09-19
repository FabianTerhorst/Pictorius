package de.fterhorst.pictoriusvertretungsplan;

import java.util.ArrayList;
import java.util.Locale;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import de.fterhorst.pictoriusvertretungsplan.adapter.MyBaseAdapter;
import de.fterhorst.pictoriusvertretungsplan.fragments.Fragment_Friday;
import de.fterhorst.pictoriusvertretungsplan.fragments.Fragment_Monday;
import de.fterhorst.pictoriusvertretungsplan.fragments.Fragment_Thursday;
import de.fterhorst.pictoriusvertretungsplan.fragments.Fragment_Tuesday;
import de.fterhorst.pictoriusvertretungsplan.fragments.Fragment_Wednesday;

public class PlanActivity extends SherlockFragmentActivity{
ArrayAdapter<String> adapter1;
MyBaseAdapter adapter3;
EditText EditText1;
Button Button1;
ListView List;
android.support.v4.view.PagerTitleStrip mPagerTitleStrip;
SectionsPagerAdapter mSectionsPagerAdapter;
ViewPager mViewPager;
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
        setContentView(R.layout.activity_plan);
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
        
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mPagerTitleStrip = (android.support.v4.view.PagerTitleStrip)findViewById(R.id.pager_title_strip);

        if(actionbarcolorpref == "0"){
        	mPagerTitleStrip.setBackgroundColor(Color.parseColor("#33B5E5"));
        }else if(actionbarcolorpref == "1"){
        	mPagerTitleStrip.setBackgroundColor(Color.parseColor(actionbarcolorpref));
        }else if(realcolor(actionbarcolorpref)== true){
        	mPagerTitleStrip.setBackgroundColor(Color.parseColor(actionbarcolorpref));
        }
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        
	}
	 
	    public boolean realcolor(String color){
	    	if(color.length() >=3){
	    	return true;
	    	}else{
	    	return false;	
	    	}
	    		
	    	}
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) 
	    {
	    	 switch (item.getItemId()) {
	    	    case android.R.id.home:
	    	        Intent i = new Intent(this,VertretungsPlanActivity.class);
	    	        startActivity(i);
	    	        return true;
	    	    default:
	    	        return super.onOptionsItemSelected(item);
	    	    }
	    }
	    
	  /*  private class DrawerItemClickListener implements ListView.OnItemClickListener {

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	      switch(position) {
	        case 0:   
	        	 Intent a2 = new Intent(getBaseContext(), VertretungsPlanActivity.class);
	             startActivity(a2);
	        break;
	        case 1:
	        	Intent a = new Intent(PlanActivity.this, PlanActivity.class);
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
	    }*/
		public class SectionsPagerAdapter extends FragmentPagerAdapter {

			public SectionsPagerAdapter(FragmentManager fm) {
				super(fm);
			}

			@Override
			public int getCount() {
				return 5;
			}

			@Override
			public CharSequence getPageTitle(int position) {
				Locale l = Locale.getDefault();
				switch (position) {
				case 0:
					return getString(R.string.title_section1).toUpperCase(l);
				case 1:
					return getString(R.string.title_section2).toUpperCase(l);
				case 2:
					return getString(R.string.title_section3).toUpperCase(l);
				case 3:
					return getString(R.string.title_section4).toUpperCase(l);
				case 4:
					return getString(R.string.title_section5).toUpperCase(l);
				}
				return null;
			}
			@Override  
			public Fragment getItem(int position) {  
			    Fragment fragment = new Fragment();  
			    switch (position) {  
			    case 0:  
			        return fragment = new Fragment_Monday();  
			    case 1:  
			        return fragment = new Fragment_Tuesday();
			    case 2:  
			        return fragment = new Fragment_Wednesday();
			    case 3:  
			        return fragment = new Fragment_Thursday();
			    case 4:  
			        return fragment = new Fragment_Friday();
			    default:  
			        break;  
			    }  
			/*	Fragment fragment = new Fragment_Monday();
				Bundle args = new Bundle();
				args.putInt(Fragment_Monday.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);*/
			    return fragment;  
		}
		}
}
