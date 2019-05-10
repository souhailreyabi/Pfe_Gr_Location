package com.estm.GR_Location;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.estm.GR_Location.manager_Dr.Societe;

import java.io.InputStream;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocieteFragment extends Fragment {

public  static Societe societe ;
    public SocieteFragment() {
        // Required empty public constructor
    }
    TextView nomS ;
    TextView emailS ;
    TextView telS ;
    TextView fixS ;
    TextView adresseS ;
    ImageView logoS ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_societe_dr, container, false);
        societe = getArguments().getParcelable("societe");
        nomS = (TextView) v.findViewById(R.id.nomS) ;
        emailS = (TextView) v.findViewById(R.id.emailS) ;
        telS = (TextView) v.findViewById(R.id.telS) ;
        fixS = (TextView) v.findViewById(R.id.fixS) ;
        adresseS = (TextView) v.findViewById(R.id.adresseS) ;
        logoS = (ImageView) v.findViewById(R.id.avatarS) ;
        nomS.setText(societe.getNom());
        emailS.setText(societe.getEmail());
        telS.setText(societe.getTel());
        fixS.setText(societe.getFix());
        adresseS.setText(societe.getAdresse());
        StringBuffer imgURL = new StringBuffer(societe.getLogo());

        new DownLoadImageTask(logoS).execute(imgURL.replace(7, 20, "192.168.42.132").toString());

        return v ;
    }
    class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
