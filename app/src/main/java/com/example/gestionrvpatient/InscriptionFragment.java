package com.example.gestionrvpatient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

            public class InscriptionFragment extends Fragment {
                private EditText txt_FirstName, txt_LastName, txt_Password, txt_Confirmpassword, txt_Login;
                private Button btn_Save, btn_Cancel;
                private String firstName, lastName, password, confirmpassword, login;
                //private Spinner dropdown;

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_inscription2, container, false);

                Spinner dropdown = view.findViewById(R.id.DropdownRole);
                txt_FirstName= view.findViewById(R.id.txt_FirstName);
                txt_LastName= view.findViewById(R.id.txt_LastName);
                txt_Password= view.findViewById(R.id.txt_Password);
                txt_Confirmpassword= view.findViewById(R.id.txt_Confirmpassword);
                txt_Login= view.findViewById(R.id.txt_Login);
                String[] items = new String[] { "Gerant" , "Patient" };
                //getactivity()
                ArrayAdapter<String> adapter= new ArrayAdapter<String>
                        (getParentFragment().
                                getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                items);
                btn_Save=view.findViewById(R.id.btn_Save);
                btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName=txt_FirstName.getText().toString().trim();
                lastName=txt_LastName.getText().toString().trim();
                password=txt_Password.getText().toString().trim();
                confirmpassword=txt_Confirmpassword.getText().toString().trim();
                login=txt_Login.getText().toString().trim();
                dropdown.setAdapter(adapter);

                if (firstName.isEmpty() || lastName.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()||login.isEmpty())
                {
                    String message = getString(R.string.error_fields);
                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                }

            }
        });
            return view;
    }
}