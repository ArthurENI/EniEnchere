package fr.eni.encheres.dao;

public interface IDAOVendeur {

    public void createArticle(Article categorie) ;

    public void deleteArticle(Article categorie) ;

    public void updateArticle(Article categorie) ;

    public void addPhoto(Article categorie) ;
}
