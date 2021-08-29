package com.example.gestionrvpatient;

import android.app.AlertDialog;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
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

import androidx.fragment.app.Fragment;

import com.example.gestionrvpatient.model.Consultation;
import com.example.gestionrvpatient.model.Medecin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ConsultationFragment extends Fragment {

    private ListView listConsultation;
    private String consultation, details;
    private List<Consultation> tabconsultation, tabDetails;
    private List<String> tempconsultation = new ArrayList<>();
    private BdRendezV bd;
    private FloatingActionButton fabAddConsultation;

    public ConsultationFragment() {
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
        bd = new BdRendezV(this.getContext());
        View view = inflater.inflate(R.layout.fragment_consultation, container, false);
        fabAddConsultation = view.findViewById(R.id.fabAddConsultation);
        fabAddConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new NouveauConsutationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        listConsultation = view.findViewById(R.id.listConsultation);
        tabconsultation = bd.readConsultations();
        for(Consultation c : tabconsultation){
            tempconsultation.add("Patient N° "+tabconsultation.get(tempconsultation.size()).getPatient().getCode() +" "+tabconsultation.get(tempconsultation.size()).getDescription());
        }
        ArrayAdapter<String> adapter;
        if (tempconsultation.size() != 0){
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,tempconsultation );
            listConsultation.setAdapter(adapter);
        }

        listConsultation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Consultation cons  = tabconsultation.get(position);
                Log.i("desc consu",cons.getDescription());
                //afficher une boitede dialogue
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.drawable.ic_patient);
                dialog.setTitle("details");
                dialog.setMessage(
                                "Date de Consul. : "+cons.getDatec()+"\n\n"+
                                "Description : "+cons.getDescription()

                );
                dialog.setPositiveButton(getString(R.string.done), null);
                dialog.show();//Affiche la boite de dialogue
            }
        });



        return view;
    }
}