package fr.eni.encheres.dao.mock;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOArticle;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DAOArticleMock implements IDAOArticle {
    public List<Article> articleDb;

    private DAOArticleMock(){
        articleDb = new ArrayList<>();
        LocalDateTime date1 = LocalDateTime.of(2026,02,18,0,59);
        LocalDateTime date2 = LocalDateTime.of(2026,02,21,0,59);
        Utilisateur acquereur1 = new Utilisateur("Jean", "0000");
        Utilisateur vendeur1 = new Utilisateur("Paul", "0000");
        Utilisateur acquereur2 = new Utilisateur("Christian", "0000");
        Utilisateur vendeur2 = new Utilisateur("Michel", "0000");
        Categorie c1 = new Categorie();
        // Créer des adresses de retrait simples
        Adresse adresseRetrait1 = new Adresse("Rue de la Vente 1", "75001", "Paris");
        Adresse adresseRetrait2 = new Adresse("Rue de la Vente 2", "69000", "Lyon");
        Enchere enchere = new Enchere();
        List<Enchere> enchereList = new ArrayList<>();
        enchereList.add(enchere);


        //Articles
        Article a1 = new Article(1L,"Ordinateur portable","Un ordinateur portable en bon état.",date1, date2, 100,100,
                vendeur1, adresseRetrait1, c1, EtatVente.ATTENTE, enchereList);


// Ajouter les articles à la liste
        articleDb.add(a1);
    }

    @Override
    public List<Article> selectAllArticles() {
        return articleDb;
    }

    @Override
    public Article selectArticleById(Long id) {
        Article article = articleDb.stream()
                .filter((art) ->  art.getNoArticle().equals(id) )
                .findFirst()
                .orElse(null);
        return article;
    }
    @Override
    public List<Article> filterArticles(String nom, Long categorieId) {
        /*return articleDb.stream()
                .filter(article -> (nom == null || nom.trim().isEmpty() || article.getNomArticle().toLowerCase().contains(nom.toLowerCase())))
                .filter(article -> (categorieId == null || (article.getCategorie() != null && article.getCategorie().getNoCategorie().equals(categorieId))))
                .toList();*/
        return null;
    }

    @Override
    public List<Article> filterArticleParNom(String nom) {
        List<Article> articles = articleDb.stream()
                .filter((art) ->  art.getNomArticle().toLowerCase().contains(nom.toLowerCase()) )
                .toList();
        return articles;
    }

    @Override
    public List<Article> selectArticleByUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public List<Article> selectArticleByEtat(EtatVente etatVente) {
        return null;
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
