package fr.eni.encheres.exception;

public class BusinessCode {

    //Clefs permettant de valider les BO

    //Articles
    public static final String VALIDATION_ARTICLE_NULL = "validation.article.null";
    public static final String VALIDATION_NOM_ARTICLE_BLANK = "validation.nom.article.blank";
    public static final String VALIDATION_NOM_ARTICLE_LENGTH = "validation.nom.article.length";
    public static final String VALIDATION_DATE_DEBUT_ENCHERE_NULL = "validation.date_debut.article.null";
    public static final String VALIDATION_DATE_DEBUT_ENCHERE_PAST_TO_NOW = "validation.date_debut.article.past.to.now";
    public static final String VALIDATION_DATE_FIN_ENCHERE_BEFORE_TO_DATE_DEBUT = "validation.date.fin.article.before.to.date.debut";
    public static final String VALIDATION_DATE_FIN_ENCHERE_NULL = "validation.date_fin.article.null";
    public static final String VALIDATION_DATE_MISE_A_PRIX_RANGE = "validation.mise_a_prix.article.range";
    public static final String VALIDATION_ADRESSE_NULL = "validation.adresse.null";
    public static final String VALIDATION_CATEGORIE_NULL = "validation.categorie.null";

    public static final String BLL_ARTICLE_CREER_ERREUR="bll.article.creer.erreur";
    public static final String BLL_ADRESSE_CREER_ERREUR="bll.adresse.creer.erreur";


}
