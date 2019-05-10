package com.estm.GR_Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;


/**
 * Created by AbdellatifMANNOUCHE on 25/03/16.
 */
public class DataPackagerAvis {
    String id,etoile;
    /*
    SECTION 1.RECEIVE ALL DATA WE WANNA SEND
     */
    public DataPackagerAvis(String id, String etoile) {
        this.id = id;
        this.etoile = etoile;
       // this.team = team;
    }
    /*
   SECTION 2
   1.PACK THEM INTO A JSON OBJECT
   2. READ ALL THIS DATA AND ENCODE IT INTO A FROMAT THAT CAN BE SENT VIA NETWORK
    */
    public String packData()
    {
        JSONObject jo=new JSONObject();
        StringBuffer packedData=new StringBuffer();
        try
        {
            jo.put("ID",id);
            jo.put("Etoile",etoile);
            //jo.put("Team",team);
            Boolean firstValue=true;
            Iterator it=jo.keys();
            do {
                String key=it.next().toString();
                String value=jo.get(key).toString();
                if(firstValue)
                {
                    firstValue=false;
                }else
                {
                    packedData.append("&");
                }
                packedData.append(URLEncoder.encode(key,"UTF-8"));
                packedData.append("=");
                packedData.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return packedData.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}