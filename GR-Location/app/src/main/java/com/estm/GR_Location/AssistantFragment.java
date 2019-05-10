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

import com.estm.GR_Location.manager_Dr.Employer;

import java.io.InputStream;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssistantFragment extends Fragment {

    public static Employer assistant ;
    public AssistantFragment() {
        // Required empty public constructor
    }

    TextView nomA ;
    TextView emailA ;
    TextView telA ;
    TextView roleA ;
    ImageView avatarA ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assistant, container, false);
        assistant = getArguments().getParcelable("assistant");
        emailA = (TextView) v.findViewById(R.id.emailA) ;
        telA = (TextView) v.findViewById(R.id.telA) ;
        nomA = (TextView) v.findViewById(R.id.nomA) ;
        roleA = (TextView) v.findViewById(R.id.roleA) ;
        avatarA = (ImageView) v.findViewById(R.id.avatarA) ;
        emailA.setText(assistant.getEmail());
        telA.setText(assistant.getTel());
        roleA.setText(assistant.getRole());
        nomA.setText(assistant.getNom());
        StringBuffer imgURL = new StringBuffer(assistant.getAvatar());

        new DownLoadImageTask(avatarA).execute(imgURL.replace(7, 20, "192.168.42.79").toString());

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
