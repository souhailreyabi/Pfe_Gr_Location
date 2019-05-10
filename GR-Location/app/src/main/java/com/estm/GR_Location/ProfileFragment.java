package com.estm.GR_Location;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.estm.GR_Location.manager_Dr.Employer;

import java.net.URL;


import android.os.AsyncTask;

import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static Employer chauffeur ;
    public ProfileFragment() {
        // Required empty public constructor
    }

    TextView nomP ;
    TextView emailP ;
    TextView telP ;
    TextView roleP ;
    ImageView iv ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        chauffeur = getArguments().getParcelable("chauffeur");
        emailP = (TextView) v.findViewById(R.id.emailP) ;
        telP = (TextView) v.findViewById(R.id.telP) ;
        nomP = (TextView) v.findViewById(R.id.nomP) ;
        roleP = (TextView) v.findViewById(R.id.roleP) ;
        iv = (ImageView) v.findViewById(R.id.avatarP) ;
        nomP.setText(chauffeur.getNom());
        emailP.setText(chauffeur.getEmail());
        telP.setText(chauffeur.getTel());
        roleP.setText(chauffeur.getRole());
     //   final String imgURL  = "https://www.google.com/images/srpr/logo11w.png";


        /*try{
            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/c4/PM5544_with_non-PAL_signals.png");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            avatarP.setImageBitmap(bmp);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        StringBuffer imgURL = new StringBuffer(chauffeur.getAvatar());

        new DownLoadImageTask(iv).execute(imgURL.replace(7, 20, "192.168.42.132").toString());

        return v ;

    }
    class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
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
