package fr.eni.encheres.services;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOArticle;
import fr.eni.encheres.dao.IDAOCategorie;
import fr.eni.encheres.dao.IDAOEnchere;
import fr.eni.encheres.dao.IDAOUtilisateur;
import fr.eni.encheres.exception.BusinessCode;
import fr.eni.encheres.exception.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        /*if (articles != null) {
            articles.forEach(this::chargerCategorieEtUtilisateur);
        }*/
        return articles;
    }


    @Override
    public Article selectArticleById(Long id) {
        Article a = idaoArticle.selectArticleById(id);
        if (a != null) {
            chargerCategorieEtUtilisateur(a);
            //Charger les encheres si il y en a
            //List<Enchere> encheres = idaoEnchere.findByArticle(id);
            /*if(encheres !=null){
                encheres.forEach(this::chargerUtilisateurEnchere);
                a.setEnchereList(encheres);
            }*/
        }
        return a;
    }

    @Override
    public List<Article> selectArticleByNom(String nom) {
        //TODO compléter la fonction
        return idaoArticle.selectArticleParNom(nom);
    }

    @Override
    public List<Article> selectArticleByUtilisateur(Long idUser) {
        List<Article> articles =  idaoArticle.selectArticleByUtilisateur(idUser);
        return articles;
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
    public void createArticle(Article article) {
        //Valider l'article avant la sauvegarde
        BusinessException be = new BusinessException();
        boolean isValid;
        isValid = validerArticle(article, be);

        if(isValid){
            try {
                idaoArticle.createArticle(article);
            } catch (DataAccessException e) {
                be.add(BusinessCode.BLL_ARTICLE_CREER_ERREUR);
            }
        }
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
        Categorie c = idaoCategorie.selectCategorieById(a.getCategorie().getNoCategorie());
        a.setCategorie(c);
        Utilisateur u = idaoUtilisateur.selectUtilisateurById(a.getUtilisateur().getNoUtilisateur());
        a.setUtilisateur(u);
    }

    private void chargerUtilisateurEnchere(Enchere e){
        Utilisateur u = idaoUtilisateur.selectUtilisateurById(e.getUtilisateur().getNoUtilisateur());
        e.setUtilisateur(u);
        System.out.println("UTILISATEUR ENCHERE : "+e.getUtilisateur().getNoUtilisateur());
    }

    private void chargerArticleParUtilisateur(Long idUser){
        List<Article> articles = idaoArticle.selectArticleByUtilisateur(idUser);
        System.out.println("ARTICLES PAR UTILISATEUR : "+articles.size());
    }


    private boolean validerNomArticle(String nom, BusinessException be){
        if(nom == null || nom.trim().isEmpty()){
            be.add(BusinessCode.VALIDATION_NOM_ARTICLE_BLANK);
            return false;
        }
        if(nom.length()<2 || nom.length()>250){
            be.add(BusinessCode.VALIDATION_NOM_ARTICLE_LENGTH);
            return false;
        }
        return true;
    }

    private boolean validerMiseAPrix(int miseAPrix, BusinessException be){
        if(miseAPrix<1){
            be.add(BusinessCode.VALIDATION_DATE_MISE_A_PRIX_RANGE);
            return false;
        }
        return true;
    }

    private boolean validerDateDebutEnchere(LocalDateTime dateDebutEnchere, BusinessException be){
        if(dateDebutEnchere == null){
            be.add(BusinessCode.VALIDATION_DATE_DEBUT_ENCHERE_NULL);
            return false;
        }
        if(dateDebutEnchere.isBefore(LocalDateTime.now())){
            be.add(BusinessCode.VALIDATION_DATE_DEBUT_ENCHERE_PAST_TO_NOW);
            return false;
        }
        return true;
    }


    private boolean validerDateFinEnchere(LocalDateTime dateFinEnchere, LocalDateTime dateDebutEnchere, BusinessException be) {
        if (dateFinEnchere == null) {
            be.add(BusinessCode.VALIDATION_DATE_FIN_ENCHERE_NULL);
            return false;
        }
        if (dateFinEnchere.isBefore(dateDebutEnchere)) {
            be.add(BusinessCode.VALIDATION_DATE_FIN_ENCHERE_BEFORE_TO_DATE_DEBUT);
            return false;
        }
        return true;
    }

    private boolean validerAdresse(Adresse adresse, BusinessException be){
        if(adresse == null){
            be.add(BusinessCode.VALIDATION_ADRESSE_NULL);
            return false;
        }
        return true;
    }

    private boolean validerCategorie(Categorie categorie, BusinessException be) {
        if (categorie == null) {
            be.add(BusinessCode.VALIDATION_CATEGORIE_NULL);
            return false;
        }
        return true;
    }

     private boolean validerArticle(Article article, BusinessException be){
        boolean isValid = true;
        if(article == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_NULL);
            return false;
        }
        if(!validerNomArticle(article.getNomArticle(), be)){
            isValid = false;
        }
        if(!validerMiseAPrix(article.getMiseAPrix(), be)){
            isValid = false;
        }
        if(!validerDateDebutEnchere(article.getDateDebutEnchere(), be)){
            isValid = false;
        }
        if(!validerDateFinEnchere(article.getDateFinEnchere(), article.getDateDebutEnchere(), be)){
            isValid = false;
        }
        if(!validerAdresse(article.getAdresseRetrait(), be)){
            isValid = false;
        }
        if(!validerCategorie(article.getCategorie(), be)){
            isValid = false;
        }
        return isValid;
     }
}

/*
    validerArticle(article, be)
    validerNomArticle(String nom, BusinessException be)
    validerMiseAPrix(int miseAPrix, BusinessException be)
    validerDateDebutEnchere(LocalDateTime dateDebutEnchere, BusinessException be)
    validerDateFinEnchere(LocalDateTime dateFinEnchere, LocalDateTime dateDebutEnchere, BusinessException be)
    validerAdresse(article.getNoAdresse(),be)
     */