package com.example.gestionrvpatient;

import android.content.Intent;
import android.os.Bundle;

import com.example.gestionrvpatient.model.Gerant;
import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Patient;
import com.example.gestionrvpatient.model.Roles;
import com.example.gestionrvpatient.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity {
    private EditText txt_FirstName, txt_LastName, txt_Password, txt_Confirmpassword, txt_Login;
    private Button btn_Save, btn_Cancel;
    private String firstName, lastName, password, confirmpassword, login;
    Spinner dropdown;
    private List<String> ltmp = new ArrayList<>();
    private BdRendezV bd;
    //private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bd = new BdRendezV(this);
        if(bd.readRoles().size() == 0){
            Roles roles = new Roles();
            roles.setNom("Gerant");
            bd.createRoles(roles);
            roles.setNom("Patient");
            bd.createRoles(roles);
            roles.setNom("Medecin");
            bd.createRoles(roles);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dropdown = findViewById(R.id.DropdownRole);
        txt_FirstName= findViewById(R.id.txt_FirstName);
        txt_LastName= findViewById(R.id.txt_LastName);
        txt_Password= findViewById(R.id.txt_Password);
        txt_Confirmpassword= findViewById(R.id.txt_Confirmpassword);
        txt_Login= findViewById(R.id.txt_Login);
        //String[] items = new String[] { "Gerant" , "Patient","Medecin" };
        //ltmp.add("Gerant");
        List<Roles> lrole = bd.readRoles();
        for (Roles rol : lrole ){
            ltmp.add(rol.getNom());
        }

        //getactivity()
        ArrayAdapter<String> adapter= new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_dropdown_item,ltmp);
        dropdown.setAdapter(adapter);
        btn_Save=findViewById(R.id.btn_Save);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("selecter role",dropdown.getSelectedItem().toString());
                firstName=txt_FirstName.getText().toString().trim();
                lastName=txt_LastName.getText().toString().trim();
                password=txt_Password.getText().toString().trim();
                confirmpassword=txt_Confirmpassword.getText().toString().trim();
                login=txt_Login.getText().toString().trim();

                if (firstName.isEmpty() || lastName.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()||login.isEmpty())
                {
                    String message = getString(R.string.error_fields);
                    Toast.makeText(getBaseContext(),message,Toast.LENGTH_SHORT).show();
                    Log.i("sorti",message);
                }else if(!password.equals(confirmpassword)) {
                    Toast.makeText(getBaseContext(),"les deux mots de passe de correspondent pas"+password+" "+confirmpassword,Toast.LENGTH_SHORT).show();
                }else{
                    //Log.i("selecter role",dropdown.getSelectedItem().toString());
                    if(dropdown.getSelectedItem().toString().equals("Gerant")){
                        Roles roles = bd.getRolesByName("Gerant");
                        Gerant gerant = new Gerant();
                        User user  = new User();
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setRoles(roles);
                        User userresult = bd.createUser(user);
                        if(userresult != null ){
                            gerant.setNom(lastName);
                            gerant.setPrenom(firstName);
                            gerant.setUser(userresult);
                            if (bd.createGerant(gerant)){
                                Log.i("infos","Gerant Ajouté");
                                Intent intent=new Intent(InscriptionActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Log.i("infos","Gerant non  Ajouté");
                            }
                        }



                    }else if(dropdown.getSelectedItem().toString().equals("Patient")){
                        Roles roles = bd.getRolesByName("Patient");
                        Patient patient = new Patient();
                        User user  = new User();
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setRoles(roles);
                        User userresult = bd.createUser(user);
                        if(userresult !=null){
                            patient.setPrenom(firstName);
                            patient.setNom(lastName);
                            patient.setCode(patient.getNom()+user.getId());
                            patient.setEmail("exemple@exemple.com");
                            patient.setDatenaiss("00/00/0000");
                            patient.setCni("123456789");
                            patient.setTelephone("777777777");
                            patient.setUser(user);

                            if (bd.createPatient(patient)){
                                Log.i("infos","patient Ajouté");
                                Intent intent=new Intent(InscriptionActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Log.i("infos","patient non  Ajouté");
                            }
                        }
                    }else{
                        Roles roles = bd.getRolesByName("Medecin");
                        Medecin medecin = new Medecin();
                        User user  = new User();
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setRoles(roles);
                        User userresult = bd.createUser(user);
                        if(userresult !=null){
                            medecin.setPrenom(firstName);
                            medecin.setNom(lastName);
                            medecin.setCode(medecin.getNom()+user.getId());
                            medecin.setSpecialite("Generaliste");
                            medecin.setDatenaiss("00/00/0000");
                            medecin.setCni("123456789");
                            medecin.setTelephone("777777777");
                            medecin.setUser(user);

                            if (bd.createMedecin(medecin)){
                                Log.i("infos","medecin Ajouté");
                                Intent intent=new Intent(InscriptionActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Log.i("infos","medecin non  Ajouté");
                            }
                        }
                    }



                }

            }
        });
    }
}