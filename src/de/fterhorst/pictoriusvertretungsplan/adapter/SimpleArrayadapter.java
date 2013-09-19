package de.fterhorst.pictoriusvertretungsplan.adapter;

import de.fterhorst.pictoriusvertretungsplan.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class SimpleArrayadapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;
  private final String[] values2;
  private final String[] values3;
  private final String[] values4;
  private final String[] values5;
  private final String[] values6;
  LayoutParams params;
  public SimpleArrayadapter(Context context, String[] values, String[] values2, String[] values3, String[] values4, String[] values5, String[] values6) {
    super(context, R.layout.list_item, values);
    this.context = context;
    this.values = values;
    this.values2 = values2;
    this.values3 = values3;
    this.values4 = values4;
    this.values5 = values5;
    this.values6 = values6;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.list_item, parent, false);
    final TextView textView = (TextView) rowView.findViewById(R.id.textView1);//klasse
    final TextView textView2 = (TextView) rowView.findViewById(R.id.textView2);//datum
    final TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);//fach
    final TextView textView4 = (TextView) rowView.findViewById(R.id.textView4);//raum
    final TextView textView5 = (TextView) rowView.findViewById(R.id.textView5);//lehrer
    final TextView textView6 = (TextView) rowView.findViewById(R.id.textView6);//stunde
    final View View1 = (View) rowView.findViewById(R.id.View1);//View
    //ll1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    //params.width = 100;
    textView3.setVisibility(View.GONE);
	textView4.setVisibility(View.GONE);
	textView5.setVisibility(View.GONE);
	textView6.setVisibility(View.GONE);
	View1.setVisibility(View.GONE);
   // rowView.setPadding(0, 0, 0, 16);
	
	
    rowView.setOnClickListener(new View.OnClickListener() {
    	boolean open = true;
		@Override
		public void onClick(View v) {
			if (open == true){
		textView.setVisibility(View.VISIBLE);
		textView2.setVisibility(View.VISIBLE);
		textView3.setVisibility(View.VISIBLE);
		textView4.setVisibility(View.VISIBLE);
		textView5.setVisibility(View.VISIBLE);
		textView6.setVisibility(View.VISIBLE);
		View1.setVisibility(View.VISIBLE);
		open = false;
		}
			else{
			textView.setVisibility(View.VISIBLE);
			textView2.setVisibility(View.VISIBLE);
			textView3.setVisibility(View.GONE);
			textView4.setVisibility(View.GONE);
			textView5.setVisibility(View.GONE);
			textView6.setVisibility(View.GONE);
			View1.setVisibility(View.GONE);
			open = true;
		}
			
		}
	});
    
    
    textView.setText(values[position]);
    textView2.setText(values2[position]);
    textView3.setText(values3[position]);
    textView4.setText(values4[position]);
    textView5.setText(values5[position]);
    textView6.setText(values6[position]);
    // Change the icon for Windows and iPhone
    String fach = values3[position];
    if (fach.startsWith("TE 1")) {
    }
    if (fach.startsWith("D")) {
        textView3.setText("Deutsch"); 
    }
    if (fach.startsWith("EUP")) {
    	
    }
    if (fach.startsWith("PRG")) {
    	textView3.setText("Programmieren"); 
    }
    if (fach.startsWith("DKG")) {
        
    }
    if (fach.startsWith("REL")) {
    	textView3.setText("Religion"); 
    }
    if (fach.startsWith("BUN")) {
    	//textView3.setText("Betriebssysteme"); 
    }
    if (fach.startsWith("TE 3")) {
        
    }
    if (fach.startsWith("PGL")) {
        
    }
    if (fach.startsWith("FP")) {
        
    }
    if (fach.startsWith("PH")) {
        
    }
    

    return rowView;
  }
} 