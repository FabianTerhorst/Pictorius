/***
  Copyright (c) 2008-2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Advanced Android Development_
    http://commonsware.com/AdvAndroid
*/

   
package de.fterhorst.pictoriusvertretungsplan.adapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
  @Override
  public RemoteViewsFactory onGetViewFactory(Intent intent) {
	  String[] klasse = null;
	  try {
		klasse = new Stuff().execute().get();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return(new LoremViewsFactory(this.getApplicationContext(),
                                 intent,klasse));
  }
  public class Stuff extends AsyncTask<Void, Void, String[]> {
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
         /* if(returnchange == "1"){
        	  return stringArray;
          }if(returnchange == "2"){
              return stringArray2;
          }if(returnchange == "3"){
              return stringArray3;
          }if(returnchange == "4"){
              return stringArray4;
          }if(returnchange == "5"){
              return stringArray5;
          }if(returnchange == "6"){
              return stringArray6;
          }*/
              return stringArray; 
          }
          
      }
      protected void onPostExecute( String[] result) {
    
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
