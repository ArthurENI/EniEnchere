package fr.eni.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Enchere implements Serializable {

        private Long noEnchere;
        private LocalDateTime dateEnchere;
        private int montant_enchere;
        Utilisateur utilisateur;
        Article article;

    public Enchere() {
    }

    public Enchere(Long noEnchere, LocalDateTime dateEnchere, int montant_enchere, Utilisateur utilisateur, Article article) {
        this.noEnchere = noEnchere;
        this.dateEnchere = dateEnchere;
        this.montant_enchere = montant_enchere;
        this.utilisateur = utilisateur;
        this.article = article;
    }


    public Long getNoEnchere() {
        return noEnchere;
    }

    public void setNoEnchere(Long noEnchere) {
        this.noEnchere = noEnchere;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchere = (Enchere) o;
        return getMontant_enchere() == enchere.getMontant_enchere() && Objects.equals(getNoEnchere(), enchere.getNoEnchere()) && Objects.equals(getDateEnchere(), enchere.getDateEnchere()) && Objects.equals(getUtilisateur(), enchere.getUtilisateur()) && Objects.equals(getArticle(), enchere.getArticle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoEnchere(), getDateEnchere(), getMontant_enchere(), getUtilisateur(), getArticle());
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "dateEnchere=" + dateEnchere +
                ", montant_enchere=" + montant_enchere +
                ", utilisateur=" + utilisateur +
                ", article=" + article +
                '}';
    }
}
