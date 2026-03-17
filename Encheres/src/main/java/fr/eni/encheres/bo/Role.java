package fr.eni.encheres.bo;

import java.io.Serializable;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    public Utilisateur utilisateur;
    public String libelle;

    public Role() {
    }

    public Role(Utilisateur utilisateur, String libelle) {
        this.utilisateur = utilisateur;
        this.libelle = libelle;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
