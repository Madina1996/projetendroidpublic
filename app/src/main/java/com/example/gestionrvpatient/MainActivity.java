package com.example.gestionrvpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionrvpatient.model.Roles;
import com.example.gestionrvpatient.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText txtLogin, txtPassword;
    private Button btnConnect, btnSignUp;
    private String login, password;
    private BdRendezV bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bd = new BdRendezV(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(bd.readRoles().size() == 0){
            Roles roles = new Roles();
            roles.setNom("Gerant");
            if ( bd.createRoles(roles))
                Log.i("create role","role Gerant cree");
            roles.setNom("Patient");
            if ( bd.createRoles(roles))
                Log.i("create role","role Patient cree");
            roles.setNom("Medecin");
            if ( bd.createRoles(roles))
                Log.i("create role","role Medecin cree");
        }else {
            Log.i("role existant",bd.readRoles().size()+" " );
        }
        txtLogin= findViewById(R.id.txtLogin);
        txtPassword= findViewById(R.id.txtPassword);
        btnConnect= findViewById(R.id.btnConnect);
        btnSignUp= findViewById(R.id.btnSign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });
        //Geer le clique sur le button
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login =txtLogin.getText().toString().trim();
                password =txtPassword.getText().toString().trim();

                if(login.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, getString(R.string.error_fields), Toast.LENGTH_SHORT).show();
                }
                else{
                    User user = new User();
                    user.setPassword(password);
                    user.setLogin(login);
                    User rest = bd.connexion(user);
                    if(rest != null){
                        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, getString(R.string.error_fields), Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}