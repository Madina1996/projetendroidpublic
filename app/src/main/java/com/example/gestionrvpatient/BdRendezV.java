package com.example.gestionrvpatient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gestionrvpatient.model.Consultation;
import com.example.gestionrvpatient.model.Gerant;
import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Patient;
import com.example.gestionrvpatient.model.RendezV;
import com.example.gestionrvpatient.model.Roles;
import com.example.gestionrvpatient.model.User;

import java.util.ArrayList;
import java.util.List;

public class BdRendezV extends SQLiteOpenHelper {

    public BdRendezV(@Nullable Context context) {
        super(context, "gestionrendezv.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT,login VARCHAR(50),password VARCHAR(100),idrole INTEGER);");
        db.execSQL("CREATE TABLE patient(id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR(100),prenom VARCHAR(100), nom VARCHAR(100), datenaiss VARCHAR(100), telephone VARCHAR(100), cni VARCHAR(100), email VARCHAR(100),iduser INTEGER);");
        db.execSQL("CREATE TABLE medecin(id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR(100),prenom VARCHAR(100), nom VARCHAR(100), datenaiss VARCHAR(100), telephone VARCHAR(100), cni VARCHAR(100),specialite VARCHAR(100));");
        db.execSQL("CREATE TABLE consultation(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(50),description VARCHAR(100),nump  INTEGER,nummed INTEGER);");
        db.execSQL("CREATE TABLE rendezv(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(50),description VARCHAR(100),nump  INTEGER,nummed INTEGER);");
        db.execSQL("CREATE TABLE gerant(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(50),prenom VARCHAR(100),nom VARCHAR(100));");
        db.execSQL("CREATE TABLE roles(id INTEGER PRIMARY KEY AUTOINCREMENT,nom VARCHAR(50));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists user;");
        db.execSQL("DROP TABLE if exists patient;");
        db.execSQL("DROP TABLE if exists medecin;");
        db.execSQL("DROP TABLE if exists consultation;");
        db.execSQL("DROP TABLE if exists rendezv;");
        db.execSQL("DROP TABLE if exists gerant;");
        db.execSQL("DROP TABLE if exists roles;");

        onCreate(db);
    }


//    crud uaser


