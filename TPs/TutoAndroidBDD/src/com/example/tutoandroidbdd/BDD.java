package com.example.tutoandroidbdd;

import com.example.tutoandroidbdd.R.string;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BDD {

	private BDDHelper helper;
	private SQLiteDatabase base;
	
	public void open(Context activity) throws SQLException {
		//creer ou ouvrir la base de donnees
		helper = new BDDHelper(activity);
		base = helper.getWritableDatabase();
	}
	
	public Cursor getInfos() {
		return base.rawQuery("SELECT _id, info FROM infos", null);
	}

	public long createInfo(String info) {
		ContentValues values = new ContentValues();
		values.putNull("_id");
		values.put("info", info);
		return base.insert("infos", null, values);
	}

	public void modifInfo(long id, String info) {
		ContentValues values = new ContentValues();
//		values.put("_id", id);
		values.put("info", info);
//		base.update("infos", values, String.valueOf(id), null);
		base.update("infos", values, "_id = " + id, null);
	}

	public void supprInfo(long id) {
//		ContentValues values = new ContentValues();
//		values.putNull("_id");
//		values.put("info", info);
//		base.delete("@string/nomTable", whereClause, whereArgs);
//		return base.insert("infos", null, values);
	}
}
