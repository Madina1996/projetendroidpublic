package com.example.gestionrvpatient;

import android.os.Bundle;
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

import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Patient;
import com.example.gestionrvpatient.model.RendezV;

import java.util.ArrayList;
import java.util.List;

public class RendezVousFragment extends Fragment {

    private EditText txt_dateappoinment, txt_description;
    private Button btn_Save,btnadd_patient,btnadd_medecin;
    private String dateappoinment, description;
    private BdRendezV bd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rendez_vous, container, false);

        List<Patient> patientList = bd.readPatient();
        List<Medecin> medecinList = bd.readMedecin();
        List<String> ltmp = new ArrayList<>();
        List<String> ltmmed = new ArrayList<>();
        ltmp.add("Selectionnez");
        ltmmed.add("Selectionnez");
        for (Patient patient : patientList){
            ltmp.add(patient.getCode());
        }
        for (Medecin medecin : medecinList){
            ltmp.add(medecin.getCode());
        }

        ArrayAdapter<String> adapterP= new ArrayAdapter<String>
                (this.getContext(),android.R.layout.simple_spinner_dropdown_item,ltmp);
        ArrayAdapter<String> adapterM= new ArrayAdapter<String>
                (this.getContext(),android.R.layout.simple_spinner_dropdown_item,ltmmed);

        Spinner dropdownP = view.findViewById(R.id.DropdownPatient);
        Spinner dropdowM = view.findViewById(R.id.DropdownMedecin);
        dropdownP.setAdapter(adapterP);
        dropdowM.setAdapter(adapterM);

        txt_dateappoinment= view.findViewById(R.id.txt_dateappoinment);
        txt_description= view.findViewById(R.id.txt_description);

        btnadd_medecin=view.findViewById(R.id.btnadd_medecin);
        btnadd_medecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new CreatDocFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnadd_patient=view.findViewById(R.id.btnadd_patient);
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

        btn_Save=view.findViewById(R.id.btn_Save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RendezV rendezV= new RendezV();
                Log.i("Choisir un patient",dropdownP.getSelectedItem().toString());
                Log.d("Choisir un medecin",dropdowM.getSelectedItem().toString());
                dateappoinment=txt_dateappoinment.getText().toString().trim();
                description=txt_description.getText().toString().trim();
                if (dateappoinment.isEmpty() || description.isEmpty())
                {
                    String message = getString(R.string.error_fields);
                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                    Log.i("Sorti",message);
                }
                else
                {
                    Patient patient= bd.getPatientByCode(dropdownP.getSelectedItem().toString());
                    Medecin medecin=bd.getMedecinByCode(dropdowM.getSelectedItem().toString());
                    if(dropdowM.getSelectedItem().toString().isEmpty() && dropdownP.getSelectedItem().toString().isEmpty())
                    {
                        try {
                            rendezV.setDaterv(dateappoinment);
                            rendezV.setDescription(description);
                            rendezV.setPatient(patient);
                            rendezV.setMedecin(medecin);

                            boolean boolresultP = bd.createRendezV(rendezV);

                            if (boolresultP){
                                Toast.makeText(getActivity(),"Rendez vous cr???? avec succ??s",Toast.LENGTH_LONG).show();
                                Log.i("Create appointment","Rendez vous cr???? avec succ??s");
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.nav_host_fragment,new ListDocFragment())
                                        .addToBackStack(null)
                                        .commit();

                            }else {
                                Toast.makeText(getActivity(),"Rendez vous non cr????",Toast.LENGTH_LONG).show();

                            }

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }
                }

            }
        });
        return view;
    }
}