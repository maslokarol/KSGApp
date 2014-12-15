package com.example.utilities;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import com.example.volley.LruBitmapCache;

/**
 * Klasa dziedzicz¹ca po Application, a wiêc jest to klasa, do której ma siê dostêp z ka¿dego miejsca aplikacji.
 * S³u¿y do obs³ugiwania asynchronicznych ¿¹dañ HTTP wysy³anych przez bibliotekê Volley.
 */

public class AppController extends Application {
	public static final String TAG = AppController.class.getSimpleName();
	public static boolean DASHBOARD_BACKGROUND_OPERATION_ENDS = false;

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private static AppController mInstance;

	@Override
	public void onCreate() {
		Log.i("AppController", "onCreate");
		super.onCreate();
		mInstance = this;
	}
	
	/*
	 *  Region VOLLEY
	 */

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	/*
	 * EndRegion VOLLEY
	 */
}
