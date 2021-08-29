package com.example.gestionrvpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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