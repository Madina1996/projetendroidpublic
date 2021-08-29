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
import com.example.gestionrvpatient.model.RendezV;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListeRendezVousFragment extends Fragment {

    private ListView listRV;
    //private String rendezv, details;
    private List<RendezV> tabrv, tabDetails;
    private List<String> temprv = new ArrayList<>();
    private BdRendezV bd;
    FloatingActionButton fabAddRV;
    public ListeRendezVousFragment() {
        // Required empty public constructor
    }

    public void RendezVousFragment()
    {

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
        View view = inflater.inflate(R.layout.fragment_liste_rendez_vous, container, false);
        fabAddRV = view.findViewById(R.id.fabAddRV);
        fabAddRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new RendezVousFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        listRV = view.findViewById(R.id.listRV);
        tabrv = bd.readRendezV();
        for(RendezV c : tabrv){
            temprv.add("Rendez vous "+tabrv.get(temprv.size()).getPatient().getCode() +" avec Docteur"+tabrv.get(temprv.size()).getMedecin().getCode() +" le"+tabrv.get(temprv.size()).getDaterv());
        }
        ArrayAdapter<String> adapter;
        if (temprv.size() != 0){
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,temprv );
            listRV.setAdapter(adapter);
        }

        listRV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RendezV rendezV  = tabrv.get(position);
                Log.i("Detail rv",rendezV.getDescription());
                //afficher une boitede dialogue
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.drawable.ic_patient);
                dialog.setIcon(R.drawable.ic_medecin);
                dialog.setTitle("Details");
                dialog.setMessage(
                        "Medecin. : "+rendezV.getMedecin()+"\n\n"+
                                "Patient : "+rendezV.getPatient()

                );
                dialog.setPositiveButton(getString(R.string.done), null);
                dialog.show();//Affiche la boite de dialogue
            }
        });



        return view;
    }
}