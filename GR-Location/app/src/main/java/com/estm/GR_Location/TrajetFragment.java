package com.estm.GR_Location;


import android.app.Fragment;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.estm.GR_Location.commun.MessageEvent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import com.estm.GR_Location.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrajetFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener
{
    GoogleMap googleMap;
    private Marker marker;
    View view;
    private Marker start;
    private Marker end;
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        view = inflater.inflate(R.layout.fragment_trajet, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment fragment = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.mapp);
        }else{
            fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapp);
        }
        fragment.getMapAsync(this);
        //ImageView imgstart = (ImageView) view.findViewById(R.id.start);
        //ImageView imgend = (ImageView) view.findViewById(R.id.end);
        //imgstart.setImageDrawable(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // test if
                if(start == null){
                    start = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Depart")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    start.showInfoWindow();
                }else{
                    end = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Arrivee")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    start.showInfoWindow();
                    end.showInfoWindow();
                }

            }
        });
        //LatLng marker = new LatLng(33.5439041, -7.6874399);
        //LatLng marker2 = new LatLng(33.5032797, -7.6556366);
        //LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 13));
        //boundsBuilder.include(marker);
        //boundsBuilder.include(marker2);
        //LatLngBounds bounds = boundsBuilder.build();
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 3));
        //googleMap.addMarker(new MarkerOptions().title("Start Point").position(marker));
        //googleMap.addMarker(new MarkerOptions().title("Start Point").position(marker2));

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        //Toast.makeText(getActivity(), "okok", Toast.LENGTH_LONG).show();
        //LatLng marker = new LatLng(Double.parseDouble(event.getLatVal()), Double.parseDouble(event.getLongVal()));
        if(marker!=null){
            marker.remove();
        }

        LatLng mLatLng = new LatLng(Double.parseDouble(event.getLatVal()), Double.parseDouble(event.getLongVal()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 13));
        marker = googleMap.addMarker(new MarkerOptions().position(mLatLng).title("Current Location").snippet("My Snippet").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));



        //LatLng marker = new LatLng(-33.867, 151.206);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(Double.parseDouble(event.)));
        //animateMarker(new Marker(), new LatLng(Double.parseDouble(event.getLatVal()), Double.parseDouble(event.getLongVal())), true);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 13));

        //googleMap.addMarker(new MarkerOptions().title("Hello Google Maps!").position(marker));
    }

    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = googleMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }



    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(getActivity(), "click", Toast.LENGTH_LONG).show();

    }
}