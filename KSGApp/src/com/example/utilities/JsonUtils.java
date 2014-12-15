package com.example.utilities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Klasa odpowiedzialna za zarz¹dzanie wiadomoœciami JSON.
 */

public class JsonUtils {
	
	public static JSONObject jLocation(double lat, double lon, String desc, String ts) throws JSONException{		
		JSONObject location = new JSONObject();
		
		location.put(Const.USERNAME, "maslokarol");
		location.put(Const.LATITUDE, lat);
		location.put(Const.LONGITUDE, lon);
		location.put(Const.DESC, desc);
		location.put(Const.TS, ts);
		
		return location;
	}
}
