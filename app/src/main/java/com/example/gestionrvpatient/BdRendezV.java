package com.example.gestionrvpatient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        super(context, "gestionrendezv.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT,login VARCHAR(50),password VARCHAR(100),idrole INTEGER);");
        db.execSQL("CREATE TABLE patient(id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR(100),prenom VARCHAR(100), nom VARCHAR(100), datenaiss VARCHAR(100), telephone VARCHAR(100), cni VARCHAR(100), email VARCHAR(100),iduser INTEGER);");
        db.execSQL("CREATE TABLE medecin(id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR(100),prenom VARCHAR(100), nom VARCHAR(100), datenaiss VARCHAR(100), telephone VARCHAR(100), cni VARCHAR(100),specialite VARCHAR(100),iduser INTEGER);");
        db.execSQL("CREATE TABLE consultation(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(50),description VARCHAR(100),nump  INTEGER,nummed INTEGER);");
        db.execSQL("CREATE TABLE rendezv(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(50),description VARCHAR(100),nump  INTEGER,nummed INTEGER);");
        db.execSQL("CREATE TABLE gerant(id INTEGER PRIMARY KEY AUTOINCREMENT,prenom VARCHAR(100),nom VARCHAR(100),iduser INTEGER);");
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


    public User createUser(User user){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login",user.getLogin());
            cv.put("password",user.getPassword());
            cv.put("idrole",user.getRoles().getId());
            db.insert("user",null,cv);
            db.close();

            user = getLastUser();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public User getLastUser(){
        User user = new User();
        try {
            List<User> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("user",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    user.setId(c.getInt(c.getColumnIndex("id")));
                    user.setRoles(getRolesById(c.getInt(c.getColumnIndex("idrole"))));
                    user.setLogin(c.getString(c.getColumnIndex("login")));
                    user.setPassword(c.getString(c.getColumnIndex("password")));
                    Log.i("id user",user.getId()+" ");
                    list.add(user);
                    //String password = c.getString(c.getColumnIndex("password"));
                    //list.add(password);
                    c.moveToNext();

                }while(!c.isAfterLast());

            }
            db.close();

            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public User connexion (User user){

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user WHERE TRIM(login) = '"+user.getLogin().trim()+"' AND  TRIM(password) = '"+user.getPassword().trim()+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    user.setId(c.getInt(c.getColumnIndex("id")));
                    user.setRoles(getRolesById(c.getInt(c.getColumnIndex("idrole"))));
                    c.moveToNext();
                }while(!c.isAfterLast());
            }else {
                user = null;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public User getUserById(int id){
        User user = new User();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user WHERE id = '"+id+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    user.setId(c.getInt(c.getColumnIndex("id")));
                    user.setRoles(getRolesById(c.getInt(c.getColumnIndex("idrole"))));
                    user.setLogin(c.getString(c.getColumnIndex("login")));
                    user.setPassword(c.getString(c.getColumnIndex("password")));
                    c.moveToNext();
                }while(!c.isAfterLast());
            }else{
                user = null;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
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

    public List<User> readsUsers(){
        try {
            List<User> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("user",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("id")));
                    user.setRoles(getRolesById(c.getInt(c.getColumnIndex("idrole"))));
                    user.setLogin(c.getString(c.getColumnIndex("login")));
                    user.setPassword(c.getString(c.getColumnIndex("password")));
                    list.add(user);
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


    public boolean createPatient(Patient patient){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("prenom",patient.getPrenom());
            cv.put("nom",patient.getNom());
            cv.put("code",patient.getCode());
            cv.put("datenaiss",patient.getDatenaiss());
            cv.put("telephone",patient.getTelephone());
            cv.put("cni",patient.getCni());
            cv.put("email",patient.getEmail());
            cv.put("iduser",patient.getUser().getId());
            db.insert("patient",null,cv);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePatient(Patient patient){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login",login);
            cv.put("prenom",patient.getPrenom());
            cv.put("nom",patient.getNom());
            cv.put("code",patient.getCode());
            cv.put("datenaiss",patient.getDatenaiss());
            cv.put("telephone",patient.getTelephone());
            cv.put("cni",patient.getCni());
            cv.put("email",patient.getEmail());
            cv.put("iduser",patient.getUser().getId());
            db.update("patient",cv,"id='"+patient.getId()+"'",null);
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
            db.delete("patient","id='"+id+"'",null);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Patient getPatientById(int id){
        Patient patient = new Patient();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM patient WHERE id = '"+id+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    patient.setCode(c.getString(c.getColumnIndex("code")));
                    patient.setEmail(c.getString(c.getColumnIndex("email")));
                    patient.setCni(c.getString(c.getColumnIndex("cni")));
                    patient.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    patient.setNom(c.getString(c.getColumnIndex("nom")));
                    patient.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    patient.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    patient.setId(c.getInt(c.getColumnIndex("id")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("iduser")));
                    patient.setUser(user);
                    c.moveToNext();
                }while(!c.isAfterLast());
            }else{
                patient = null;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return patient;
    }

    public Patient getPatientByCode(String code){
        Patient patient = new Patient();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM patient WHERE code = '"+code+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    patient.setCode(c.getString(c.getColumnIndex("code")));
                    patient.setEmail(c.getString(c.getColumnIndex("email")));
                    patient.setCni(c.getString(c.getColumnIndex("cni")));
                    patient.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    patient.setNom(c.getString(c.getColumnIndex("nom")));
                    patient.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    patient.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    patient.setId(c.getInt(c.getColumnIndex("id")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("iduser")));
                    patient.setUser(user);
                    c.moveToNext();
                }while(!c.isAfterLast());
            }else{
                patient = null;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return patient;
    }
    public List<Patient> readPatient(){
        try {
            List<Patient> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query("patient",null,null,null,null,null,null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    Patient patient = new Patient();
                    patient.setCode(c.getString(c.getColumnIndex("code")));
                    patient.setEmail(c.getString(c.getColumnIndex("email")));
                    patient.setCni(c.getString(c.getColumnIndex("cni")));
                    patient.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    patient.setNom(c.getString(c.getColumnIndex("nom")));
                    patient.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    patient.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    patient.setId(c.getInt(c.getColumnIndex("id")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("iduser")));
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
        cv.put("iduser",medecin.getUser().getId());
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
            cv.put("iduser",medecin.getUser().getId());
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
    public Medecin getMedecinById(int id){
        Medecin medecin = new Medecin();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM medecin WHERE id = '"+id+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    medecin.setSpecialite(c.getString(c.getColumnIndex("specialite")));
                    medecin.setCni(c.getString(c.getColumnIndex("cni")));
                    medecin.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    medecin.setId(c.getInt(c.getColumnIndex("id")));
                    medecin.setNom(c.getString(c.getColumnIndex("nom")));
                    medecin.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    medecin.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("iduser")));
                    c.moveToNext();
                }while(!c.isAfterLast());
            }else{
                medecin = null;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return medecin;
    }

    public Medecin getMedecinByCode(String code){
        Medecin medecin = new Medecin();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM medecin WHERE code = '"+code+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    medecin.setSpecialite(c.getString(c.getColumnIndex("specialite")));
                    medecin.setCni(c.getString(c.getColumnIndex("cni")));
                    medecin.setDatenaiss(c.getString(c.getColumnIndex("datenaiss")));
                    medecin.setId(c.getInt(c.getColumnIndex("id")));
                    medecin.setNom(c.getString(c.getColumnIndex("nom")));
                    medecin.setPrenom(c.getString(c.getColumnIndex("prenom")));
                    medecin.setTelephone(c.getString(c.getColumnIndex("telephone")));
                    User user = new User();
                    user.setId(c.getInt(c.getColumnIndex("iduser")));
                    c.moveToNext();
                }while(!c.isAfterLast());
            }else{
                medecin = null;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return medecin;
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
                    user.setId(c.getInt(c.getColumnIndex("iduser")));
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
                    consultation.setDescription( c.getString(c.getColumnIndex("description")));
                    Patient patient = new Patient();
                    patient.setId(c.getInt(c.getColumnIndex("nump")));
                    patient = getPatientById(patient.getId());
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
                    rendezV.setDescription( c.getString(c.getColumnIndex("description")));
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
            cv.put("iduser",gerant.getUser().getId());
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
            cv.put("iduser",gerant.getUser().getId());
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
    public Roles getRolesByName(String nom){
        Roles roles = new Roles();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM roles WHERE TRIM(nom) = '"+nom.trim()+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    roles.setId(c.getInt(c.getColumnIndex("id")));
                    roles.setNom( c.getString(c.getColumnIndex("nom")));
                    c.moveToNext();
                }while(!c.isAfterLast());
            }
            db.close();
            return roles;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Roles getRolesById(int id){
        Roles roles = new Roles();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM roles WHERE id = '"+id+"'", null);
            if(c!=null && c.getCount()>0){
                c.moveToFirst();
                do{
                    roles.setId(c.getInt(c.getColumnIndex("id")));
                    roles.setNom( c.getString(c.getColumnIndex("nom")));
                    c.moveToNext();
                }while(!c.isAfterLast());
            }
            db.close();
            return roles;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
