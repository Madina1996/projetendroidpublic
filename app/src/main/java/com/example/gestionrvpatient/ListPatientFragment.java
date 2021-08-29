package com.example.gestionrvpatient;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Patient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListPatientFragment extends Fragment {

    private ListView listPatient;
    private String patient, details;
    private List<Patient> tabpatient, tabDetails;
    private List<String> temppatient = new ArrayList<>();
    private BdRendezV bd;
    FloatingActionButton fabAddPatient;

    public ListPatientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = new BdRendezV(this.getContext());
        if (bd.readPatient().size() == 0){
            Patient patient = new Patient();
            patient.setTelephone("778538538");
            patient.setDatenaiss("20/08/1980");
            patient.setCni("122232656329659");
            patient.setNom("Ndiaye");
            patient.setPrenom("Abdou");
            if (bd.createPatient(patient)){
                Log.i("sorti"," patient 1 ajouter");
            }else {
                Log.i("sorti"," patient 1 non ajouter");
            }
            patient.setTelephone("778538538");
            patient.setDatenaiss("20/08/1980");
            patient.setCni("122232656329659");
            patient.setNom("Diop");
            patient.setPrenom("Lamine");
            if (bd.createPatient(patient)){
                Log.i("sorti"," patient 2 ajouter");
            }else {
                Log.i("sorti"," patient 2 non ajouter");
            }
        }

        View view = inflater.inflate(R.layout.fragment_list_patient, container, false);
        listPatient = view.findViewById(R.id.listPatient);
        tabpatient = bd.readPatient();
        for(Patient p : tabpatient){
            temppatient.add("Patient "+tabpatient.get(temppatient.size()).getNom());
        }
        ArrayAdapter<String> adapter;
        if (temppatient.size() != 0){
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,temppatient );
            listPatient.setAdapter(adapter);
        }

        listPatient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Patient pat = tabpatient.get(position);
                //afficher une boitede dialogue
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("details");
                dialog.setMessage(
                        pat.getPrenom() +" "+ pat.getNom()+"\n\n"+
                                "Telephone : "+pat.getTelephone()+"\n\n"+
                                "CNI : "+pat.getCni()+"\n\n"+
                                "Date de N. : "+pat.getDatenaiss()
                );
                dialog.setPositiveButton(getString(R.string.done), null);
                dialog.show();//Affiche la boite de dialogue
            }
        });
        fabAddPatient = view.findViewById(R.id.fabAddPatient);
        fabAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new PatientFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }
}