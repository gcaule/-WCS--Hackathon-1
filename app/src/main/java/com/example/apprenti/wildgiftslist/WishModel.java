package com.example.apprenti.wildgiftslist;

/**
 * Created by apprenti on 30/10/17.
 */

public class WishModel {

    private String idWish;
    private String name;
    private String description;
    private String image;
    private String link;
    private int cadeau;

    public WishModel(String idWish,String name, String description, String image, String link, int cadeau) {
        this.idWish = idWish;
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
        this.cadeau = cadeau;
    }

    public WishModel() {
    }

    public String getIdWish() {
        return idWish;
    }

    public void setIdWish() {
        this.idWish = idWish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCadeau() {
        return cadeau;
    }

    public void setCadeau(int cadeau) {
        this.cadeau = cadeau;
    }

}
