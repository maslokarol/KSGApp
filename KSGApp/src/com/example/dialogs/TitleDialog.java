package com.example.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.example.db.Data;
import com.example.ksgapp.R;
import com.example.volley.DataVolley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TitleDialog extends DialogFragment{
	
	private LatLng position;
	private GoogleMap map;
	private List<Data> dataToServer;
	private DataVolley volley;
	private String username;
	
	public TitleDialog(GoogleMap map, LatLng position,List<Data> dataToServer,
			DataVolley volley, String username) {
		this.position = position;
		this.map = map;
		this.dataToServer = dataToServer;
		this.volley = volley;
		this.username = username;
	}
	
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.title_dialog, null);
		final EditText title = (EditText)view.findViewById(R.id.titleET);
		final EditText snippet = (EditText)view.findViewById(R.id.snippetET);
		Button save = (Button)view.findViewById(R.id.saveBtn);
		Button quickSend = (Button)view.findViewById(R.id.quickSendBtn);
		
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Data newData = new Data();
				newData.setLatitude(position.latitude);
				newData.setLongitude(position.longitude);
				newData.setTitle(title.getText().toString());
				Time now = new Time();
				now.setToNow();
				newData.setTimestamp(now.format3339(false));
				
				//zapamietanie do wyslania
				dataToServer.add(newData);
					
				map.addMarker(new MarkerOptions()
					.position(position)
					.title(title.getText().toString())
					.snippet(snippet.getText().toString()));
				
				dismiss();
			}
			
		});
		
		quickSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Data newData = new Data();
				newData.setLatitude(position.latitude);
				newData.setLongitude(position.longitude);
				newData.setTitle(title.getText().toString());
				Time now = new Time();
				now.setToNow();
				newData.setTimestamp(now.format3339(false));
				try {
					volley.sendData(username, newData.getLatitude(), newData.getLongitude(), 
								newData.getTitle(), newData.getTimestamp());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast tst = Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG);
					tst.show();
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

	public List<Data> getDataToServer() {
		return dataToServer;
	}

	public void setDataToServer(List<Data> dataToServer) {
		this.dataToServer = dataToServer;
	}
}
