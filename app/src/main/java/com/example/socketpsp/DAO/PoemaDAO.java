package com.example.socketpsp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.socketpsp.model.Poema;

public class PoemaDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public PoemaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertPoema(Poema poema) {
        ContentValues values = new ContentValues();
        values.put("id", poema.getId());
        values.put("titulo", poema.getTitulo());
        values.put("contenido", poema.getContenido());
        values.put("puntos", poema.getPuntos());
        return database.insert("poema", null, values);
    }

    public int updatePoema(Poema poema) {
        ContentValues values = new ContentValues();
        values.put("titulo", poema.getTitulo());
        values.put("contenido", poema.getContenido());
        values.put("puntos", poema.getPuntos());
        return database.update("poema", values, "id = ?", new String[]{String.valueOf(poema.getId())});
    }

    public int deletePoema(int poemaId) {
        return database.delete("poema", "id = ?", new String[]{String.valueOf(poemaId)});
    }
}
