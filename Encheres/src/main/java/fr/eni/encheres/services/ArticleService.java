package fr.eni.encheres.services;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ArticleService {

    List<Article> selectAllArticles();
    Article selectArticleById(Long id);
    List<Article> selectArticleByNom(String nom);
    List<Article> selectArticleByUtilisateur(Long id);
    List<Article> selectArticleByEtat(EtatVente etatVente);
    List<Article> filterArticles(String nom, Long categorieId);


    //Créer un article revient à créer une vente
    void createArticle(Article article);
    Article deleteArticle();
    Article updateArticle();

    void validerStatutArticle(Article article);

}
