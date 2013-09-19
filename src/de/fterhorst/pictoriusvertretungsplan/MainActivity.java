package de.fterhorst.pictoriusvertretungsplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        if (width > 1023 || height > 1023){
           //code for big screen (like tablet)
        Intent i = new Intent(this,VertretungsPlanActivity_landscape.class);
        startActivity(i);
        }else{
           //code for small screen (like smartphone)
        	Intent i = new Intent(this,VertretungsPlanActivity.class);
            startActivity(i);
        }
        if (width > 1123){
            //code for big screen (like tablet)
         Intent i = new Intent(this,VertretungsPlanActivity_landscape.class);
         startActivity(i);
         }else{
            //code for small screen (like smartphone)
         	Intent i = new Intent(this,VertretungsPlanActivity.class);
             startActivity(i);
         }

}
}
