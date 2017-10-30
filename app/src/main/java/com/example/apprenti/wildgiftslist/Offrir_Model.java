package com.example.apprenti.wildgiftslist;

/**
 * Created by apprenti on 30/10/17.
 */

public class Offrir_Model {
    //private String idOffrir;
    private String nameOff;
    private String descriptionOff;
    private String imageOff;
    private String destinataire;

    public Offrir_Model(){

    }

    public Offrir_Model(String nameOff, String descriptionOff, String imageOff, String destinataire) {
        //this.idOffrir = idOffrir;
        this.nameOff = nameOff;
        this.descriptionOff = descriptionOff;
        this.imageOff = imageOff;
        this.destinataire = destinataire;
    }

    /*public String getIdOffrir() {
        return idOffrir;
    }*/

   /* public void setIdOffrir(String idOffrir) {
        this.idOffrir = idOffrir;
    }*/

    public String getNameOff() {
        return nameOff;
    }

    public void setNameOff(String nameOff) {
        this.nameOff = nameOff;
    }

    public String getDescriptionOff() {
        return descriptionOff;
    }

    public void setDescriptionOff(String descriptionOff) {
        this.descriptionOff = descriptionOff;
    }

    public String getImageOff() {
        return imageOff;
    }

    public void setImageOff(String imageOff) {
        this.imageOff = imageOff;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }
}
