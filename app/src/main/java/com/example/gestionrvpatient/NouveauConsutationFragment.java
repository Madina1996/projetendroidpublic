package com.example.gestionrvpatient;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gestionrvpatient.model.Consultation;
import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NouveauConsutationFragment extends Fragment {

    private EditText txt_description;
    private Button btn_Save, btn_Cancel,btnadd_patient;
    private String description;
    Spinner dropdownPatient;
    private BdRendezV bd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = new BdRendezV(this.getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nouveau_consutation, container, false);

        dropdownPatient = view.findViewById(R.id.DropdownPatient);

        List<Patient> patientList = bd.readPatient();
        List<String> ltmp = new ArrayList<>();
        ltmp.add("Selectionnez");
        for (Patient patient : patientList){
            ltmp.add(patient.getCode());
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<String>
                (this.getContext(),android.R.layout.simple_spinner_dropdown_item,ltmp);

        dropdownPatient.setAdapter(adapter);

        txt_description = view.findViewById(R.id.txt_description);
        btn_Cancel = view.findViewById(R.id.btn_Cancel);
        btn_Save = view.findViewById(R.id.btn_Save);
        btnadd_patient = view.findViewById(R.id.btnadd_patient);
        btnadd_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new PatientFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new ConsultationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description=txt_description.getText().toString().trim();
                Patient patient = bd.getPatientByCode(dropdownPatient.getSelectedItem().toString());
                if (patient != null){
                    Consultation consultation = new Consultation();
                    consultation.setDatec(new Date().toString());
                    consultation.setDescription(description);
                    consultation.setPatient(patient);
                    Medecin medecin = new Medecin();
                    medecin.setId(1);
                    consultation.setMedecin(medecin);
                    try {
                        if (bd.createConsultation(consultation)){
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.nav_host_fragment,new ConsultationFragment())
                                    .addToBackStack(null)
                                    .commit();
                            Log.i("infos consultation","consultation   créé");
                        }else{
                            Log.i("infos consultation","consultation non créé");
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else {
                    Log.i("info patient","inpossible de recuperer le patient");
                }
                //Patient patient = bd.


            }
        });

        return view;
    }
}