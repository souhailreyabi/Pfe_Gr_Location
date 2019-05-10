package com.estm.GR_Location;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estm.GR_Location.Manager.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class voyage extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    MapView mapView;
    View mview;
List<Pause> liste;




    public voyage() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_voyage, container, false);

        return mview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mview.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        map = googleMap;
        double latdep=Double.parseDouble(Sender.getTrajetObj().getLAT_Depart());
        double latarr=Double.parseDouble(Sender.getTrajetObj().getLAT_Arr());
        double lnarr=Double.parseDouble(Sender.getTrajetObj().getLNG_Arr());
        double lndep=Double.parseDouble(Sender.getTrajetObj().getLNG_Depart());
        LatLng depart=new LatLng(latdep,lndep);
        LatLng arrive=new LatLng(latarr,lnarr);
        Polyline lina=googleMap.addPolyline(new PolylineOptions().add(depart).width(10).color(R.color.colorPrimary).geodesic(true));

        ArrayList<LatLng> po = new ArrayList<LatLng>();
        po.add(depart);

        int i =0;
        liste = Sender.getListPauses();
            for (Pause p :liste){
                i++;
                double latp=Double.parseDouble(p.getLAT());
                double lngp=Double.parseDouble(p.getLNG());
                LatLng paus=new LatLng(latp,lngp);
                Marker pause= googleMap.addMarker(new MarkerOptions().position(paus).title("Pause"+i).snippet(" duree :" +p.getDuree()+"Min"));
                CameraPosition P = CameraPosition.builder().target(depart).zoom(8).bearing(0).tilt(45).build();
                pause.showInfoWindow();
                po.add(paus);
            }


            po.add(arrive);

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Marker dep= googleMap.addMarker(new MarkerOptions().position(depart).title("depart").snippet(" duree :" +Sender.getTrajetObj().getDate_Depart()));
        CameraPosition cdep = CameraPosition.builder().target(depart).zoom(8).bearing(0).tilt(45).build();
        Marker ARR= googleMap.addMarker(new MarkerOptions().position(arrive).title("arrive").snippet(" date :" +Sender.getTrajetObj().getDate_Depart()));
        CameraPosition Carr = CameraPosition.builder().target(arrive).zoom(8).bearing(0).tilt(45).build();

        dep.showInfoWindow();
        ARR.showInfoWindow();
        googleMap.setBuildingsEnabled(true);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cdep));
        Polyline line=googleMap.addPolyline(new PolylineOptions().addAll(po).width(10).color(R.color.colorPrimary).geodesic(true));
    }
}
