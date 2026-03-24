package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;

import java.util.List;

public interface IDAOArticle {

    //Selectionner un article revient à sélectionner une vente

    List<Article> selectAllArticles();
    Article selectArticleById(Long id);
    List<Article> selectArticleParNom(String nom);
    List<Article> selectArticleByUtilisateur(Long id);
    List<Article> selectArticleByEtat(EtatVente etatVente);
    List<Article> filterArticles(String nom, Long categorieId);

    //Créer un article revient à créer une vente
    void createArticle(Article article);
    Article deleteArticle();
    Article updateArticle();





}
