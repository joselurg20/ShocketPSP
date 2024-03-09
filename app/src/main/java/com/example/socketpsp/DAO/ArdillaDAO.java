package com.example.socketpsp.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.socketpsp.model.Ardilla;

import java.util.ArrayList;
import java.util.List;

public class ArdillaDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public ArdillaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertArdilla(Ardilla ardilla) {
        ContentValues values = new ContentValues();
        values.put("dni", ardilla.getDni());
        values.put("email", ardilla.getEmail());
        values.put("password", ardilla.getPassword());
        values.put("nombre", ardilla.getNombre());
        values.put("puntos", ardilla.getPuntos());
        return database.insert("ardilla", null, values);
    }

    @SuppressLint("Range")
    public List<Ardilla> getAllArdillas() {
        List<Ardilla> ardillas = new ArrayList<>();
        Cursor cursor = database.query("ardilla", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Ardilla ardilla = new Ardilla();
                ardilla.setId(cursor.getInt(cursor.getColumnIndex("id")));
                ardilla.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                ardilla.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                ardilla.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                ardilla.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                ardilla.setPuntos(cursor.getInt(cursor.getColumnIndex("puntos")));
                ardillas.add(ardilla);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return ardillas;
    }


    public int updateArdilla(Ardilla ardilla) {
        ContentValues values = new ContentValues();
        values.put("dni", ardilla.getDni());
        values.put("email", ardilla.getEmail());
        values.put("password", ardilla.getPassword());
        values.put("nombre", ardilla.getNombre());
        values.put("puntos", ardilla.getPuntos());
        return database.update("ardilla", values, "id = ?", new String[]{String.valueOf(ardilla.getId())});
    }

    public int deleteArdilla(int ardillaId) {
        return database.delete("ardilla", "id = ?", new String[]{String.valueOf(ardillaId)});
    }

    @SuppressLint("Range")
    public Ardilla getArdillaByEmailAndPassword(String correo, String password) {
        Ardilla ardilla = null;
        String[] columns = {"id", "dni", "email", "password", "nombre", "puntos"};
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {correo, password};

        Cursor cursor = database.query("ardilla", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            ardilla = new Ardilla();
            ardilla.setId(cursor.getInt(cursor.getColumnIndex("id")));
            ardilla.setDni(cursor.getString(cursor.getColumnIndex("dni")));
            ardilla.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            ardilla.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            ardilla.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            ardilla.setPuntos(cursor.getInt(cursor.getColumnIndex("puntos")));
            cursor.close();
        }

        return ardilla;
    }

    public void actualizarPuntosArdilla(String nombreArdilla, int numeroPuntos) {
        ContentValues values = new ContentValues();
        values.put("puntos", numeroPuntos);

        // Definir la condición WHERE para actualizar la ardilla por nombre
        String whereClause = "nombre = ?";
        String[] whereArgs = {nombreArdilla};

        // Actualizar los puntos de la ardilla en la base de datos
        database.update("ardilla", values, whereClause, whereArgs);
    }

}
