package com.example.gestionrvpatient;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gestionrvpatient.model.Medecin;
import com.example.gestionrvpatient.model.Patient;
import com.example.gestionrvpatient.model.Roles;
import com.example.gestionrvpatient.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txtNom, txtPrenom, txtDateNaiss,txtTel, txtcni;
    private Button btnSave;
    private Patient patient;
    private BdRendezV bd;

    public PatientFragment() {
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
        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        txtNom = view.findViewById(R.id.txtNom);
        txtPrenom = view.findViewById(R.id.txtPrenom);
        txtDateNaiss = view.findViewById(R.id.txtDateNaiss);
        txtTel = view.findViewById(R.id.txtTel);
        txtcni = view.findViewById(R.id.txtCni);
        btnSave = view.findViewById(R.id.btn_Save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Roles roles = bd.getRolesByName("Patient");
                user.setLogin("Patient"+txtNom.getText().toString().trim());
                user.setPassword("passer");
                user.setRoles(roles);
                User userresult = bd.createUser(user);
                if(userresult !=null) {
                    patient.setCode(patient.getNom() + user.getId());
                    patient.setPrenom(txtNom.getText().toString().trim());
                    patient.setNom(txtPrenom.getText().toString().trim());
                    patient.setCni(txtcni.getText().toString().trim());
                    patient.setDatenaiss(txtDateNaiss.getText().toString().trim());
                    patient.setTelephone(txtTel.getText().toString().trim());
                    patient.setUser(user);
                    Boolean b = bd.createPatient(patient);
                    if (b){
                        Toast.makeText(getActivity(),"Patient créé",Toast.LENGTH_LONG).show();
                        Log.i("create user","Patient créé");
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.nav_host_fragment,new ConsultationFragment())
                                .addToBackStack(null)
                                .commit();

                    }else {
                        Toast.makeText(getActivity(),"Patient non créé",Toast.LENGTH_LONG).show();

                    }
                }



            }
        });

        return view;
    }
}