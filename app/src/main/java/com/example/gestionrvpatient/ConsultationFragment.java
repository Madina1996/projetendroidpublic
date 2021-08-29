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
    FloatingActionButton fabAddConsultation;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.item1){
            //What you want(Code Here)
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = new BdRendezV(this.getContext());
        if (bd.readConsultations().size() == 0){
            Consultation consultation = new Consultation();
            consultation.setDescription("20/08/1980");
            consultation.setDatec("10/06/2021");
            if (bd.createConsultation(consultation)){
                Log.i("sorti"," consultation 1 ajouter");
            }else {
                Log.i("sorti"," consultation 1 non ajouter");
            }
            consultation.setDescription("20/08/1980");
            consultation.setDatec("10/06/2021");
            if (bd.createConsultation(consultation)){
                Log.i("sorti"," consultation 2 ajouter");
            }else {
                Log.i("sorti"," consultation 2 non ajouter");
            }
        }

        View view = inflater.inflate(R.layout.fragment_list_doc, container, false);
        listConsultation = view.findViewById(R.id.listDoctor);
        tabconsultation = bd.readConsultations();
        for(Consultation c : tabconsultation){
            tempconsultation.add(" "+tabconsultation.get(tempconsultation.size()).getPatient()+" "+tabconsultation.get(tempconsultation.size()).getDescription());
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
                //afficher une boitede dialogue
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("details");
                dialog.setMessage(

                                "Description : "+cons.getDescription()+"\n\n"+
                                "Date de Consul. : "+cons.getDatec()
                );
                dialog.setPositiveButton(getString(R.string.done), null);
                dialog.show();//Affiche la boite de dialogue
            }
        });
        fabAddConsultation = view.findViewById(R.id.fabAddConsultation);
        fabAddConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new ConsultationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }
}