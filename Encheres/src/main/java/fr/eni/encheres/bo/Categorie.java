package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long noCategorie;

    @NotNull(message = "Vous devez choisir une catégorie")
    private String libelle;
    private List<Article> articleList;

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
}
