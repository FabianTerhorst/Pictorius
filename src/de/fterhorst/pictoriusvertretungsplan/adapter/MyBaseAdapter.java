package de.fterhorst.pictoriusvertretungsplan.adapter;

import de.fterhorst.pictoriusvertretungsplan.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter /*implements OnClickListener*/ {
	
	/*private class OnItemClickListener implements OnClickListener{           
	    private int mPosition;
	    OnItemClickListener(int position){
	            mPosition = position;
	    }
	    public void onClick(View arg0) {
	            Log.v("ddd", "onItemClick at position" + mPosition);                      
	    }               
	}*/

	public static final String LOG_TAG = "BI::CA";
    private Context context;
    private String[] deviceList;

    public MyBaseAdapter(Context context,String[] deviceList ) { 
        this.context = context;
        this.deviceList = deviceList;
    }

    public int getCount() {                        
        return deviceList.length;
    }

    public Object getItem(int position) {     
        return deviceList[position];
    }

    public long getItemId(int position) {  
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) 
    { 
    	 LayoutInflater inflater = (LayoutInflater) context
    		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		    View rowView = inflater.inflate(R.layout.timetable_item, parent, false);
    		    TextView textView = (TextView) rowView.findViewById(R.id.fach);
    		    ImageButton menu = (ImageButton) rowView.findViewById(R.id.overflow);
    		    ImageView imageView1 = (ImageView)rowView.findViewById(R.id.stripe);
    		    textView.setText(deviceList[position]);
    		    this.notifyDataSetChanged();
    		    menu.setOnClickListener(new View.OnClickListener() {
    		        public void onClick(View v) {
    		        }
    		    });
    		    String imagecolor = deviceList[position];
    		    if (imagecolor.startsWith("123")) {
    		    	imageView1.setBackgroundColor(Color.parseColor("#2ecc71"));
    		    	this.notifyDataSetChanged();
    		    }

    		    return rowView;
    }

    /*public void onClick(View v) {
            Log.v(LOG_TAG, "Row button clicked");
    }*/

}