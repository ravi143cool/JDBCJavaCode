package BSFDispatcher.JSONCaller;
import java.util.*;
import java.io.*;
import net.sf.json.xml.XMLSerializer;
import net.sf.json.*;
import java.math.BigDecimal;
//import org.json.*;
public class JSONCallerConvertXMLtoJSON{
/****** START SET/GET METHOD, DO NOT MODIFY *****/
	protected String XMLString = "";
	protected int Indent = 0;
	protected String JSONString = "";
	public String getXMLString() {
		return XMLString;
	}
	public void setXMLString(String val) {
		XMLString = val;
	}
	public int getIndent() {
		return Indent;
	}
	public void setIndent(int val) {
		Indent = val;
	}
	public String getJSONString() {
		return JSONString;
	}
	public void setJSONString(String val) {
		JSONString = val;
	}
/****** END SET/GET METHOD, DO NOT MODIFY *****/

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
			// Otherwise, the object by now should be a string, int, long etc. If the value of the key is empty or null, replace it with explict JSON NULL.
			} else {
				val = json.getString(key);
				try{
                                       	if(!val.contains("E")){
						if(val.startsWith("0")){
								if (val.length()>1){
									
								}
								else{
									d= new BigDecimal(val);
									numberConverted = true;
								}	
							}
							else{
							d= new BigDecimal(val);
							numberConverted = true;
								}
							}	
						}
					catch (NumberFormatException ex) {
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

public void invoke() throws Exception {
/* Available Variables: DO NOT MODIFY
	In  : String XMLString
	In  : int Indent
	Out : String JSONString
* Available Variables: DO NOT MODIFY *****/

	      XMLSerializer xs = new XMLSerializer();
              xs.setTypeHintsCompatibility(false);
              JSON j = xs.read(XMLString);
	      parse((JSONObject) j);

              JSONString = j.toString(Indent);

}
}
