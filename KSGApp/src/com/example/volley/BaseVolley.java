package com.example.volley;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.utilities.Const;

/**
 * Klasa odpowiedzialna za zarz¹dzanie b³êdami zwi¹zanymi z po³¹czeniem z serwerem.
 */

public class BaseVolley {
    protected Context ctx;

    public BaseVolley(Context context) {
        this.ctx = context;
    }

    protected boolean isConnectionTimeout(String errorMessage) {
        return errorMessage.contains(Const.VolleyErrors.CONNECTION_TIMEOUT);
    }

    protected boolean isUserUnauthorized(String errorMessage) {
        return errorMessage.contains(Const.VolleyErrors.AUTHORISATION_FAILURE);
    }

    protected boolean isNoInternetConnection(String errorMessage) {
        return (errorMessage.contains(Const.VolleyErrors.NO_CONNECTION)
                && errorMessage.contains(Const.VolleyErrors.UNKNOWN_HOST));
    }

    protected boolean isServerUnavailable(String errorMessage) {
        return (errorMessage.contains(Const.VolleyErrors.NO_CONNECTION)
                && errorMessage.contains(Const.VolleyErrors.CONNECT_EXCEPTION));
    }

    protected boolean isServerError(String errorMessage) {
        return errorMessage.contains(Const.VolleyErrors.SERVER_ERROR);
    }
    
    protected boolean isParseError(String errorMessage) {
        return errorMessage.contains(Const.VolleyErrors.PARSE_ERROR);
    }

    private abstract class GeneralErrorListener implements Response.ErrorListener {
        private Context ctx;

        protected GeneralErrorListener(Context context) {
            this.ctx = context;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            if (isConnectionTimeout(error.toString())) {
            	Log.e("BaseVolley", error.toString());
            	Toast.makeText(ctx, "Connection Timeout... try later", Toast.LENGTH_LONG).show();
            } else if (isUserUnauthorized(error.toString())) {
            	Toast.makeText(ctx, "Invalid email or password", Toast.LENGTH_LONG).show();
            	Log.e("BaseVolley", error.toString());
            } else if (isServerUnavailable(error.toString())) {
            	Toast.makeText(ctx, Const.CustomAppError.e503, Toast.LENGTH_LONG).show();
            	Log.e("BaseVolley", error.toString());
            } else if (isNoInternetConnection(error.toString())) {
            	Toast.makeText(ctx, "No Internet Connection...", Toast.LENGTH_LONG).show();
            	Log.e("BaseVolley", error.toString());
            } else if (isServerError(error.toString())) {
            	Toast.makeText(ctx, Const.CustomAppError.e500, Toast.LENGTH_LONG).show();
            	Log.e("BaseVolley", error.toString());
            } else if (isParseError(error.toString())) {
            	Toast.makeText(ctx, "JSON Parse error" + ctx.getClass(), Toast.LENGTH_LONG).show();
            	Log.e("BaseVolley", error.toString());
            } else {
            	Toast.makeText(ctx, "Something wrong.. try again.", Toast.LENGTH_LONG).show();
            	Log.w("BaseVolley", "else statment and " + error.toString());
            }
        }
    }

    public class GeneralErrorListenerImpl extends GeneralErrorListener {
        public GeneralErrorListenerImpl(Context context) {
            super(context);
        }
    }
}