package de.fterhorst.pictoriusvertretungsplan.adapter;

import de.fterhorst.pictoriusvertretungsplan.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;
  private final String[] values2;
  private final String[] values3;
  private final String[] values4;
  
  String[] imageUrls;
  public MySimpleArrayAdapter(Context context, String[] values, String[] values2, String[] values3, String[] values4) {
    super(context, R.layout.timetable_list_item, values);
    this.context = context;
    this.values = values;
    this.values2 = values2;
    this.values3 = values3;
    this.values4 = values4;
    this.notifyDataSetChanged();
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) { 
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.timetable_list_item, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.fach);
    TextView room = (TextView) rowView.findViewById(R.id.raum);
    TextView hour = (TextView) rowView.findViewById(R.id.time);
    TextView time = (TextView) rowView.findViewById(R.id.hour);
    ImageButton menu = (ImageButton) rowView.findViewById(R.id.overflow);
 //   ImageView imageView1 = (ImageView)rowView.findViewById(R.id.stripe);
    textView.setText(values[position]);
    room.setText(values2[position]);
    hour.setText(values3[position]);
    time.setText(values4[position]);
    this.notifyDataSetChanged();
    String imagecolor = values[position];
    if (imagecolor.startsWith("123")) {
    //	imageView1.setBackgroundColor(Color.parseColor("#2ecc71"));
    	this.notifyDataSetChanged();
    }

    return rowView;
  }
} 