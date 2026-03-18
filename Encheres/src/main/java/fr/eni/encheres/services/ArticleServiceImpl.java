package fr.eni.encheres.services;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOArticle;
import fr.eni.encheres.dao.IDAOCategorie;
import fr.eni.encheres.dao.IDAOEnchere;
import fr.eni.encheres.dao.IDAOUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class ArticleServiceImpl  implements ArticleService{
    private final IDAOArticle idaoArticle;
    private final IDAOCategorie idaoCategorie;
    private final IDAOUtilisateur idaoUtilisateur;
    private final IDAOEnchere idaoEnchere;


    public ArticleServiceImpl(IDAOArticle idaoArticle, IDAOCategorie idaoCategorie, IDAOUtilisateur idaoUtilisateur, IDAOEnchere idaoEnchere) {
        this.idaoArticle = idaoArticle;
        this.idaoCategorie = idaoCategorie;
        this.idaoUtilisateur = idaoUtilisateur;
        this.idaoEnchere = idaoEnchere;
    }

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
        Article a = idaoArticle.selectArticleById(id);
        if (a != null) {
            chargerCategorieEtUtilisateur(a);
            //Charger les encheres si il y en a
            List<Enchere> encheres = idaoEnchere.findByArticle(id);
            if(encheres !=null){
                encheres.forEach(this::chargerUtilisateurEnchere);
                a.setEnchereList(encheres);
            }
        }
        return a;
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

    private void chargerCategorieEtUtilisateur(Article a){
        Categorie c = idaoCategorie.read(a.getCategorie().getNoCategorie());
        a.setCategorie(c);
        Utilisateur u = idaoUtilisateur.selectUtilisateurById(a.getUtilisateur().getNoUtilisateur());
        a.setUtilisateur(u);
    }

    private void chargerUtilisateurEnchere(Enchere e){
        Utilisateur u = idaoUtilisateur.selectUtilisateurById(e.getUtilisateur().getNoUtilisateur());
        e.setUtilisateur(u);
    }
}
