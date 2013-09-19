package de.fterhorst.pictoriusvertretungsplan.adapter;


import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import de.fterhorst.pictoriusvertretungsplan.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleArrayadapterDay extends ArrayAdapter<String>{
  private final Context context;
  private final String[] values;
  private final String[] values2;
  private final String[] values3;
  private final String[] values4;
  private final String[] values5;
  private final String[] values6;
  private final String[] dayx;
  HashMap<String, Integer> alphaIndexer;
 // private ArrayList<String> mStringList;
  LayoutParams params;
 // private ArrayList<String> mStringFilterList;
  String[] sections;  
  //LinkedList<String> items;
	boolean[] hidden = null;
  public SimpleArrayadapterDay(Context context,LinkedList<String> items,String[] dayx, String[] values, String[] values2, String[] values3, String[] values4, String[] values5, String[] values6) {
    super(context, R.layout.list_item, values);

  /*  alphaIndexer = new HashMap<String, Integer>();  
    int size = items.size();  

    for (int x = 0; x < size; x++) {  
         String s = items.get(x);  

         // get the first letter of the store  
         String ch = s.substring(0, 1);  
         // convert to uppercase otherwise lowercase a -z will be sorted  
         // after upper A-Z  
         ch = ch.toUpperCase();  // ch = ch.toUpperCase();
         if (!alphaIndexer.containsKey(ch))
        	 alphaIndexer.put(ch, x);
}  
    Set<String> sectionLetters = alphaIndexer.keySet();  
    // create a list from the set to sort  
    ArrayList<String> sectionList = new ArrayList<String>(  
              sectionLetters);  
    Collections.sort(sectionList);  
    sections = new String[sectionList.size()];  
    sectionList.toArray(sections);  */

    this.context = context;
    this.values = values;
    this.values2 = values2;
    this.values3 = values3;
    this.values4 = values4;
    this.values5 = values5;
    this.values6 = values6;
    this.dayx = dayx;
    getFilter();
  }
  @Override
  public int getCount() {

      return values.length;
  } 

  //Get the data item associated with the specified position in the data set.
  @Override
  public String getItem(int position) {
 
      return values[position];
  }

  //Get the row id associated with the specified position in the list.
  @Override
  public long getItemId(int position) {

      return position;
  }
  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View rowView = inflater.inflate(R.layout.list_item_day, parent, false);
    //hide(position2);
    
    
    final LinearLayout Linearlayout1 = (LinearLayout) rowView.findViewById(R.id.ll1);//layout
    final TextView textView = (TextView) rowView.findViewById(R.id.textView1);//klasse
    final TextView textView2 = (TextView) rowView.findViewById(R.id.textView2);//datum
    final TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);//fach
    final TextView textView4 = (TextView) rowView.findViewById(R.id.textView4);//raum
    final TextView textView5 = (TextView) rowView.findViewById(R.id.textView5);//lehrer
    final TextView textView6 = (TextView) rowView.findViewById(R.id.textView6);//stunde
    final View View1 = (View) rowView.findViewById(R.id.View1);
   	textView.setVisibility(View.GONE);
    textView2.setVisibility(View.GONE);
	textView3.setVisibility(View.GONE);
	textView4.setVisibility(View.GONE);
	textView5.setVisibility(View.GONE);
	textView6.setVisibility(View.GONE);
	View1.setVisibility(View.GONE);
	rowView.setVisibility(View.GONE);
	Linearlayout1.setVisibility(View.GONE);
    /*rowView.setOnClickListener(new View.OnClickListener() {
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
		imageView1.setImageResource(0);
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
			imageView1.setImageResource(R.drawable.spinner);
			open = true;
		}
			
		}
	});
	*/
    
    
    textView.setText(values[position]);
    textView2.setText(values2[position]);
    textView3.setText(values3[position]);
    textView4.setText(values4[position]);
    textView5.setText(values5[position]);
    textView6.setText(values6[position]);
    // Change the icon for Windows and iPhone
    String fach = values3[position];
    String day = values2[position];	
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
    Calendar c = Calendar.getInstance();
    int cday = c.get(Calendar.DAY_OF_MONTH);
    int cmonth = c.get(Calendar.MONTH);
    if (dayx[0] == "today"){
 if (day.startsWith(Integer.toString(cday)+"."+Integer.toString(cmonth+1)+".")) {
	 daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
    }else{
    	dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position); 
    }
    }
    if (dayx[0] == "today+1"){
    	 if (day.startsWith(Integer.toString(getDayPlusX(1))+"."+Integer.toString(cmonth+1)+".")) {
    		 daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
   }else{
	   dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position);
   }
   }
   if (dayx[0] == "today+2"){
	   if (day.startsWith(Integer.toString(getDayPlusX(2))+"."+Integer.toString(cmonth+1)+".")) {
		   daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
  }else{
	  dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position);  
  }
  }
  if (dayx[0] == "today+3"){
	   if (day.startsWith(Integer.toString(getDayPlusX(3))+"."+Integer.toString(cmonth+1)+".")) {
		   daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
  }else{
	  dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position);
  }
  }
  if (dayx[0] == "today+4"){
	   if (day.startsWith(Integer.toString(getDayPlusX(4))+"."+Integer.toString(cmonth+1)+".")) {
		   daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
 }else{
	 dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position);  
 }
 }
  if (dayx[0] == "today+5"){
	   if (day.startsWith(Integer.toString(getDayPlusX(5))+"."+Integer.toString(cmonth+1)+".")) {
		   daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
 }else{
	 dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position);
 }
  }
	   if (dayx[0] == "today+6"){
		   if (day.startsWith(Integer.toString(getDayPlusX(6))+"."+Integer.toString(cmonth+1)+".")) {
			   daynotempty(rowView, textView, textView2, textView3, textView4, textView5, textView6,Linearlayout1,View1);
	 }else{
		 dayempty(rowView,textView,textView2,textView3,textView4,textView5,textView6, View1,position);
	 }	   
 }
    

    return rowView;
  }
  public int getDayPlusX(int number){
 	 Calendar calendar2 = Calendar.getInstance();
      calendar2.add(Calendar.DAY_OF_MONTH,+number);
      int mDayplus2 = calendar2.get(Calendar.DAY_OF_MONTH);
      return mDayplus2;
 }
  public void dayempty(View rowView,TextView textView,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6,View View1,int position){
		textView.setVisibility(View.VISIBLE);
		textView.setText(null);
	    textView2.setVisibility(View.VISIBLE);
	    textView2.setText(null);
		textView3.setVisibility(View.VISIBLE);
		textView3.setText(null);
		textView4.setVisibility(View.VISIBLE);
		textView4.setText(null);
		textView5.setVisibility(View.VISIBLE);
		textView5.setText(null);
		textView6.setVisibility(View.VISIBLE);
		textView6.setText(null);
		View1.setVisibility(View.VISIBLE);
		rowView.setVisibility(View.VISIBLE);
  }
  public void daynotempty(View rowView,TextView textView,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6,LinearLayout Linearlayout1,View View1){
    rowView.setVisibility(View.VISIBLE);
	textView.setVisibility(View.VISIBLE);
	textView2.setVisibility(View.VISIBLE);
	textView3.setVisibility(View.VISIBLE);
	textView4.setVisibility(View.VISIBLE);
	textView5.setVisibility(View.VISIBLE);
	textView6.setVisibility(View.VISIBLE);
	Linearlayout1.setVisibility(View.VISIBLE);
	View1.setVisibility(View.VISIBLE);
	LinearLayout ll1 = (LinearLayout)rowView.findViewById(R.id.ll1);
    params = ll1.getLayoutParams();
    params.height = 220;
    params.width = params.WRAP_CONTENT;
  }
}
