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
        Categorie c1 = new Categorie(1l,"Catégorie1", null);
        // Créer des adresses de retrait simples
        Adresse adresseRetrait1 = new Adresse("Rue de la Vente 1", "75001", "Paris");
        Adresse adresseRetrait2 = new Adresse("Rue de la Vente 2", "69000", "Lyon");

// Créer le premier article
        Article article1 = new Article();
        article1.setNoArticle(1L);
        article1.setNomArticle("Ordinateur portable");
        article1.setDescription("Un ordinateur portable en bon état.");
        article1.setDateDebutEnchere(date1);
        article1.setDateFinEnchere(date2);
        article1.setMiseAPrix(100);
        article1.setPrixVenteEnCours(100);
        article1.setUtilisateur(vendeur1);
        article1.setAdresseRetrait(adresseRetrait1);
        article1.setCategorie(c1);
        article1.setEtatVente(EtatVente.OUVERTE);
        article1.setEnchereList(new ArrayList<>());

// Créer le deuxième article
        Article article2 = new Article();
        article2.setNoArticle(2L);
        article2.setNomArticle("Vélo de course");
        article2.setDescription("Un vélo de course neuf.");
        article2.setDateDebutEnchere(date1);
        article2.setDateFinEnchere(date2);
        article2.setMiseAPrix(200);
        article2.setPrixVenteEnCours(200);
        article2.setUtilisateur(vendeur2);
        article2.setAdresseRetrait(adresseRetrait2);
        article2.setCategorie(c1);
        article2.setEtatVente(EtatVente.OUVERTE);
        article2.setEnchereList(new ArrayList<>());

// Ajouter les articles à la liste
        articleDb.add(article1);
        articleDb.add(article2);
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
