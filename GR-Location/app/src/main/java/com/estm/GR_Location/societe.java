package com.estm.GR_Location;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class societe extends Fragment {
TextView noms,adresses,emails,fix,tel;
ImageView logosoc;
RatingBar rt;
    Button envoyer;
    public societe() {
        // Required empty public constructor


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                View v = inflater.inflate(R.layout.fragment_societe, container, false);

        noms = (TextView) v.findViewById(R.id.nom);
        adresses = (TextView)v.findViewById(R.id.adress);
        emails = (TextView)v.findViewById(R.id.emails);
        tel = (TextView) v.findViewById(R.id.tel);
        fix  = (TextView) v.findViewById(R.id.fix);
        logosoc=(ImageView) v.findViewById(R.id.logos);
        int ids=Sender.getSocieteObj().getId();
         String snom=Sender.getSocieteObj().getNom();
        noms.setText(snom);
        String sadresse=Sender.getSocieteObj().getAdresse();
        adresses.setText(sadresse);
        String semail=Sender.getSocieteObj().getEmail();
        emails.setText(semail);
        String stelp=Sender.getSocieteObj().getTel();
        tel.setText(stelp);

        String fixs=Sender.getSocieteObj().getFix();
        fix.setText(fixs);

        rt=(RatingBar)v.findViewById(R.id.ratingBar);
        final String sids=ids+"";
        String ur=Sender.getSocieteObj().getLogo();
        StringBuffer sbr=new StringBuffer(ur);
        sbr.replace(7,21,"192.168.42.79");
        final String imgurl=sbr.toString();

        Picasso.with(v.getContext()) //The context of your activity
                .load(imgurl)
                .into(logosoc);

envoyer=(Button) v.findViewById(R.id.avisenvoyer);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a=rt.getRating();
                Toast.makeText(getContext(),a+"",Toast.LENGTH_LONG).show();

        new SenderAvis(v.getContext(),"http://192.168.42.79/GR_locat/public/Services/ClientServices/AddAvis",a+"",sids).execute();
                Toast.makeText(v.getContext(),"Waiting",Toast.LENGTH_LONG).show();
if(SenderAvis.getRes()==null){
    Toast.makeText(v.getContext(),"Non Envoye resseyer....!!",Toast.LENGTH_LONG).show();
}else {
    Toast.makeText(v.getContext(),"Bien Envoye",Toast.LENGTH_LONG).show();
    envoyer.setEnabled(false);
    rt.setEnabled(false);
    rt.setRating(a);
}
            }
        });




        return  v;
    }



}
