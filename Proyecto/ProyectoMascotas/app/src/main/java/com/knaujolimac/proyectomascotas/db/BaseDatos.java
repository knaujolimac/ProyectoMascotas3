package com.knaujolimac.proyectomascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.knaujolimac.proyectomascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by juan.chaparro on 22/07/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE_MASCOTA + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_ID_FOTO + " INTEGER" +
                ")";

        db.execSQL(queryCrearTablaContacto);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        onCreate(db);
    }

    /**
     * Método encargado de insertar los likes de una mascota
     * @param contentValues
     */
    public void insertarLikesMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null, contentValues);
        db.close();
    }

    /**
     * Método encargado de obtener las cinco mascotas con más likes
     * @param cantidadMascotas
     * @param filtrar
     * @return
     */
    public ArrayList<Mascota> obtenerMascotasRating(int cantidadMascotas, boolean filtrar){

        ArrayList<Mascota> mascotasLike =  new ArrayList<Mascota>();

        String subQuery = " SELECT " + ConstantesBaseDatos.TABLE_MASCOTA_ID + ","
                    + "  COUNT("+ConstantesBaseDatos.TABLE_MASCOTA_ID+") AS CANT_LIKES "
                    + "  FROM "+ConstantesBaseDatos.TABLE_MASCOTA
                    + " GROUP BY " + ConstantesBaseDatos.TABLE_MASCOTA_ID;

        String query = " SELECT DISTINCT A.*, B.CANT_LIKES " +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTA +" A " +
                " INNER JOIN ("+subQuery+") B ON A."+ConstantesBaseDatos.TABLE_MASCOTA_ID + "= B." + ConstantesBaseDatos.TABLE_MASCOTA_ID +
                " ORDER BY B.CANT_LIKES DESC ";

                if(filtrar){
                    query +=" LIMIT 0,"+cantidadMascotas;
                }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota data = new Mascota();

            int index = 0;
            data.setIdMascota(registros.getInt(index++));
            data.setNombreMascota(registros.getString(index++));
            data.setFoto(registros.getInt(index++));
            data.setCantidadLikes(registros.getInt(index++));

            mascotasLike.add(data);
        }

        return mascotasLike;
    }

}
