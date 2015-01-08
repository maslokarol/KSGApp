package com.example.ksgapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksgapp.R;

import com.example.db.Data;
import com.example.dialogs.FilterDialog;
import com.example.dialogs.TitleDialog;
import com.example.volley.DataVolley;
import com.example.volley.RemoveVolley;
import com.example.volley.ShowVolley;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends Activity implements OnMapReadyCallback {
	Button clearPosition, sendToServer;
	Switch autoSendSwitch;
	public static GoogleMap map;
	List<Data> dataToServer = new ArrayList<Data>();
	DataVolley volleySend;
	RemoveVolley volleyRemove;
	ShowVolley volleyShow;
	String username;
	public static String userF, dateBefore, dateAfter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        volleySend = new DataVolley(getApplicationContext());
        volleyRemove = new RemoveVolley(getApplicationContext());
        volleyShow = new ShowVolley(getApplicationContext());
        
        clearPosition = (Button)findViewById(R.id.clearPositionBtn);
        sendToServer = (Button)findViewById(R.id.sendToServerBtn);
        
        Intent tmp = getIntent();
        username = tmp.getStringExtra("username");
        
        this.setTitle("KSGApp. Witaj " + username);
             
        clearPosition.setEnabled(false);
        sendToServer.setEnabled(false);
           
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
	public void onMapReady(final GoogleMap googleMap) {
		// TODO Auto-generated method stub
    	
		LatLng trojmiasto = new LatLng(54.415273,18.523042);
		googleMap.setMyLocationEnabled(true);
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trojmiasto, 10));
		
		googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {

			@Override
			public void onMapLongClick(LatLng arg0) {
				// TODO Auto-generated method stub
				//googleMap.addMarker(new MarkerOptions().position(arg0));
				
				TitleDialog td = new TitleDialog(googleMap, arg0, dataToServer, volleySend, username);
				td.show(getFragmentManager(), "tag");
			}
			
		});
		
		clearPosition.setEnabled(true);
        sendToServer.setEnabled(true);
		map = googleMap;
	}
    
    public void clearPosition(View view) {
    	map.clear();
    	dataToServer.clear();
    }
    
    public void sendToServer(View view) throws JSONException {
    	for(Data d : dataToServer) {
    		volleySend.sendData(username, d.getLatitude(), d.getLongitude(), d.getTitle(), d.getTimestamp());   		
    	}
    	map.clear();
    	dataToServer.clear();
    }
    
    public void sendRemainPos(View view) throws JSONException {
    	volleySend.sendData(username, map.getMyLocation().getLatitude(), map.getMyLocation().getLongitude() , "", "");
    }
    
    public void showAll(View view) throws JSONException {
    	volleyShow.show(username, map);
    	///volleyShow.
    }
    
    public void removeAll(View view) throws JSONException {
    	volleyRemove.remove(username);
    	map.clear();
    	dataToServer.clear();
    	// usunac wszystkie zielone znaczniki
    }
    
    public void filter(View view) throws JSONException {
    	FilterDialog fd = new FilterDialog(userF, dateBefore, dateAfter, map, volleyShow);
		fd.show(getFragmentManager(), "tag");
		

		//volleyShow.show(userF, map);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
