package com.estm.GR_Location;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estm.GR_Location.manager_Dr.Trajet;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static Trajet trajet ;
    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        trajet = getArguments().getParcelable("trajet");
        View v = null;

        v = inflater.inflate(R.layout.fragment_home, container, false );
        return v ;

    }

}
