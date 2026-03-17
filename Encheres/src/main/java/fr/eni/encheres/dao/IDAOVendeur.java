package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Article;

public interface IDAOVendeur {

    public void createArticle(Article categorie) ;

    public void deleteArticle(Article categorie) ;

    public void updateArticle(Article categorie) ;

    public void addPhoto(Article categorie) ;
}
