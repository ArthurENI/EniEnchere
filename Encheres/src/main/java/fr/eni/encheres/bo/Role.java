package fr.eni.encheres.bo;

import java.io.Serializable;

public class Role{

    public Long no_role;
    public String libelle;

    public Role() {
    }

    public Role(Long utilisateur, String libelle) {
        this.no_role = utilisateur;
        this.libelle = libelle;
    }


    public Long getNo_role() {
        return no_role;
    }

    public void setNo_role(Long no_role) {
        this.no_role = no_role;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
