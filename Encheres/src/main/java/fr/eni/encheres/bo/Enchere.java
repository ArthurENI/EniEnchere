package fr.eni.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Enchere implements Serializable {

        private LocalDateTime dateEnchere;
        private int montant_enchere;
        Utilisateur utilisateur;
        Article article;

    public Enchere() {
    }

    public Enchere(LocalDateTime dateEnchere, Article article, Utilisateur utilisateur, int montant_enchere) {
        this.dateEnchere = dateEnchere;
        this.article = article;
        this.utilisateur = utilisateur;
        this.montant_enchere = montant_enchere;
    }

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }

    public Article getArticle() {
        return article;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }

    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
