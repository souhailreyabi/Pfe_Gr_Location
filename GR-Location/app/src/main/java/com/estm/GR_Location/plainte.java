 package com.estm.GR_Location;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 /**
 * A fragment with a Google +1 button.
 */
public class plainte extends Fragment {
     EditText sub,cnt,email,name;
     Button submit;
     String Idtr;





     public plainte() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


       View v=inflater.inflate(R.layout.fragment_plainte, container, false);
        cnt=(EditText) v.findViewById(R.id.contenu);
        sub=(EditText) v.findViewById(R.id.Sujet);
        email=(EditText) v.findViewById(R.id.email);
        name=(EditText) v.findViewById(R.id.nom);
        Idtr=Sender.getTrajetObj().getId()+"";
        submit=(Button) v.findViewById(R.id.envoyer);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  http://localhost/GR_locat/public/Services/ClientServices/AddReclamation
if(!isValidEmail(email) || sub.getText().toString().isEmpty() || name.getText().toString().isEmpty() || cnt.getText().toString().isEmpty() ){
   if( sub.getText().toString().isEmpty())
   sub.setError("Sujet est vide");
    if( cnt.getText().toString().isEmpty())
        cnt.setError("Pas de contenu saisie");
    if( name.getText().toString().isEmpty())
        name.setError("Veillez entrez votre nom");
    submit.setEnabled(true);
}else{


                new SenderPlainte(v.getContext(),"http://192.168.42.79/GR_locat/public/Services/ClientServices/AddReclamation",Idtr,sub,cnt,email,name).execute();

                Toast.makeText(v.getContext(),"Waiting",Toast.LENGTH_LONG).show();
                if(SenderPlainte.getRes()==null){

                    Toast.makeText(v.getContext(),"non Envoye ...Resseyer!!!!! ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(v.getContext(),"Bien Envoye",Toast.LENGTH_LONG).show();

                }
            }

            }

        });






        return v;
    }


         public final static boolean isValidEmail(EditText email) {
         boolean valid=false;
             if (email.getText().toString().isEmpty()) {
                 email.setError("N° Empty");
                 valid = false;


             } else {
                 valid=android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
                 if(valid==false)
                     email.setError(" email non valide");
             }
         return valid;
         }



}
