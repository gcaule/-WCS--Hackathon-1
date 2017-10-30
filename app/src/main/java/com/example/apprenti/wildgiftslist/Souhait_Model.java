package com.example.apprenti.wildgiftslist;

/**
 * Created by apprenti on 30/10/17.
 */

public class Souhait_Model {
    private String name;
    private String desc;
    private String urlImg;
    private String url;

    private Souhait_Model(){

    }

    public Souhait_Model(String name, String desc, String urlImg,String url) {
        this.name = name;
        this.desc = desc;
        this.urlImg = urlImg;
        this.url = url;
    }

    public String getDesc() {
        return name;
    }

    public void setDesc(String desc) {
        this.name = desc;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
