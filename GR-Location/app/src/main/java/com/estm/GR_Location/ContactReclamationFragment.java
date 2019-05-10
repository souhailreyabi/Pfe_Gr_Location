package com.estm.GR_Location;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.estm.GR_Location.manager_Dr.Trajet;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactReclamationFragment extends Fragment {

    View v ;
    Button btn_C ;
    Spinner typeC ;
    EditText sujetC ;
    EditText textC ;
    SenderContact sc ;
    String type ;
    String subject ;
    String content ;
   public static Trajet trajet ;
    String urlAddress="http://192.168.42.79/GR_locat/public/Services/ChauffeurServices/Contact";

    public ContactReclamationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_contactreclamation, container, false) ;
        btn_C  =(Button) v.findViewById(R.id.btn_C);
        typeC  =(Spinner) v.findViewById(R.id.typeC);
         type = typeC.getSelectedItem().toString();
        sujetC  =(EditText) v.findViewById(R.id.sujetC);
         subject = sujetC.getText().toString() ;
        textC  =(EditText) v.findViewById(R.id.textC);
         content = textC.getText().toString() ;
         trajet = getArguments().getParcelable("trajet");
        btn_C  =(Button) v.findViewById(R.id.btn_C);
        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc=new SenderContact(getActivity(),urlAddress,trajet.getId() +"" ,type,sujetC,textC) ;
                sc.execute();

            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
