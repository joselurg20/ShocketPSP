package com.example.socketpsp.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ardillasBBDD";
    private static final int DATABASE_VERSION = 1;

    // Sentencias SQL para crear las tablas
    private static final String CREATE_TABLE_ARDILLA = "CREATE TABLE ardilla (id INTEGER PRIMARY KEY, dni TEXT, email TEXT, password TEXT, nombre TEXT, puntos INTEGER)";
    private static final String CREATE_TABLE_POEMA = "CREATE TABLE poema (id INTEGER PRIMARY KEY, titulo TEXT, contenido TEXT, puntos INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ejecutar las sentencias SQL para crear las tablas
        db.execSQL(CREATE_TABLE_ARDILLA);
        db.execSQL(CREATE_TABLE_POEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualizar la versión de la base de datos, aquí puedes realizar acciones como eliminar las tablas antiguas y crearlas nuevamente
        db.execSQL("DROP TABLE IF EXISTS ardilla");
        db.execSQL("DROP TABLE IF EXISTS poema");
        onCreate(db);
    }
}
