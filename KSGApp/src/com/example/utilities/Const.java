package com.example.utilities;

/**
 * Klasa przechowuje wszelkie sta³e. Pozwala to dokonywaæ zmian tylko w tym pliku.
 */

public class Const {
	// server links
	private final static String server_url = "http://agile-everglades-5619.herokuapp.com/";
	public static final String URL_SEND = server_url + "user/create";

	// Const Strings
	public static final String USERNAME = "username";
	public static final String LATITUDE = "lat";
	public static final String LONGITUDE = "lon";
	public static final String DESC = "desc";
	public static final String TS = "ts";

	
	public abstract static class VolleyErrors {
        public static final String CONNECTION_TIMEOUT = "com.android.volley.TimeoutError";
        public static final String NO_CONNECTION = "com.android.volley.NoConnectionError";
        public static final String CONNECT_EXCEPTION = "java.net.ConnectException";
        public static final String UNKNOWN_HOST = "java.net.UnknownHostException";
        public static final String AUTHORISATION_FAILURE = "com.android.volley.AuthFailureError";
        public static final String SERVER_ERROR = "com.android.volley.ServerError";
        public static final String PARSE_ERROR = "com.android.volley.ParseError";
    }
	
	public abstract static class CustomIntentActions {
        public static final String ACTION_AUTH_FAILED_BROADCAST = "com.geommo.intent.action.AUTH_FAILED_BROADCAST";
        public static final String ACTION_UNAUTHORIZED_BROADCAST = "com.geommo.intent.action.UNAUTHORIZED_BROADCAST";
        public static final String ACTION_CONN_TIMEOUT_BROADCAST = "com.geommo.intent.action.CONN_TIMEOUT_BROADCAST";
        public static final String ACTION_NO_INTERNET_CONNECTION_BROADCAST = "com.geommo.intent.action.NO_INTERNET_CONNECTION_BROADCAST";
        public static final String ACTION_SERVER_UNAVAILABLE_BROADCAST = "com.geommo.intent.action.SERVER_UNAVAILABLE_BROADCAST";
        public static final String ACTION_SERVER_ERROR_BROADCAST = "com.geommo.intent.action.SERVER_ERROR_BROADCAST";
    }
	
	public abstract static class CustomAppError {
        public static final String e401 = "user is not authorized";
        public static final String e422 = "default";
        public static final String e500 = "Server error.. try again for a little while";
        public static final String e503 = "Server unavailabie... try again for a little while";
	}
}
