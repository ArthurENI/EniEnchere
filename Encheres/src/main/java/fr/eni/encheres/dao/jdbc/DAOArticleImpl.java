package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOArticle;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary == Prioriser la class à utiliser pour la DAL
@Primary
public class DAOArticleImpl implements IDAOArticle {
    @Override
    public List<Article> selectAllArticles() {
        return List.of();
    }

    @Override
    public Article selectArticleById(Long id) {
        return null;
    }

    @Override
    public List<Article> filterArticleParNom(String nom) {
        return List.of();
    }

    @Override
    public List<Article> selectArticleByUtilisateur(Utilisateur utilisateur) {
        return List.of();
    }

    @Override
    public List<Article> selectArticleByEtat(EtatVente etatVente) {
        return List.of();
    }

    @Override
    public List<Article> filterArticles(String nom, Long categorieId) {
        return List.of();
    }

    @Override
    public Article createArticle() {
        return null;
    }

    @Override
    public Article deleteArticle() {
        return null;
    }

    @Override
    public Article updateArticle() {
        return null;
    }

}
