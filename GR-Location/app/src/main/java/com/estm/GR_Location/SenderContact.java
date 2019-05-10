package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;


public class SenderContact extends AsyncTask<Void,Void,String> implements Serializable {
    public static  Context c;
    String urlAddress;
    EditText subjecttxt,contenttxt;
    String id,subject,content,type;


    public SenderContact(){

    }
    ProgressDialog pdLoading ;
    public SenderContact(Context c, String address,String id, String type ,  EditText... editTexts) {
        this.c = c;
       this.urlAddress = address;
        pdLoading = new ProgressDialog(c) ;

        this.subjecttxt=editTexts[1];
        this.contenttxt=editTexts[1];

        this.id=id;
        subject=subjecttxt.getText().toString();
        content=contenttxt.getText().toString();
        this.type=type;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pdLoading.setMessage("\tSending...");
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


            subjecttxt.setText("");
            contenttxt.setText("");
            Toast.makeText(c,"The message has been sent successfully",Toast.LENGTH_LONG).show();

        }else
        {

            Toast.makeText(c,"Your message has not been sent successfully, Please try again",Toast.LENGTH_LONG).show();

          //  logout(false);

        }

        pdLoading.dismiss();

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
            bw.write(new DataPackagerContact(id,subject,content,type).packData());
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
            }else
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}