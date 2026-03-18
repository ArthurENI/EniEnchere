package fr.eni.encheres.services;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOArticle;
import fr.eni.encheres.dao.IDAOCategorie;
import fr.eni.encheres.dao.IDAOUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl  implements ArticleService{
    private final IDAOArticle idaoArticle;
    private final IDAOCategorie idaoCategorie;
    private final IDAOUtilisateur idaoUtilisateur;


    @Override
    public List<Article> selectAllArticles() {
        List<Article> articles =  idaoArticle.selectAllArticles();

        if (articles != null) {
            articles.forEach(this::chargerCategorieEtUtilisateur);
        }
        return articles;
    }

    @Override
    public Article selectArticleById(Long id) {
        Article article = idaoArticle.selectArticleById(id);
        if(article != null){
            chargerCategorieEtUtilisateur(article);
        }
    }

    @Override
    public List<Article> selectArticleByNom(String nom) {
        return idaoArticle.filterArticleParNom(nom);
    }

    @Override
    public List<Article> selectArticleByUtilisateur(Utilisateur utilisateur) {
        return idaoArticle.selectArticleByUtilisateur(utilisateur);
    }

    @Override
    public List<Article> selectArticleByEtat(EtatVente etatVente) {
        return idaoArticle.selectArticleByEtat(etatVente);
    }

    @Override
    public List<Article> filterArticles(String nom, Long categorieId) {
        return idaoArticle.filterArticles(nom, categorieId);
    }

    private void chargerCategorieEtUtilisateur(Article a){
        Categorie c = idaoCategorie.read(a.getCategorie().getNoCategorie());
        a.setCategorie(c);
        Utilisateur u = idaoUtilisateur.selectUtilisateurById(a.getUtilisateur().getNoUtilisateur());
        a.setUtilisateur(u);
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
