package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;


public class SenderPlainte extends AsyncTask<Void,Void,String> implements Serializable {
    public static  Context c;
    public static  Context c2;
    String urlAddress;
    EditText nameTxt,sujetTxt,ContenuTxt,emailTxt;
    String name,sujet,contenu,email,id;
    static String res=null;


    public static boolean test = false ;
    public boolean check ;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    public SenderPlainte(){

    }
    ProgressDialog pdLoading ;
    public SenderPlainte(Context c, String address,String idT, EditText... editTexts) {
        this.c = c;
       this.urlAddress = address;
        pdLoading = new ProgressDialog(c) ;
        this.sujetTxt=editTexts[0];
        this.ContenuTxt=editTexts[1];
        this.emailTxt=editTexts[2];
        this.nameTxt=editTexts[3];

        name=nameTxt.getText().toString();
        contenu=ContenuTxt.getText().toString();
        email=emailTxt.getText().toString();
        sujet=sujetTxt.getText().toString();
        id=idT;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       //this.result = new Result() ;
        pdLoading.setMessage("\tLoading...");
        pdLoading.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.sendPlainte();
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
             ContenuTxt.setText("");
             sujetTxt.setText("");
             emailTxt.setText("");
            id="";
            this.setCheck(true);

           // Toast.makeText(c,"From Sender "+isCheck(),Toast.LENGTH_LONG).show();

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
        Toast.makeText(c,"Email Or Password doesn't exists",Toast.LENGTH_LONG).show();

        Intent myintent = new Intent(c,c.getClass()) ;

        c.startActivity(myintent);
    }

    public void JSON2OBJECTS(String response) {
        try {

        JSONObject jsonObj = new JSONObject(response);

            JSONObject resp = jsonObj.getJSONObject("result");
            resp.getString("result");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String sendPlainte()
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
            bw.write(new DataPackagerPlainte(id,name,sujet,contenu,email).packData());
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
                res= response.toString();
                return response.toString();
            }else if(responseCode==con.HTTP_BAD_REQUEST)
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  String getRes(){return  res;}

}