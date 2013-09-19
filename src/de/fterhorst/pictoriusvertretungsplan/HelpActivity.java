package de.fterhorst.pictoriusvertretungsplan;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.haarman.supertooltips.ToolTip;
import com.haarman.supertooltips.ToolTipRelativeLayout;
import com.haarman.supertooltips.ToolTipView;

import de.fterhorst.pictoriusvertretungsplan.R.color;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;

public class HelpActivity extends SherlockActivity implements View.OnClickListener, ToolTipView.OnToolTipViewClickedListener {

    private ToolTipView mRedToolTipView;
    private ToolTipView mGreenToolTipView;
    private ToolTipView mBlueToolTipView;
    private ToolTipView mPurpleToolTipView;
    private ToolTipView mOrangeToolTipView;
    private ToolTipRelativeLayout mToolTipFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        getSupportActionBar().setTitle("Hilfe");
        mToolTipFrameLayout = (ToolTipRelativeLayout) findViewById(R.id.activity_main_tooltipframelayout);
       // findViewById(R.id.textView1).setOnClickListener(HelpActivity.this);
       // findViewById(R.id.textView2).setOnClickListener(HelpActivity.this);
       // findViewById(R.id.textView3).setOnClickListener(HelpActivity.this);
       // findViewById(R.id.textView4).setOnClickListener(HelpActivity.this);
       // findViewById(R.id.textView5).setOnClickListener(HelpActivity.this);
       // findViewById(R.id.textView6).setOnClickListener(HelpActivity.this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	add1(R.id.ttextView1,color.holo_blue,"Klasse");
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	add1(R.id.ttextView2,color.holo_red,"Datum");
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	add1(R.id.ttextView3,color.holo_purple,"Fach");
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	add1(R.id.ttextView4,color.holo_orange,"Raum");
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	add1(R.id.ttextView5,color.holo_green,"Lehrer");
            }
        }, 2500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	add1(R.id.ttextView6,color.holo_blue,"Stunde");
            }
        }, 3000);

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
   		}
   		
   		return super.onOptionsItemSelected(item);
   	}
    private void add1(int id, int color,String text) {
        mRedToolTipView = mToolTipFrameLayout.showToolTipForView(
                new ToolTip()
                        .withText(text)
                        .withColor(getResources().getColor(color)),
                findViewById(id));
        mRedToolTipView.setOnToolTipViewClickedListener(HelpActivity.this);
    }
    

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView1:
                if (mRedToolTipView == null) {
                	add1(R.id.ttextView1,color.holo_blue,"Klasse");
                } else {
                	mRedToolTipView.remove();
                	mRedToolTipView = null;
                }
                break;
            case R.id.textView2:
                if (mRedToolTipView == null) {
                	add1(R.id.ttextView1,color.holo_red,"Datum");
                } else {
                	mRedToolTipView.remove();
                	mRedToolTipView = null;
                }
                break;
            case R.id.textView3:
                if (mRedToolTipView == null) {
                	add1(R.id.ttextView1,color.holo_purple,"Fach");
                } else {
                	mRedToolTipView.remove();
                	mRedToolTipView = null;
                }
                break;
            case R.id.textView4:
                if (mRedToolTipView == null) {
                	add1(R.id.ttextView1,color.holo_orange,"Raum");
                } else {
                	mRedToolTipView.remove();
                	mRedToolTipView = null;
                }
                break;
            case R.id.textView5:
                if (mRedToolTipView == null) {
                	add1(R.id.ttextView1,color.holo_green,"Lehrer");
                } else {
                	mRedToolTipView.remove();
                	mRedToolTipView = null;
                }
                break;
            case R.id.textView6:
                if (mRedToolTipView == null) {
                	add1(R.id.ttextView1,color.holo_blue,"Stunde");
                } else {
                	mRedToolTipView.remove();
                	mRedToolTipView = null;
                }
                break;
        }
    }

    @Override
    public void onToolTipViewClicked(ToolTipView toolTipView) {
        if (mRedToolTipView == toolTipView) {
            mRedToolTipView = null;
        } else if (mGreenToolTipView == toolTipView) {
            mGreenToolTipView = null;
        } else if (mBlueToolTipView == toolTipView) {
            mBlueToolTipView = null;
        } else if (mPurpleToolTipView == toolTipView) {
            mPurpleToolTipView = null;
        } else if (mOrangeToolTipView == toolTipView) {
            mOrangeToolTipView = null;
        }
    }
}