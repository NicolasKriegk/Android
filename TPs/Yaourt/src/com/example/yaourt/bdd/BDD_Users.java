package com.example.yaourt.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BDD_Users {

	private BDDHelper helper;
	private SQLiteDatabase base;
	
	public void open(Context activity) throws SQLException {
		//creer ou ouvrir la base de donnees
		helper = new BDDHelper(activity);
		base = helper.getWritableDatabase();
	}
	
	public Cursor getUsers() {
		return base.query("users", null, null, null, null, null, null);
	}

	public long createUser(String user) {
		ContentValues values = new ContentValues();
//		values.put("_login",user.getLogin());
//		values.put("passw", user.getPassw());
//		values.put("name", user.getName());
//		values.put("super_user", user.isSuperUser());
		return base.insert("users", null, values);
	}

	public void modifUser(long id, String info) {
		ContentValues values = new ContentValues();
//		values.put("_id", id);
		values.put("info", info);
//		base.update("infos", values, String.valueOf(id), null);
		base.update("infos", values, "_id = " + id, null);
	}

	public void supprUser(long id) {
//		ContentValues values = new ContentValues();
//		values.putNull("_id");
//		values.put("info", info);
//		base.delete("@string/nomTable", whereClause, whereArgs);
//		return base.insert("infos", null, values);
	}
}
