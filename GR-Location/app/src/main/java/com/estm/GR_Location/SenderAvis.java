package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;


public class SenderAvis extends AsyncTask<Void,Void,String> implements Serializable {
    public static  Context c;
    public static  Context c2;
    String urlAddress;
    EditText nameTxt;
    String name,et;
    static String res=null;




    public static boolean test = false ;
    public boolean check ;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    public SenderAvis(){

    }
    ProgressDialog pdLoading ;
    public SenderAvis(Context c, String address,String et, String ids) {
        this.c = c;
       this.urlAddress = address;
        pdLoading = new ProgressDialog(c) ;
          name=ids;
        this.et=et;

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
        return this.sendAvis();
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
            name="";
            et="";
            this.setCheck(true);

           // Toast.makeText(c,"From Sender "+isCheck(),Toast.LENGTH_LONG).show();

           //login();

        }else
        {

        //    Toast.makeText(c,"Unsuccessful ",Toast.LENGTH_LONG).show();



        }

        pdLoading.dismiss();

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

    private String sendAvis()
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
            bw.write(new DataPackagerAvis(name,et).packData());
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
                res=response.toString();
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