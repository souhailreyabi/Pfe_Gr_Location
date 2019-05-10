package com.estm.GR_Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by AbdellatifMANNUCHE.
 * 1.BASICALLY PACKS DATA WE WANNA SEND
 */
public class DataPackagerContact {
    String id,subject,content,type;
    /*
    SECTION 1.RECEIVE ALL DATA WE WANNA SEND
     */
    public DataPackagerContact(String id,String subject ,String content, String type) {
        this.id = id;
        this.subject = subject;
       this.content = content;
        this.type = type ;
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
            jo.put("subject",subject);
            jo.put("content",content);
            jo.put("type",type);

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