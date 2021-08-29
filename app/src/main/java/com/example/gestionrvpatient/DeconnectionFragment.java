package com.example.gestionrvpatient;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DeconnectionFragment extends Fragment {

    public DeconnectionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_deconnection, container, false);
        Intent intent= new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        return view;

    }
}