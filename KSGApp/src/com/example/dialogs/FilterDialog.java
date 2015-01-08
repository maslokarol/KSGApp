package com.example.dialogs;

import java.util.List;

import org.json.JSONException;

import com.example.db.Data;
import com.example.ksgapp.MapActivity;
import com.example.ksgapp.R;
import com.example.volley.ShowVolley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class FilterDialog extends DialogFragment{
	
	private LatLng position;
	private GoogleMap map;
	private ShowVolley volleyFilter;

	
	public FilterDialog(String user, String dateBefore, String dateAfter, GoogleMap map, ShowVolley volley) {

		this.map = map;
		this.volleyFilter = volley;
	}
	
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.filter_dialog, null);
		final EditText username = (EditText)view.findViewById(R.id.filterUsernameET);
		final EditText dateBefore = (EditText)view.findViewById(R.id.dataBeforeET);
		final EditText dateAfter = (EditText)view.findViewById(R.id.dateAfterET);
		Button filter = (Button)view.findViewById(R.id.filterBtn);
		Button filterByDate = (Button)view.findViewById(R.id.filterByDateBtn);
		
		filter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				map.clear();
				if(username.getText().toString() != "") {
					try {
						volleyFilter.show(username.getText().toString(), map);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
				dismiss();
			}		
		});
		
		filterByDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				map.clear();
				try {
					volleyFilter.showByDate(username.getText().toString(), dateBefore.getText().toString(), dateAfter.getText().toString(), map);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				dismiss();
			}		
		});
	
		return view;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		return dialog;
	}

	public LatLng getPosition() {
		return position;
	}

	public void setPosition(LatLng position) {
		this.position = position;
	}

	public GoogleMap getMap() {
		return map;
	}

	public void setMap(GoogleMap map) {
		this.map = map;
	}
}
