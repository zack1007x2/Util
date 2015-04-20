package com.example.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
	
	
	
	//TODO Array
	/**
	 * find difference item of A2
	 * @param A1
	 * @param A2
	 * @return difference item of A2
	 */
	public static String[] findifference(String[]A1,String[] A2) {
		ArrayList<String> a1=new ArrayList(Arrays.asList(A1));
		ArrayList<String> a2=new ArrayList(Arrays.asList(A2));
		
		for( String s : a1){
            a2.remove(s);
		}
		
	    String[] diffArr=new String[a2.size()];
	    diffArr = a2.toArray(diffArr);
	    return diffArr; 
	}

	//TODO JSON
	/**
	 * trim JSON Object
	 * @param json
	 * @param key
	 * @return
	 */
	public static String trimMessage(String json, String key){
	    try{
	        JSONObject obj = new JSONObject(json);
	        return obj.getString(key);
	    } catch(JSONException e){
	        e.printStackTrace();
	        return "";
	    }
	}
}
