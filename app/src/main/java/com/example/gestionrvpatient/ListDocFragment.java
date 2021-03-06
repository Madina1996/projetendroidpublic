package com.example.gestionrvpatient;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.gestionrvpatient.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListDocFragment extends Fragment {
    private ListView listMedcin;
    private String medcin, details;
    private List<Medecin> tabmedcin, tabDetails;
    private List<String> tempmedin = new ArrayList<>();
    private BdRendezV bd;
    FloatingActionButton fabAddMedcin;
    public ListDocFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = new BdRendezV(this.getContext());
        if (bd.readMedecin().size() == 0){
            Medecin medecin = new Medecin();
            medecin.setTelephone("778538538");
            medecin.setDatenaiss("20/08/1980");
            medecin.setCni("122232656329659");
            medecin.setSpecialite("dentiste");
            medecin.setNom("Ndiaye");
            medecin.setPrenom("Mame diarra");
            medecin.setCode("xxxx1");
            User user = new User();
            user.setId(1);
            medecin.setUser(user);
            if (bd.createMedecin(medecin)){
                Log.i("sorti"," medcin 1 ajouter");
            }else {
                Log.i("sorti"," medcin 1 non ajouter");
            }
            medecin.setTelephone("778538538");
            medecin.setDatenaiss("20/08/1980");
            medecin.setCni("122232656329659");
            medecin.setSpecialite("dentiste");
            medecin.setNom("Diam??");
            medecin.setPrenom("Aissatou");
            medecin.setCode("xxxx1");
            user.setId(1);
            medecin.setUser(user);
            if (bd.createMedecin(medecin)){
                Log.i("sorti"," medcin 2 ajouter");
            }else {
                Log.i("sorti"," medcin 2 non ajouter");
            }
        }

        View view = inflater.inflate(R.layout.fragment_list_doc, container, false);
        listMedcin = view.findViewById(R.id.listDoctor);
        tabmedcin = bd.readMedecin();
        for(Medecin m : tabmedcin){
            tempmedin.add("Dr "+tabmedcin.get(tempmedin.size()).getNom()+" "+tabmedcin.get(tempmedin.size()).getSpecialite());
        }
        ArrayAdapter<String> adapter;
        if (tempmedin.size() != 0){
             adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,tempmedin );
            listMedcin.setAdapter(adapter);
        }

        listMedcin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medecin med  = tabmedcin.get(position);
                //afficher une boitede dialogue
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("details");
                dialog.setMessage(
                        med.getPrenom() +" "+ med.getNom()+"\n\n"+
                        "Specialit?? : "+med.getSpecialite()+"\n\n"+
                        "Telephone : "+med.getTelephone()+"\n\n"+
                        "CNI : "+med.getCni()+"\n\n"+
                        "Date de N. : "+med.getDatenaiss()
                );
                dialog.setPositiveButton(getString(R.string.done), null);
                dialog.show();//Affiche la boite de dialogue
            }
        });
        fabAddMedcin = view.findViewById(R.id.fabAddMedcin);
        fabAddMedcin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,new CreatDocFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }
}