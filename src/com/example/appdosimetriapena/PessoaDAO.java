package com.example.appdosimetriapena;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PessoaDAO {
	private DBHelper dbHelper;
	private SQLiteDatabase mDatabase;

	public PessoaDAO(Context context) {
		dbHelper = new DBHelper(context);
		try {
			open();
		} catch (Exception e) {
			Log.e("PessoaDAO", "Exception while connecting the DB.");
			e.printStackTrace();
		}
	}

	public void open() throws Exception {
		mDatabase = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void save(Pessoa c) {
		ContentValues values = new ContentValues();

		values.put("nome", c.getNome());
		values.put("idade", c.getIdade());
		values.put("sexo", c.getSexo());
		values.put("pena", c.getPena());
		

		long generatedId = mDatabase.insert(DBHelper.TABLE_PESSOA, null,
				values);
		c.setId(generatedId);
	}

	public List<Pessoa> getAll() {
		List<Pessoa> ret = new ArrayList<Pessoa>();

		Cursor cursor = mDatabase.rawQuery("SELECT * FROM "
				+ DBHelper.TABLE_PESSOA, null);
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Pessoa c = cursorToPessoa(cursor);
			ret.add(c);
			cursor.moveToNext();
		}
		cursor.close();

		return ret;
	}

	private Pessoa cursorToPessoa(Cursor cursor) {
		Pessoa c = new Pessoa();

		c.setId(cursor.getLong(0));
		c.setNome(cursor.getString(1));
		c.setIdade(cursor.getInt(2));
		c.setSexo(cursor.getInt(3));
		c.setPena(cursor.getInt(4));		

		return c;
	}

	private Pessoa cursorToPessoa(Cursor cursor, Pessoa c) {
		Pessoa i = new Pessoa();

		i.setId(cursor.getLong(0));
		i.setNome(cursor.getString(1));
		i.setIdade(cursor.getInt(2));
		i.setSexo(cursor.getInt(3));
		i.setPena(cursor.getInt(4));

		return i;
	}

	public void delete(Pessoa c) {
		mDatabase.delete(DBHelper.TABLE_PESSOA, "_id = " + c.getId(), null);
	}

	public Pessoa getById(long editId) {
		Pessoa c = null;
		Cursor cursor = mDatabase.rawQuery("SELECT * FROM "
				+ DBHelper.TABLE_PESSOA + " WHERE _id = " + editId, null);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			c = cursorToPessoa(cursor, c);
		}
		cursor.close();

		return c;
	}

	public List<Pessoa> getBy(Pessoa c) {
		ArrayList<Pessoa> ret = new ArrayList<Pessoa>();

		Cursor cursor = mDatabase.rawQuery("SELECT * FROM "
				+ DBHelper.TABLE_PESSOA
				+ " WHERE _id = ? ",
				new String[] { String.valueOf(c.getId()) });
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Pessoa i = cursorToPessoa(cursor, c);
			ret.add(i);
			cursor.moveToNext();
		}
		cursor.close();

		return ret;
	}

	public void update(Pessoa c) {
		ContentValues values = new ContentValues();

		values.put("nome", c.getNome());
		values.put("idade", c.getIdade());
		values.put("sexo", c.getSexo());
		values.put("pena", c.getPena());

		mDatabase.update(DBHelper.TABLE_PESSOA, values,
				"_id = " + c.getId(), null);
	}
}
