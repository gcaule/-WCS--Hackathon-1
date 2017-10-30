package com.example.apprenti.wildgiftslist;

/**
 * Created by apprenti on 30/10/17.
 */

public class List_Souhait_Model {
    private String urlImg;
    private String nameItem;

    public List_Souhait_Model(){

    }

    public List_Souhait_Model(String urlImg, String nameItem) {
        this.urlImg = urlImg;
        this.nameItem = nameItem;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }
}
