package com.dbs.dbsvc;

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.commons.lang.StringEscapeUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class XMLToJSON {
	
	public static String convertXML2JSON(String XMLString, int indent) {
		XMLSerializer xs = new XMLSerializer();
		xs.setTypeHintsCompatibility(false);
		
		//xs.setRootName("Component");
		xs.setForceTopLevelObject(true);
		System.out.println(XMLString);
		JSON j = xs.read(XMLString);
		
		boolean isJSONArr = j.isArray();
		if (isJSONArr){
			System.out.println("is JSON Array");
		}else {
		parse((JSONObject) j);
		}

		String JSONString = j.toString(indent);

		return JSONString;
	}
	
	public static void parse(JSONObject json) throws JSONException {

		Iterator <? > keys = json.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String val = null;			
			BigDecimal d = new BigDecimal(0);
			boolean numberConverted = false;
			
			try {
				// If current object is a JSONObject, mark the keys to remove, create a new JSON array with same key/value pairs for addition later. 
				if (json.get(key) instanceof JSONObject) {
					JSONObject jObject = json.getJSONObject(key);						
					parse(jObject);

					// If the object is a JSONArray, iterate the array to get each JSONObject
				}else if (json.get(key) instanceof JSONArray) {

					JSONArray jArray = json.getJSONArray(key);

					for (int n = 0; n < jArray.size(); n++) {
						JSONObject jObject = jArray.getJSONObject(n);
						parse(jObject);
					} 
					// if the value is []
					if (jArray.size() == 0) {    						
						json.put(key, JSONNull.getInstance());
					}
					// Otherwise, the object by now should be a string, int, long etc. If the value of the key is empty or null, replace it with expliczzzt JSON NULL.
				} else {
					
					
					val = json.getString(key);
									
					try{
						d= new BigDecimal(val);
						
						numberConverted = true;
					} catch (NumberFormatException ex) {
						//val was not a number
					}
					
					
					if (val.length() == 0) {					
						json.put(key, JSONNull.getInstance());
					}else if (numberConverted) {
						
						json.put(key, d);
					}else if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
						json.put(key, Boolean.valueOf(val));
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public static final String[][] HELP_STRINGS = { 
		{ 
		"convertXML2JSON", 
		"This method converts an XML string into JSON string.",
		"convertXML2JSON(\""+ StringEscapeUtils.escapeXml("<root><persons json_class=\""+"array"+"\"><person><name>Smith</name><age>20</age></person></persons></root>")+"\", 0)",
		"{\"persons\":[{\"name\":\"Smith\",\"age\":20}]}"} 
	};
}
