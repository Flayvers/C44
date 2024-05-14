package com.eric.appsql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

//un singleton peut etre utiliser quand on veut pas seulement avec sql
public class GestionBD extends SQLiteOpenHelper {

    //instance unique de la classe Singleton GestionBD
    private static GestionBD instance;
    private SQLiteDatabase database;


    // méthode de base pour un Singleton
    //permet de n'avoir que un singleton, on va maintenant utiliser la methode getInstance pour avoir acces a l'objet GestionBD
    public static GestionBD getInstance(Context contexte) {
        if (instance == null)
            instance = new GestionBD(contexte);
        return instance;
    }


    private GestionBD(Context context) {
        super(context, "db", null, 1);

    }

//onCreate appeler seulement une seule fois lorsquon installe l'app
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE inventeur(_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, origine TEXT, invention TEXT, annee INTEGER)");
        ajouterInventeur(new Inventeur("Laszlo Biro", "Hongrie", "stylo a bille", 1938), db);
        ajouterInventeur(new Inventeur("Benjamin Franklin", "Etats-Unis", "Paratonnerre", 1752), db);
        ajouterInventeur(new Inventeur("Mary Anderson", "Etats-Unis", "Essuie-glace", 1903), db);
        ajouterInventeur(new Inventeur("Grace Hopper", "Etats-Unis", "Compilateur", 1952), db);
        ajouterInventeur(new Inventeur("Benoit Rouquayrot", "France", "Scaphandre", 1864), db);

    }
    public void ajouterInventeur(Inventeur i, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nom", i.getNom());
        cv.put("origine", i.getOrigine());
        cv.put("invention", i.getInvention());
        cv.put("annee", i.getAnnee());
        db.insert("inventeur", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL();
    }
    public void ouvrirConnexion(){
        database = this.getWritableDatabase();
    }
    public void fermerConnexion(){
        database.close();
    }
    //si je fais select*(dans le rawquery) pour avoir invention dans dans mon getString ce serait a l'index 3 (id(0), nom(1), origine(2), invention(3), annee(4))
    //vu que ici j'ai fais select invention FROM inventeur on est directement sur la colone invention qui est donc la colone 0
    public Vector<String> retournerInvention(){
        Vector<String> v = new Vector<>();
        Cursor c = database.rawQuery("select invention from inventeur", null);
        while(c.moveToNext()) {
            v.add(c.getString(0));
        }
        c.close();
        return v;
    }
    //nom = nom de l'inventeur
    public boolean match(String nom, String invention){
        String[] tab = {nom, invention};
        Cursor c = database.rawQuery("select invention from inventeur where nom= ? and invention = ?", tab);

        return c.moveToFirst(); //retourne vrai ou faux
    }

}
