package com.estm.GR_Location;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */

public class autocar extends Fragment {
    TextView model,matricul,nb_place,km,vitre,chouffage;


    public autocar() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  v=inflater.inflate(R.layout.fragment_autocar, container, false);

       model=(TextView) v.findViewById(R.id.Model);
        matricul=(TextView) v.findViewById(R.id.Matricule);
        nb_place=(TextView) v.findViewById(R.id.nb_place);
        km=(TextView) v.findViewById(R.id.km);
        vitre=(TextView) v.findViewById(R.id.vivtre_teinte);
        chouffage=(TextView) v.findViewById(R.id.chouffage);


String mdl=Sender.getAutocarObj().getModel();
        model.setText(mdl);
String matr=Sender.getAutocarObj().getMatricul();
        matricul.setText(matr);
String nb=Sender.getAutocarObj().getNb_place();
        nb_place.setText(nb);
String k=Sender.getAutocarObj().getKm()+" km";
        km.setText(k);
String vite=Sender.getAutocarObj().isVitre_teinte()+"";
        vitre.setText(vite);
String chf=Sender.getAutocarObj().isChouffage()+"";
        chouffage.setText(chf);
        ImageView logoaut = (ImageView) v.findViewById(R.id.imageView2);
        String ur = Sender.getAutocarObj().getImage();

        StringBuffer sbr = new StringBuffer(ur);
        sbr.replace(7, 21, "192.168.42.79");
        final String imgurl = sbr.toString();
        Picasso.with(v.getContext()) //The context of your activity
                .load(imgurl)
                .into(logoaut);


    return v;
    }





}
