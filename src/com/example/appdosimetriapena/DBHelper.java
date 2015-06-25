package com.example.appdosimetriapena;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "pessoa.db" ;
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_PESSOA = "pessoa";
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE pessoa (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT NOT NULL," +
				"idade INTEGER NOT NULL," +
				"sexo INTEGER NOT NULL," +
				"pena INTEGER);";
		db.execSQL(sql);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS pessoa");
		onCreate(db);
	}
}
