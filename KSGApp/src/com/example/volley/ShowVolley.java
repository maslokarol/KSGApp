package com.example.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import com.example.db.Data;
import com.example.utilities.AppController;
import com.example.utilities.Const;
import com.example.utilities.JsonUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowVolley extends BaseVolley {
	
	public static List<Data> dataToServer = new ArrayList<Data>();
	
	public ShowVolley(Context context) {
		super(context);
	}
	
	public void show(final String username, final GoogleMap map) throws JSONException{
		JsonArrayRequest jsonObjReq = new JsonArrayRequest(Const.URL_FILTER+"?username="+username, 
		new Response.Listener<JSONArray>() {
 
        @Override
        public void onResponse(JSONArray response) {
            Log.d("show_onResponse", response.toString());   
            
            dataToServer.clear();
            try {
				interpreter(response, map);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        }, new GeneralErrorListenerImpl(ctx)) {
    	
		@Override
		 protected Response<JSONArray> parseNetworkResponse(NetworkResponse nResponse) {
			Log.d("show_statusCode", Integer.toString(nResponse.statusCode));
			String jsonString = "";

			return super.parseNetworkResponse(nResponse);
		}	
    		
		@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            return headers;
        }	
    	};
 
		AppController.getInstance().addToRequestQueue(jsonObjReq, "tag_show");
	}
	
	public void showByDate(final String username, final String dateAfter, final String dateBefore, final GoogleMap map) throws JSONException{
		JsonArrayRequest jsonObjReq = new JsonArrayRequest(Const.URL_FILTER+"?dateAfter="+dateAfter+"&"+
				"dateBefore="+dateBefore+"&"+"username="+username, 
		new Response.Listener<JSONArray>() {
 
        @Override
        public void onResponse(JSONArray response) {
            Log.d("show_onResponse", response.toString());   
            
            dataToServer.clear();
            try {
				interpreter(response, map);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        }, new GeneralErrorListenerImpl(ctx)) {
    	
		@Override
		 protected Response<JSONArray> parseNetworkResponse(NetworkResponse nResponse) {
			Log.d("show_statusCode", Integer.toString(nResponse.statusCode));
			String jsonString = "";

			return super.parseNetworkResponse(nResponse);
		}	
    		
		@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            return headers;
        }	
    	};
 
		AppController.getInstance().addToRequestQueue(jsonObjReq, "tag_show");
	}
	
	public void interpreter(JSONArray array, GoogleMap map) throws JSONException {
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			
			Data tmp = new Data();
			tmp.setLatitude(item.getDouble(Const.LATITUDE));
			tmp.setLongitude(item.getDouble(Const.LONGITUDE));
			tmp.setTitle(item.getString(Const.DESC));
			
			dataToServer.add(tmp);
		}			
        
        for(int i = 0; i < dataToServer.size(); i++) {      	
        	LatLng pos = new LatLng(dataToServer.get(i).getLatitude(), dataToServer.get(i).getLongitude());
        	map.addMarker(new MarkerOptions()
			.position(pos)
			.title(dataToServer.get(i).getTitle()));
        }
	}
}
