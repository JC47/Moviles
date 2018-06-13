package com.softsofware.javiercalette.proyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class base extends SQLiteOpenHelper {

    private static final String bdName = "agenda.bd";
    private static final int bdVersion=1;
    private static final String tabla1 = "CREATE TABLE T1(ID INTEGER PRIMARY KEY AUTOINCREMENT, EVENTO TEXT, FECHA TEXT, HORA TEXT, STATUS TEXT, CONTACTO TEXT)";

    public base(Context context) {
        super(context, bdName, null,bdVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS T1" + tabla1);
    }

    public void agregarEvento(String evento,String fecha,String hora,String status,String contacto){
        SQLiteDatabase bd = getWritableDatabase();

        if(bd!=null){
            bd.execSQL("INSERT INTO T1 VALUES (null,'"+evento+"','"+fecha+"','"+hora+"','"+status+"','"+contacto+"')");
            bd.close();
        }
    }

    public List<Evento> listar(String d){
        List<Evento> r = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM T1 WHERE FECHA = '" +d+"'", null);
        if(cursor.moveToFirst()){
            do{
                r.add(new Evento(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),R.drawable.event));
            }while (cursor.moveToNext());
        }
        return r;
    }

    public boolean isBussy(String d){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM T1 WHERE FECHA = '" +d+"'", null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }
}
