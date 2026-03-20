package fr.eni.encheres.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Categorie {

    private Long noCategorie;
    private String libelle;
    @JsonIgnore
    private List<Article> articles;

    public Categorie() {
    }

    public Categorie(Long noCategorie, String libelle, List<Article> articles) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
        this.articles = articles;
    }

    public Long getNoCategorie() {
        return noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setNoCategorie(Long noCategorie) {
        this.noCategorie = noCategorie;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
