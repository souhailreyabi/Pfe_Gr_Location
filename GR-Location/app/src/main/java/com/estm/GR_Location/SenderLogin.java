package com.estm.GR_Location;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.estm.GR_Location.manager_Dr.*;
import com.estm.GR_Location.manager_Dr.Autocar;
import com.estm.GR_Location.manager_Dr.Employer;
import com.estm.GR_Location.manager_Dr.Societe;
import com.estm.GR_Location.manager_Dr.Trajet;

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


public class SenderLogin extends AsyncTask<Void,Void,String> implements Serializable {
    public static  Context c;
    public static  Context c2;
    String urlAddress;
    EditText nameTxt,posTxt;
    String name,pos;


   //ProgressDialog  progressDialog;
   public static Trajet trajetObj ;
    public static Autocar autocarObj;
    public static List<Pauses> listPauses;
    public static Employer assistantObj;
    public static Employer chauffeurObj;
    public static Societe societeObj;

    public static boolean test = false ;
    public boolean check ;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public static  Trajet getTrajetObj() {

        return trajetObj;
    }

    public static List<Pauses> getListPauses() {

        return listPauses;
    }

    public static Employer getAssistantObj() {

        return assistantObj;
    }

    public static Employer getChauffeurObj() {

        return chauffeurObj;
    }

    public static Societe getSocieteObj() {

        return societeObj;
    }

    public static Autocar getAutocarObj() {

        return autocarObj;
    }

    public SenderLogin(){

    }
    ProgressDialog pdLoading ;
    public SenderLogin(Context c, String address, EditText... editTexts) {
        this.c = c;
       this.urlAddress = address;
        pdLoading = new ProgressDialog(c) ;
        this.nameTxt=editTexts[0];
        this.posTxt=editTexts[1];

        name=nameTxt.getText().toString();
        pos=posTxt.getText().toString();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       //this.result = new Result() ;

        trajetObj = new Trajet();
        autocarObj = new Autocar() ;
        assistantObj = new Employer();
        chauffeurObj = new Employer() ;
        listPauses = new ArrayList<Pauses>();
        societeObj = new Societe() ;
        pdLoading.setMessage("\tLoading...");
        pdLoading.show();
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
           // test = true ;
           // Toast.makeText(c, "Result From SenderLogin " + getTrajetObj().toString(),Toast.LENGTH_LONG).show();
         //  Toast.makeText(c, "From sender" +getSocieteObj().toString() ,Toast.LENGTH_LONG).show();
            nameTxt.setText("");
            posTxt.setText("");
            //this.setCheck(true);
            // login();
            Toast.makeText(c,"Login successful ",Toast.LENGTH_LONG).show();
        }else
        {

            Toast.makeText(c,"Login Unsuccessful ",Toast.LENGTH_LONG).show();

          //  logout(false);

        }

