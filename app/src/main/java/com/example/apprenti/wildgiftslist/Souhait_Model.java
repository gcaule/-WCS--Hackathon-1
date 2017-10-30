package com.example.apprenti.wildgiftslist;

/**
 * Created by apprenti on 30/10/17.
 */

public class Souhait_Model {
    private String desc;
    private String Url;

    private Souhait_Model(){

    }

    public Souhait_Model(String desc, String url) {
        this.desc = desc;
        Url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