    public boolean createUser(String login,String password,int role){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login",login);
            cv.put("password",password);
            cv.put("idrole",role);
            db.insert("user",null,cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUser(String login,String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login",login);
            cv.put("password",password);
            db.update("user",cv,"login='"+login+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteUser(String login){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("user","login='"+login+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    public User getUserByid(){
//        try {
//           // List<String> list = new ArrayList<>();
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor c = db.query("user",null,null,null,null,null,null);
//            if(c!=null && c.getCount()>0){
//                c.moveToFirst();
//
//                do{
//                    String login = c.getString(c.getColumnIndex("login"));
//                    list.add(login);
//                    //String password = c.getString(c.getColumnIndex("password"));
//                    //list.add(password);
//                    c.moveToNext();
//
//                }while(!c.isAfterLast());
//
//            }
//            db.close();
//            return list;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }



//    crud patient



    public boolean createPatient(String code, String prenom,String nom,String datenaisse,String telephone,String cni,int iduser,String email){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("prenom",prenom);
            cv.put("nom",nom);
            cv.put("code",code);
            cv.put("datenaiss",datenaisse);
            cv.put("telephone",telephone);
            cv.put("cni",cni);
            cv.put("email",email);
            cv.put("iduser",iduser);
            db.insert("patient",null,cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePatient(int id, String code, String prenom,String nom,String datenaisse,String telephone,String cni,String email){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login",login);
            cv.put("prenom",prenom);
            cv.put("nom",nom);
            cv.put("code",code);
            cv.put("datenaiss",datenaisse);
            cv.put("telephone",telephone);
            cv.put("cni",cni);
            cv.put("email",cni);
            db.update("medecin",cv,"id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeletePatient(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("medecin","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<Patient> readPatient(){
        try {
            List<Patient> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("medecin",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    Patient patient = new Patient();
                    patient.setCode(c.getString(c.getColumnIndex("code")));
                    patient.setEmail(c.getString(c.getColumnIndex("email")));
                    patient.setCni(c.getString(c.getColumnIndex("cni")));
                    patient.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    patient.setId(c.getInt(c.getColumnIndex("id")));
                    patient.setNom(c.getString(c.getColumnIndex("nom")));
                    patient.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    patient.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("id")));
                    patient.setUser(user);
                    list.add(patient);
                    //String password = c.getString(c.getColumnIndex("password"));
                    //list.add(password);
                    c.moveToNext();

                }while(!c.isAfterLast());

            }
            db.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


//    crud medcin
    public boolean createMedecin(Medecin medecin){
    try {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("prenom",medecin.getPrenom());
        cv.put("nom",medecin.getNom());
        cv.put("datenaiss",medecin.getDatenaiss());
        cv.put("telephone",medecin.getTelephone());
        cv.put("cni",medecin.getCni());
        cv.put("specialite",medecin.getSpecialite());
        db.insert("medecin",null,cv);
        db.close();
        return true;
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }
}
    public boolean updateMedecin(Medecin medecin){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("prenom",medecin.getPrenom());
            cv.put("nom",medecin.getNom());
            cv.put("datenaiss",medecin.getDatenaiss());
            cv.put("telephone",medecin.getTelephone());
            cv.put("cni",medecin.getCni());
            cv.put("specialite",medecin.getSpecialite());
            db.update("medecin",cv,"id='"+medecin.getId()+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteMedecin(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("medecin","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<Medecin> readMedecin(){
        try {
            List<Medecin> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("medecin",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    Medecin medecin = new Medecin();
                    medecin.setSpecialite(c.getString(c.getColumnIndex("specialite")));
                    medecin.setCni(c.getString(c.getColumnIndex("cni")));
                    medecin.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    medecin.setId(c.getInt(c.getColumnIndex("id")));
                    medecin.setNom(c.getString(c.getColumnIndex("nom")));
                    medecin.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    medecin.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("id")));
                    list.add(medecin);
                    //String password = c.getString(c.getColumnIndex("password"));
                    //list.add(password);
                    c.moveToNext();

                }while(!c.isAfterLast());

            }
            db.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    crud consultation
    public boolean createConsultation(Consultation consultation){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("date",consultation.getDatec());
            cv.put("description",consultation.getDescription());
            cv.put("nump",consultation.getPatient().getId());
            cv.put("nummed",consultation.getMedecin().getId());
            db.insert("consultation",null,cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateConsultation(Consultation consultation){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login",login);
            cv.put("date",consultation.getDatec());
            db.update("consultation",cv,"id='"+consultation.getId()+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteConsultation(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("consultation","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<Consultation> readConsultations(){
        try {
            List<Consultation> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("consultation",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();

                do{
                    Consultation consultation = new Consultation();
                    consultation.setDatec( c.getString(c.getColumnIndex("date")));
                    consultation.setDescription( c.getString(c.getColumnIndex("date")));
                    Patient patient = new Patient();
                    patient.setId(c.getInt(c.getColumnIndex("nump")));
                    consultation.setPatient(patient);
                    Medecin medecin = new Medecin();
                    medecin.setId(c.getInt(c.getColumnIndex("nummed")));
                    consultation.setMedecin(medecin);
                    list.add(consultation);
                    //String password = c.getString(c.getColumnIndex("password"));
                    //list.add(password);
                    c.moveToNext();

                }while(!c.isAfterLast());

            }
            db.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    crud rendez vous

    public boolean createRendezV(RendezV rendezV){
    try {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date",rendezV.getDaterv());
        cv.put("description",rendezV.getDescription());
        cv.put("nump",rendezV.getPatient().getId());
        cv.put("nummed",rendezV.getMedecin().getId());
        db.insert("rendezv",null,cv);
        db.close();
        return true;
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }
}
    public boolean updateRendezV(RendezV rendezV){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login",login);
            cv.put("date",rendezV.getDaterv());
            db.update("rendezv",cv,"id='"+rendezV.getId()+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteRendezV(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("rendezv","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<RendezV> readRendezV(){
        try {
            List<RendezV> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("rendezv",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();

                do{
                    RendezV rendezV = new RendezV();
                    rendezV.setDaterv( c.getString(c.getColumnIndex("date")));
                    rendezV.setDescription( c.getString(c.getColumnIndex("date")));
                    Patient patient = new Patient();
                    patient.setId(c.getInt(c.getColumnIndex("nump")));
                    rendezV.setPatient(patient);
                    Medecin medecin = new Medecin();
                    medecin.setId(c.getInt(c.getColumnIndex("nummed")));
                    rendezV.setMedecin(medecin);
                    list.add(rendezV);
                    //String password = c.getString(c.getColumnIndex("password"));
                    //list.add(password);
                    c.moveToNext();

                }while(!c.isAfterLast());

            }
            db.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //    crud Gerant

    public boolean createGerant(Gerant gerant){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom",gerant.getNom());
            cv.put("prenom",gerant.getPrenom());
            db.insert("gerant",null,cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateGerant(Gerant gerant){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom",gerant.getNom());
            cv.put("prenom",gerant.getPrenom());
            db.update("gerant",cv,"id='"+gerant.getId()+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteGerant(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("gerant","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //    crud  Role

    public boolean createRoles(Roles roles){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom",roles.getNom());

            db.insert("roles",null,cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateRoles(Roles roles){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom",roles.getNom());
            db.update("roles",cv,"id='"+roles.getId()+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteRoles(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("roles","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<Roles> readRoles(){
        try {
            List<Roles> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("roles",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();

                do{
                    Roles roles = new Roles();
                    roles.setNom( c.getString(c.getColumnIndex("nom")));
                    list.add(roles);
                    c.moveToNext();
                }while(!c.isAfterLast());
            }
            db.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
