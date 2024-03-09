package com.example.socketpsp.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.socketpsp.model.Poema;

import java.util.ArrayList;
import java.util.List;

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

    @SuppressLint("Range")
    public List<Poema> getAllPoemas() {
        List<Poema> poemas = new ArrayList<>();
        Cursor cursor = database.query("poema", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Poema poema = new Poema();
                poema.setId(cursor.getInt(cursor.getColumnIndex("id")));
                poema.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                poema.setContenido(cursor.getString(cursor.getColumnIndex("contenido")));
                poema.setPuntos(cursor.getInt(cursor.getColumnIndex("puntos")));
                poemas.add(poema);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return poemas;
    }
}
