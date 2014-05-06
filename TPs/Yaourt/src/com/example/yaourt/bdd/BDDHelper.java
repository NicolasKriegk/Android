package com.example.yaourt.bdd;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDDHelper extends SQLiteOpenHelper {

	//Constructeur rajouté a la mano
	public BDDHelper(Context context) {
		super(context, "yaourt.db", null, 1);
	}

	public BDDHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public BDDHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//table utilisateurs
		db.execSQL("CREATE TABLE users("
			+	"_login      TEXT NOT NULL ,"
			+	"passw       TEXT NOT NULL ,"
			+	"name        TEXT ,"
			+	"super_user  INTEGER NOT NULL ,"
			+	"PRIMARY KEY (_login)"
			+	")"
		);
		//creation d'un super utilisateur, pour premiere connexion
		db.execSQL(
					"INSERT INTO users"
							+	"(_login, passw, name, super_user) "
							+	"VALUES "
							+	"('admin','0000','admin',1)"
				);
		//TODO suppr, valeurs test
		db.execSQL(
				"INSERT INTO users"
						+	"(_login, passw, name, super_user) "
						+	"VALUES "
						+	"('1','0000','test1',0)"
						+	", ('2','0000','test2',0)"
			);
		//table clients
		db.execSQL(
				"CREATE TABLE clients("
						+	"_id   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"
						+	", name  TEXT NOT NULL"
						+	")"
		);
		//table produits
		db.execSQL(
				"CREATE TABLE products("
						+	"_id   INTEGER NOT NULL"
						+	", recipe  TEXT NOT NULL"
						+	", PRIMARY KEY AUTOINCREMENT (_id)"
						+	")"
		);

//		#------------------------------------------------------------
//		#        Script SQLite  
//		#------------------------------------------------------------
//
//		CREATE TABLE order(
//			_id          INTEGER autoincrement NOT NULL ,
//			nb_batch     INTEGER NOT NULL ,
//			order_date   NUMERIC NOT NULL ,
//			_id_product  INTEGER NOT NULL ,
//			_id_client   INTEGER NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_product) REFERENCES product(_id),
//			FOREIGN KEY (_id_client) REFERENCES client(_id)
//		);
//
//		CREATE TABLE batch(
//			_id        INTEGER autoincrement NOT NULL ,
//			step       INTEGER ,
//			_id_order  INTEGER NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_order) REFERENCES order(_id)
//		);
//
//		CREATE TABLE preparation(
//			_id          INTEGER autoincrement NOT NULL ,
//			_id_batch    INTEGER NOT NULL ,
//			_id_tank     INTEGER NOT NULL ,
//			_login_user  TEXT NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_batch) REFERENCES batch(_id),
//			FOREIGN KEY (_id_tank) REFERENCES tank(_id),
//			FOREIGN KEY (_login_user) REFERENCES user(_login)
//		);
//
//		CREATE TABLE prepa_tank(
//			_id_tank       INTEGER NOT NULL ,
//			name_tank      TEXT NOT NULL ,
//			product_tank   INTEGER ,
//			nb_batch_tank  INTEGER ,
//			PRIMARY KEY (_id_tank) ,
//			
//			FOREIGN KEY (_id_tank) REFERENCES tank(_id)
//		);
//
//		CREATE TABLE ster(
//			_id          INTEGER autoincrement NOT NULL ,
//			avg_temp     REAL NOT NULL ,
//			_id_tank     INTEGER NOT NULL ,
//			_id_batch    INTEGER NOT NULL ,
//			_id_tank_1   INTEGER NOT NULL ,
//			_login_user  TEXT NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_tank) REFERENCES tank(_id),
//			FOREIGN KEY (_id_batch) REFERENCES batch(_id),
//			FOREIGN KEY (_id_tank_1) REFERENCES tank(_id),
//			FOREIGN KEY (_login_user) REFERENCES user(_login)
//		);
//
//		CREATE TABLE tank(
//			_id       INTEGER autoincrement NOT NULL ,
//			name      TEXT NOT NULL ,
//			product   INTEGER ,
//			nb_batch  INTEGER ,
//			PRIMARY KEY (_id)
//		);
//
//		CREATE TABLE storage_tank(
//			_id_tank       INTEGER NOT NULL ,
//			name_tank      TEXT NOT NULL ,
//			product_tank   INTEGER ,
//			nb_batch_tank  INTEGER ,
//			PRIMARY KEY (_id_tank) ,
//			
//			FOREIGN KEY (_id_tank) REFERENCES tank(_id)
//		);
//
//		CREATE TABLE storage(
//			_id          INTEGER autoincrement NOT NULL ,
//			_id_batch    INTEGER NOT NULL ,
//			_id_tank     INTEGER NOT NULL ,
//			_login_user  TEXT NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_batch) REFERENCES batch(_id),
//			FOREIGN KEY (_id_tank) REFERENCES tank(_id),
//			FOREIGN KEY (_login_user) REFERENCES user(_login)
//		);
//
//		CREATE TABLE packaging(
//			_id          INTEGER autoincrement NOT NULL ,
//			_id_batch    INTEGER NOT NULL ,
//			_login_user  TEXT NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_batch) REFERENCES batch(_id),
//			FOREIGN KEY (_login_user) REFERENCES user(_login)
//		);
//
//		CREATE TABLE packaging_machine(
//			_id            INTEGER autoincrement NOT NULL ,
//			name           TEXT NOT NULL ,
//			_id_tank       INTEGER NOT NULL ,
//			_id_packaging  INTEGER NOT NULL ,
//			PRIMARY KEY (_id) ,
//			
//			FOREIGN KEY (_id_tank) REFERENCES tank(_id),
//			FOREIGN KEY (_id_packaging) REFERENCES packaging(_id)
//		);
//



	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
