package com.ronmadethis.historic.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ronmadethis.historic.R;

public class MapActivity extends Activity {

	private GoogleMap map;
	private LatLng target;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		findLatLon();
		makeMap();
		pinMarker();
	}

	/***
	 * Get the intent extras that we packed on list item selected and make a
	 * LatLng object
	 */
	private void findLatLon() {
		Intent i = getIntent();
		float lat = i.getFloatExtra("lat", 999);
		float lon = i.getFloatExtra("lon", 999);
		target = new LatLng(lat, lon);
	}

	/**
	 * Sets up our map and the location we want to see
	 */
	private void makeMap() {
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(target).zoom(15).build();
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}

	/**
	 * Pin the marker on the target
	 * 
	 * TODO add info about the location
	 */
	private void pinMarker() {

		map.addMarker(new MarkerOptions().position(target)).setTitle(
				"Hello world");
	}
}