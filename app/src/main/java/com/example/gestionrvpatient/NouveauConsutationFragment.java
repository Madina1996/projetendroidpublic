package com.example.gestionrvpatient;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class NouveauConsutationFragment extends Fragment {

    private EditText txt_description;
    private Button btn_Save, btn_Cancel;
    private String description;
    Spinner dropdownPatient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nouveau_consutation, container, false);

        Spinner dropdownPatient = view.findViewById(R.id.DropdownPatient);
        txt_description= view.findViewById(R.id.txt_description);
        btn_Save=view.findViewById(R.id.btn_Save);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description=txt_description.getText().toString().trim();


            }
        });
        return view;
    }
}