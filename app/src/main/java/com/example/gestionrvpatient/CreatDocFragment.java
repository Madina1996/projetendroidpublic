package com.example.gestionrvpatient;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Roles;
import com.example.gestionrvpatient.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatDocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatDocFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txtFirstName,txtLastName,txtBirthday,txtCallNumber,txtCni,txtSpeciality;
    private Button btnfinish;
    private Medecin medecin;
    private BdRendezV bd;

    public CreatDocFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatDocFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatDocFragment newInstance(String param1, String param2) {
        CreatDocFragment fragment = new CreatDocFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creat_doc, container, false);
        txtFirstName = view.findViewById(R.id.txtFirstName);
        txtLastName = view.findViewById(R.id.txtLastName);
        txtBirthday = view.findViewById(R.id.txtBirthday);
        txtCallNumber = view.findViewById(R.id.txtCallNumber);
        txtCni = view.findViewById(R.id.txtCni);
        txtSpeciality = view.findViewById(R.id.txtSpeciality);
        btnfinish = view.findViewById(R.id.btnfinish);
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Roles roles = bd.getRolesByName("Medecin");
                user.setLogin("DR"+txtLastName.getText().toString().trim());
                user.setPassword("passer");
                user.setRoles(roles);
                User userresult = bd.createUser(user);
                if(userresult !=null) {
                    medecin.setCode(medecin.getNom() + user.getId());
                    medecin.setPrenom(txtFirstName.getText().toString().trim());
                    medecin.setNom(txtLastName.getText().toString().trim());
                    medecin.setSpecialite(txtSpeciality.getText().toString().trim());
                    medecin.setCni(txtCni.getText().toString().trim());
                    medecin.setDatenaiss(txtBirthday.getText().toString().trim());
                    medecin.setTelephone(txtCallNumber.getText().toString().trim());
                    medecin.setUser(user);
                    Boolean b = bd.createMedecin(medecin);
                    if (b){
                        Toast.makeText(getActivity(),"Medcin créé",Toast.LENGTH_LONG).show();
                        Log.i("create user","Medcin créé");
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.nav_host_fragment,new ListDocFragment())
                                .addToBackStack(null)
                                .commit();

                    }else {
                        Toast.makeText(getActivity(),"Medcin non créé",Toast.LENGTH_LONG).show();
                         
                    }
                }



            }
        });




        return view;
    }
}