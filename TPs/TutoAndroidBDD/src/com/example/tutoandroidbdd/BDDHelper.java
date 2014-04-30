package com.example.tutoandroidbdd;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDDHelper extends SQLiteOpenHelper {

	//Constructeur rajouté a la mano
	public BDDHelper(Context context) {
		super(context, "infos.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	public BDDHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public BDDHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE infos(_id INTEGER PRIMARY KEY AUTOINCREMENT, info TEXT NOT NULL)");
		db.execSQL("INSERT INTO infos VALUES (1,'Lundi')"
				+ ", (2,'Mardi')"
				+ ", (3,'Mercredi')"
				+ ", (4,'Jeudi')"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
