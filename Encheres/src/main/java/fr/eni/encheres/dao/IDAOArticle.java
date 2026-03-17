package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;

import java.util.List;

public interface IDAOArticle {

    //Selectionner un article revient à sélectionner une vente

    List<Article> selectAllArticles();
    Article selectArticleById(Long id);
    Article selectArticleByNom(String nom);
    Article selectArticleByUtilisateur(Utilisateur utilisateur);
    Article selectArticleByEtat(EtatVente etatVente);


    //Créer un article revient à créer une vente
    Article createArticle();
    Article deleteArticle();
    Article updateArticle();





}
