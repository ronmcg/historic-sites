package com.ronmadethis.historic.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper {

	// All Static variables
	private static final String TAG = "com.ronmadethis.historic.DataBaseHandler";
	// Database Version
	private static final int DB_VERSION = 1;

	// Database Name
	private static final String DB_NAME = "HISTORIC_SITES_DB";

	// Contacts table name
	private static final String TABLE_SITES = "sites";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME_EN = "name_en";
	private static final String KEY_NAME_FR = "name_fr";
	private static final String KEY_PLAQUE = "plaque";
	private static final String KEY_TOWN = "town";
	private static final String KEY_PROV = "province";
	private static final String KEY_REASON_EN = "reason_en";
	private static final String KEY_REASON_FR = "reason_fr";
	private static final String KEY_LAT = "latitude";
	private static final String KEY_LON = "longitude";

	public DataBaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TABLE_SITES + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME_EN
				+ " TEXT," + KEY_NAME_FR + " TEXT," + KEY_PLAQUE + " TEXT,"
				+ KEY_TOWN + " TEXT," + KEY_PROV + " TEXT," + KEY_REASON_EN
				+ " TEXT," + KEY_REASON_FR + " TEXT," + KEY_LAT + " REAL,"
				+ KEY_LON + " REAL" + ");";
		db.execSQL(CREATE_TABLE);
		Log.i(TAG, "Table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITES);
		// Create tables again
		onCreate(db);
		Log.i(TAG, "Table upgraded");
	}

	/**
	 * add a new site
	 */
	public void addSite(HistoricSite hs) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME_EN, hs.getNameEn());
		values.put(KEY_NAME_FR, hs.getNameFr());
		values.put(KEY_PLAQUE, hs.getPlaqueLoc());
		values.put(KEY_TOWN, hs.getTown());
		values.put(KEY_PROV, hs.getProvince());
		values.put(KEY_REASON_EN, hs.getReasonEn());
		values.put(KEY_REASON_FR, hs.getReasonFr());
		values.put(KEY_LAT, hs.getLatitude());
		values.put(KEY_LON, hs.getLongitude());
		// Inserting Row
		db.insert(TABLE_SITES, null, values);
		db.close(); // Closing database connection
		Log.i(TAG, "Site added");
	}

	/*
	 * Returns a List of the sites
	 */
	public ArrayList<HistoricSite> getAll() {
		ArrayList<HistoricSite> siteList = new ArrayList<HistoricSite>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_SITES, null, null, null, null, null,
				null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HistoricSite hs = new HistoricSite();
			hs.setNameEn(cursor.getString(1));
			hs.setNameFr(cursor.getString(2));
			hs.setPlaqueLoc(cursor.getString(3));
			hs.setTown(cursor.getString(4));
			hs.setProvince(cursor.getString(5));
			hs.setReasonEn(cursor.getString(6));
			hs.setReasonFr(cursor.getString(7));
			hs.setLatitude(cursor.getFloat(8));
			hs.setLongitude(cursor.getFloat(9));
			siteList.add(hs);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		db.close();
		return siteList;
	}

	/*
	 * Returns a List of the sites
	 */
	public ArrayList<String> getAllNamesEn() {
		ArrayList<String> siteList = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_SITES, null, null, null, null, null,
				null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			siteList.add(cursor.getString(1));
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		db.close();
		return siteList;
	}
}
