package fr.eni.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Enchere implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dateEnchere;
    private int montantEnchere;
    private Utilisateur utilisateur;
    private List<Article> articleList;

    public Enchere(int montantEnchere, Utilisateur utilisateur, List<Article> articleList) {
        this.dateEnchere = LocalDateTime.now();
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.articleList = articleList;
    }

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchere = (Enchere) o;
        return getMontantEnchere() == enchere.getMontantEnchere() && Objects.equals(getDateEnchere(), enchere.getDateEnchere()) && Objects.equals(getUtilisateur(), enchere.getUtilisateur()) && Objects.equals(getArticleList(), enchere.getArticleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateEnchere(), getMontantEnchere(), getUtilisateur(), getArticleList());
    }
}
