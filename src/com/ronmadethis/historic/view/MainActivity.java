package com.ronmadethis.historic.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ronmadethis.historic.model.DataBaseHandler;
import com.ronmadethis.historic.model.HistoricSite;
import com.ronmadethis.historic.model.Parser;

public class MainActivity extends ListActivity implements
		ActionBar.OnNavigationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		makeActionBar();
		parse();
	}

	private void makeActionBar() {
		// Set up the action bar to show a dropdown list.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		String[] dropdownValues = { "All", "Alberta", "BC", "Manitoba",
				"New Brunswick", "Newfoundland & Labrador", "NWT",
				"Nova Scotia", "Nunavut", "Ontario", "PEI", "Quebec",
				"Saskatchewan", "Yukon" };
		// Specify a SpinnerAdapter to populate the dropdown list.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actionBar.getThemedContext(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				dropdownValues);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(adapter, this);
	}

	private void parse() {
		new ParseTask().execute();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Intent i = new Intent(this, SiteActivity.class);
		// i.putExtra("id", position + 1);
		// startActivity(i);
	}

	@Override
	public boolean onNavigationItemSelected(int i, long l) {
		if (i > 0) {
			// DataBaseHandler db = new DataBaseHandler(this);
			// List<String> values = db.getProvince(i);
			// ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			// getBaseContext(), android.R.layout.simple_list_item_1,
			// values);
			// setListAdapter(adapter);
		}
		return false;
	}

	private class ParseTask extends
			AsyncTask<Void, Void, ArrayList<HistoricSite>> {

		private ArrayList<String> values = new ArrayList<String>();

		@Override
		protected ArrayList<HistoricSite> doInBackground(Void... voids) {
			DataBaseHandler db = new DataBaseHandler(getBaseContext());
			if (!getSharedPreferences("default", 0).getBoolean("dataLoaded",
					false)) {
				AssetManager am = getResources().getAssets();
				try {
					InputStream is = am.open("sites.csv");
					Parser p = new Parser();
					ArrayList<HistoricSite> parsed = p.parseData(is);
					for (int i = 0; i < parsed.size(); i++) {
						db.addSite(parsed.get(i));
						values.add(parsed.get(i).getNameEn());
					}
					SharedPreferences pref = getSharedPreferences("default", 0);
					SharedPreferences.Editor editor = pref.edit();
					editor.putBoolean("dataLoaded", true);
					editor.commit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				values = db.getAllNamesEn();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<HistoricSite> historicSites) {
			super.onPostExecute(historicSites);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getBaseContext(), android.R.layout.simple_list_item_1,
					values);
			setListAdapter(adapter);
		}

	}
}