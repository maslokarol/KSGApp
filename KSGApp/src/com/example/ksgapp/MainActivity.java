package com.example.ksgapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ksgapp.R;

import com.example.dialogs.TitleDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements OnMapReadyCallback {

	
	Button clearPosition, sendToServer;
	GoogleMap map;
	List<Data> dataToServer = new ArrayList<Data>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        clearPosition = (Button)findViewById(R.id.clearPositionBtn);
        sendToServer = (Button)findViewById(R.id.sendToServerBtn);
        
        clearPosition.setEnabled(false);
        sendToServer.setEnabled(false);
       
        
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
				
				TitleDialog td = new TitleDialog(googleMap, arg0, dataToServer);
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
    
    public void sendToServer(View view) {
    	//Przes³aæ do bazy zawartoœæ dataToServer
    	
    }
}
