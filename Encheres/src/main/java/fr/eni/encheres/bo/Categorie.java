package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long noCategorie;
    private String libelle;
    private List<Article> articleList;

    public Categorie() {
        this.articleList = new ArrayList<>();
    }

    public Categorie(Long noCategorie, String libelle, List<Article> articleList) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
        this.articleList = articleList;
    }

    public Long getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(Long noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        Categorie categorie = (Categorie) o;
        return Objects.equals(getNoCategorie(), categorie.getNoCategorie()) && Objects.equals(getLibelle(), categorie.getLibelle()) && Objects.equals(getArticleList(), categorie.getArticleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoCategorie(), getLibelle(), getArticleList());
    }
}
