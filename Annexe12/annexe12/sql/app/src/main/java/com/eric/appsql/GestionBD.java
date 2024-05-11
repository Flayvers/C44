package com.eric.appsql;


import android.content.Context;



public class GestionBD extends SQLiteOpenHelper {

    //instance unique de la classe Singleton GestionBD
    private static GestionBD instance;


    // méthode de base pour un Singleton
    public static GestionBD getInstance(Context contexte) {
        if (instance == null)
            instance = new GestionBD(contexte);
        return instance;
    }


    private GestionBD(Context context) {
        super(context, "db", null, 1);

    }


}
