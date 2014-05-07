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
	
	public Cursor getUsers(String[] user) {
//TODO filtrer la recherche. pour le moment, toute la liste est remontee 
//		return base.query("users", null, arguments de recherche a completer, null, null, null, null);
		return base.query("users", null, null, null, null, null, null);
	}

	public long createUser(String[] user) {
		ContentValues values = new ContentValues();
		//TODO verifier les infos avant d'ecrire
		values.put("_login",user[0]);
		values.put("name",user[1]);
		values.put("passw",user[2]);
		values.put("super_user",Integer.valueOf(user[3]));
		return base.insert("users", null, values);
	}

	public void modifUser(String id, String[] user) {
		ContentValues values = new ContentValues();
		//TODO verifier les infos avant d'ecrire
		values.put("_login",user[0]);
		values.put("name",user[1]);
		values.put("passw",user[2]);
		values.put("super_user",Integer.valueOf(user[3]));
		base.update("users", values, "_login = " + id, null);
	}

	public void supprUser(long id) {

	}
}
