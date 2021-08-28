package com.example.gestionrvpatient;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class RendezVousFragment extends Fragment {

    private EditText txt_dateappoinment, txt_description;
    private Button btn_Save, btn_Cancel;
    private String dateappoinment, description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_rendez_vous, container, false);

        Spinner dropdownP = view.findViewById(R.id.DropdownPatient);
        Spinner dropdowM = view.findViewById(R.id.DropdownMedecin);
        txt_dateappoinment= view.findViewById(R.id.txt_dateappoinment);
        txt_description= view.findViewById(R.id.txt_description);
        btn_Save=view.findViewById(R.id.btn_Save);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateappoinment=txt_dateappoinment.getText().toString().trim();
                description=txt_description.getText().toString().trim();
                if (dateappoinment.isEmpty() || description.isEmpty())
                {
                    String message = getString(R.string.error_fields);
                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}