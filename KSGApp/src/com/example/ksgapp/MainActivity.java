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
import android.widget.Toast;

import com.example.ksgapp.R;

import com.example.db.Data;
import com.example.dialogs.TitleDialog;
import com.example.volley.DataVolley;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity {
	Button login;
	EditText username;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        login = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
    }
    
    public void login(View view) {
    	if(username.getText().toString() == "") {
    		Toast.makeText(getApplicationContext(), "Nazwa u¿ytkownika jest wymagana.", Toast.LENGTH_LONG).show();
    	} else {
    		Intent login = new Intent(getApplicationContext(), MapActivity.class);
    		login.putExtra("username", username.getText().toString());
    		login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		this.startActivity(login);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
