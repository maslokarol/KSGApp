package com.example.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.utilities.AppController;
import com.example.utilities.Const;
import com.example.utilities.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Klasa odpowiedzialna za komunikacjê z serwerem w celu pobrania danych o u¿ytkowniku.
 */

public class RemoveVolley extends BaseVolley {
	
	public RemoveVolley(Context context) {
		super(context);
	}
	
	public void remove(final String username) throws JSONException{
    	JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
		Const.URL_REMOVE, JsonUtils.jRemove(username),
		new Response.Listener<JSONObject>() {
 
        @Override
        public void onResponse(JSONObject response) {
            Log.d("remove_onResponse", response.toString());       
        }
        }, new GeneralErrorListenerImpl(ctx)) {
    	
		@Override
		 protected Response<JSONObject> parseNetworkResponse(NetworkResponse nResponse) {
			Log.d("remove_statusCode", Integer.toString(nResponse.statusCode));
			
			return super.parseNetworkResponse(nResponse);
		}	
    		
		@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            return headers;
        }	
    	};
 
		AppController.getInstance().addToRequestQueue(jsonObjReq, "tag_remove");
	}
}
