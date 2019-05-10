package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;


import com.estm.GR_Location.Manager.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class Sender extends AsyncTask<Void,Void,String> implements Serializable {
    public static  Context c;
    public static  Context c2;
    String urlAddress;
    EditText nameTxt;
    String name;
    String resultat;
   static int code;
    static  String resp=null;


   // ProgressDialog  progressDialog;
   public static Trajet trajetObj ;
    public static Autocar autocarObj ;
    public static List<Pause> listPauses ;

    public static Societe societeObj ;

    public static boolean test = false ;
    public boolean check ;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public static Trajet getTrajetObj() {

        return trajetObj;
    }

    public static List<Pause> getListPauses() {

        return listPauses;
    }



    public static Societe getSocieteObj() {

        return societeObj;
    }

    public static Autocar getAutocarObj() {

        return autocarObj;
    }

    public Sender(){

    }
    ProgressDialog pdLoading ;
    public Sender(Context c, String address, EditText... editTexts) {
        this.c = c;
       this.urlAddress = address;
        pdLoading = new ProgressDialog(c) ;
        this.nameTxt=editTexts[0];
        name=nameTxt.getText().toString();

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       //this.result = new Result() ;
      //  pdLoading.setMessage("\tLoading...");
        //pdLoading.show();
        trajetObj = new Trajet();
        autocarObj = new Autocar() ;
        listPauses = new ArrayList<Pause>();
        societeObj = new Societe() ;
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.send();
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        if(response != null)
        {

             JSON2OBJECTS(response) ;
            test = true ;
           // Toast.makeText(c, "Result From Sender " + getTrajetObj().toString(),Toast.LENGTH_LONG).show();
           // Toast.makeText(c, "From sender" +getAutocarObj().toString() ,Toast.LENGTH_LONG).show();
            nameTxt.setText("");
          //  posTxt.setText("");
            this.setCheck(true);

           // Toast.makeText(c,"bienvenu .."+isCheck(),Toast.LENGTH_LONG).show();

           //login();

        }else
        {

        //    Toast.makeText(c,"Unsuccessful ",Toast.LENGTH_LONG).show();

            logout(false);

        }

        pdLoading.dismiss();

    }



    public static void logout(boolean t){
        if(t == true )
        Toast.makeText(c,"Erreur numero de Trajet non valide ",Toast.LENGTH_LONG).show();

        Intent myintent = new Intent(c,c.getClass()) ;
        Sender.trajetObj = null;
        Sender.societeObj = null ;
        Sender.listPauses = null ;
        Sender.autocarObj = null ;

        c.startActivity(myintent);
    }

    public  void JSON2OBJECTS(String response) {
        try {

        JSONObject jsonObj = new JSONObject(response);

            JSONObject autocar = jsonObj.getJSONObject("autocar");
            int idAu = Integer.parseInt(autocar.getString("id"));
            String nb_place = autocar.getString("nb_place");
            String km = autocar.getString("km");
            String Matricul = autocar.getString("Matricul");
            String Model = autocar.getString("Model");
            String Vitre_teinte = autocar.getString("Vitre_teinte");
            String Chouffage = autocar.getString("Chouffage");
            String image = autocar.getString("image");
            Boolean ch=Boolean.parseBoolean(Chouffage);
            Boolean vt=Boolean.parseBoolean(Vitre_teinte);

            autocarObj.setId(idAu);
            autocarObj.setChouffage(ch);
            autocarObj.setImage(image);
            autocarObj.setMatricul(Matricul);
            autocarObj.setKm(km);
            autocarObj.setNb_place(nb_place);
            autocarObj.setModel(Model);
            autocarObj.setVitre_teinte(vt);


            JSONObject trajet = jsonObj.getJSONObject("trajet");
            int idT = Integer.parseInt(trajet.getString("id"));
            String LAT_Arr = trajet.getString("LAT_Arrive");
            String LAT_Depart = (trajet.getString("LAT_Depart"));
            String Date_Arr_Reel = trajet.getString("Date_Arrive_Reel");
            String Date_Depart = trajet.getString("Date_Depart");
            String Date_Arr_Attendue = trajet.getString("Date_Arrive_Attendue");
            String LNG_Arr = trajet.getString("LNG_Arrive");
            String LNG_Depart = trajet.getString("LNG_Depart");

            trajetObj.setDate_Arr_Attendue(Date_Arr_Attendue);
            trajetObj.setDate_Arr_Reel(Date_Arr_Reel);
            trajetObj.setDate_Depart(Date_Depart);
            trajetObj.setId(idT);
            trajetObj.setLAT_Arr(LAT_Arr);
            trajetObj.setLAT_Depart(LAT_Depart);
            trajetObj.setLNG_Arr(LNG_Arr);
            trajetObj.setLNG_Depart(LNG_Depart);

            JSONObject societe = jsonObj.getJSONObject("societe");
            int idS = Integer.parseInt(societe.getString("id"));
            String nom = societe.getString("nom");
            String email = societe.getString("email");
            String adresse = societe.getString("adresse");
            String logo = societe.getString("logo");
            String tel = societe.getString("tel");
            String fix = societe.getString("fix");

            societeObj.setId(idS);
            societeObj.setAdresse(adresse);
            societeObj.setEmail(email);
            societeObj.setFix(fix);
            societeObj.setLogo(logo);
            societeObj.setNom(nom);
            societeObj.setTel(tel);


            JSONArray pauses = jsonObj.getJSONArray("pauses");
            for (int i = 0; i < pauses.length(); i++) {
                JSONObject pause = (JSONObject) pauses.get(i);
                Pause pauseObj = new Pause() ;
                String LNG = pause.getString("LNG");
                int idP = Integer.parseInt(pause.getString("id"));
                String LNGP = pause.getString("LNG");
                String LATP = pause.getString("LAT");
                int Duree = Integer.parseInt(pause.getString("Duree"));
                String nomP = pause.getString("nom");
                pauseObj.setNom(nomP);
                pauseObj.setId(idP);
                pauseObj.setDuree(Duree);
                pauseObj.setLAT(LATP);
                pauseObj.setLNG(LNGP);
                listPauses.add(pauseObj);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public   String send()
    {
        //CONNECT
        HttpURLConnection con= Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }
        try
        {
            OutputStream os=con.getOutputStream();
            //WRITE
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bw.write(new DataPackager(name).packData());
            bw.flush();
            //RELEASE RES
            bw.close();
            os.close();
            //HAS IT BEEN SUCCESSFUL?
            int responseCode=con.getResponseCode();
            code=responseCode;
            if(responseCode==con.HTTP_OK)
            {
                //GET EXACT RESPONSE
                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response=new StringBuffer();
                String line;
                //READ LINE BY LINE
                while ((line=br.readLine()) != null)
                {
                    response.append(line);
                }
                //RELEASE RES
                br.close();
               resp=response.toString();
                return response.toString();
            }else if(responseCode==con.HTTP_BAD_REQUEST)
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


   public static int getCode(){
       return code;
   }
    public  static String getResp()
    {return  resp;}

}