        pdLoading.dismiss();

    }



    public static void logout(boolean t){
        if(t == true ) Toast.makeText(c,"Email Or Password doesn't exists",Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = c.getSharedPreferences("prefs.xml", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("logedIn", false).apply();
        Intent myintent = new Intent(c,c.getClass()) ;
       SenderLogin.trajetObj = null;
        SenderLogin.societeObj = null ;
        SenderLogin.listPauses = null ;
        SenderLogin.autocarObj = null ;
        SenderLogin.assistantObj = null ;
        SenderLogin.chauffeurObj = null ;
        c.startActivity(myintent);
    }

    public void JSON2OBJECTS(String response) {
        try {

        JSONObject jsonObj = new JSONObject(response);

            JSONObject trajet = jsonObj.getJSONObject("trajet");
            int idT = Integer.parseInt(trajet.getString("id"));
            String LAT_Arr = trajet.getString("LAT_Arrive");
            String LAT_Depart = (trajet.getString("LAT_Depart"));
            String Date_Arr_Reel = trajet.getString("Date_Arrive_Reel");
            String Date_Depart = trajet.getString("Date_Depart");
            String Date_Arr_Attendue = trajet.getString("Date_Arrive_Attendue");
            String LNG_Arr = trajet.getString("LNG_Arrive");
            String LNG_Depart = trajet.getString("LNG_Depart");
            //trajetObj = new Trajet(LAT_Depart, idT, LAT_Arr, Date_Arr_Reel, Date_Depart, Date_Arr_Attendue, LNG_Arr, LNG_Depart);
            trajetObj.setDate_Arr_Attendue(Date_Arr_Attendue);
            trajetObj.setDate_Arr_Reel(Date_Arr_Reel);
            trajetObj.setDate_Depart(Date_Depart);
            trajetObj.setId(idT);
            trajetObj.setLAT_Arr(LAT_Arr);
            trajetObj.setLAT_Depart(LAT_Depart);
            trajetObj.setLNG_Arr(LAT_Arr);
            trajetObj.setLNG_Depart(LNG_Depart);
            trajetObj.setLNG_Arr(LNG_Arr);
            Toast.makeText(c,"From sender 2:"+ SenderLogin.getTrajetObj().toString(),Toast.LENGTH_LONG).show();

            JSONObject societe = jsonObj.getJSONObject("societe");
            int idS = Integer.parseInt(societe.getString("id"));
            String nom = societe.getString("nom");
            String email = societe.getString("email");
            String adresse = societe.getString("adresse");
            String logo = societe.getString("logo");
            String tel = societe.getString("tel");
            String fix = societe.getString("fix");
            // societeObj = new Societe(idS, nom, email, adresse, logo, tel, fix);
            societeObj.setId(idS);
            societeObj.setAdresse(adresse);
            societeObj.setEmail(email);
            societeObj.setFix(fix);
            societeObj.setLogo(logo);
            societeObj.setNom(nom);
            societeObj.setTel(tel);

             //listPauses = new ArrayList<Pauses>();
            JSONArray pauses = jsonObj.getJSONArray("pauses");
            for (int i = 0; i < pauses.length(); i++) {
                JSONObject pause = (JSONObject) pauses.get(i);
                Pauses pauseObj = new Pauses() ;
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
            JSONObject chauffeur = jsonObj.getJSONObject("chauffeur");
            int idC = Integer.parseInt(chauffeur.getString("id"));
            String roleC = chauffeur.getString("role");
            String emailC = chauffeur.getString("email");
            String nomC = chauffeur.getString("nom");
            String avatarC = chauffeur.getString("avatar");
            String telC = chauffeur.getString("tel");
            //chauffeurObj = new Employer(idC, roleC, emailC, nomC, avatarC, telC);
            chauffeurObj.setNom(nomC);
            chauffeurObj.setTel(telC);
            chauffeurObj.setId(idC);
            chauffeurObj.setEmail(emailC);
            chauffeurObj.setAvatar(avatarC);
            chauffeurObj.setRole( roleC);
            JSONObject assistant = jsonObj.getJSONObject("assistant");
            int idA = Integer.parseInt(assistant.getString("id"));
            String roleA = assistant.getString("role");
            String emailA = assistant.getString("email");
            String nomA = assistant.getString("nom");
            String avatarA = assistant.getString("avatar");
            String telA = assistant.getString("tel");
            //assistantObj = new Employer(idA, roleA, emailA, nomA, avatarA, telA);
            assistantObj.setNom(nomA);
            assistantObj.setRole(roleA);
            assistantObj.setTel(telA);
            assistantObj.setId(idA);
            assistantObj.setEmail(emailA);
            assistantObj.setAvatar(avatarA);

            JSONObject autocar = jsonObj.getJSONObject("autocar");
            int idAu = Integer.parseInt(autocar.getString("id"));
            String nb_place = autocar.getString("nb_place");
            String km = autocar.getString("km");
            String Matricul = autocar.getString("Matricul");
            String Model = autocar.getString("Model");
            String Vitre_teinte = autocar.getString("Vitre_teinte");
            String Chouffage = autocar.getString("Chouffage");
            String image = autocar.getString("image");
            //j autocarObj = new Autocar(idAu, nb_place, km, Matricul, Model, Vitre_teinte, Chouffage, image);
            autocarObj.setId(idAu);
            autocarObj.setChouffage(Chouffage);
            autocarObj.setImage(image);
            autocarObj.setMatricul(Matricul);
            autocarObj.setKm(km);
            autocarObj.setNb_place(nb_place);
            autocarObj.setModel(Model);
            autocarObj.setVitre_teinte(Vitre_teinte);
          //  Toast.makeText(c, "From sender" +autocarObj.toString() ,Toast.LENGTH_LONG).show();
            //this.setTrajetObj(trajetObj);
           /* this.result.setTrajet(trajetObj);
            this.result.setAutocar(autocarObj);
            this.result.setPause(listPauses);
            this.result.setSociete(societeObj);
            this.result.setAssistant(assistantObj);
            this.result.setChauffeur(chauffeurObj);*/


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String send()
    {
        //CONNECT
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }
        try
        {
            OutputStream os=con.getOutputStream();
            //WRITE
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bw.write(new DataPackagerLogin(name,pos).packData());
            bw.flush();
            //RELEASE RES
            bw.close();
            os.close();
            //HAS IT BEEN SUCCESSFUL?
            int responseCode=con.getResponseCode();
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
                return response.toString();
            }else if(responseCode==con.HTTP_BAD_REQUEST)
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}