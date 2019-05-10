package com.estm.GR_Location.commun;


/**
 * Created by AbdellatifMANNOUCHE on 4/11/16.
 */

public class MessageEvent {
    private String longVal;
    private String latVal;

    public MessageEvent(String longVal, String latVal) {
        this.longVal = longVal;
        this.latVal = latVal;
    }

    public String getLongVal() {

        return longVal;
    }

    public void setLongVal(String longVal) {
        this.longVal = longVal;
    }

    public String getLatVal() {
        return latVal;
    }

    public void setLatVal(String latVal) {
        this.latVal = latVal;
    }
}
