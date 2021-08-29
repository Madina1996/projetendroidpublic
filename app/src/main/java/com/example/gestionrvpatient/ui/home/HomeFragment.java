package com.example.gestionrvpatient.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestionrvpatient.BdRendezV;
import com.example.gestionrvpatient.R;
import com.example.gestionrvpatient.model.CourseGVAdapter;
import com.example.gestionrvpatient.model.CourseModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    GridView coursesGV;
    private BdRendezV bd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bd = new BdRendezV(getContext());
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        coursesGV = root.findViewById(R.id.idGVcourses);

        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
        courseModelArrayList.add(new CourseModel(bd.readConsultations().size()+" Consultation", R.drawable.ic_consultation));
        courseModelArrayList.add(new CourseModel(bd.readRendezV().size()+" Rendez-vous", R.drawable.ic_rendezv));
        courseModelArrayList.add(new CourseModel(bd.readMedecin().size()+" Medecin", R.drawable.ic_medecin));
        courseModelArrayList.add(new CourseModel(bd.readPatient().size()+" Patients", R.drawable.ic_patient));

        CourseGVAdapter adapter = new CourseGVAdapter(getContext(), courseModelArrayList);
        coursesGV.setAdapter(adapter);

        return root;
    }
}