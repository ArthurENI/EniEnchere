package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Article {

    private Long noArticle;

    @NotBlank
    @Length(min = 2, max = 250, message = "Le Nom doit avoir au moins 2 caractères")
    private String nomArticle;

    private String nomImage;

    private String description;

    @NotNull(message = "L'enchère doit avoir une date de lancement")
    private LocalDateTime dateDebutEnchere;

    @NotNull(message = "L'enchère doit avoir une date de clôture")
    private LocalDateTime dateFinEnchere;

    @NotBlank
    @Range(min = 1, message = "La valeur minimale doit être de 1 point")
    @Positive
    private int miseAPrix;;
    private Utilisateur utilisateur;
    private List<Enchere> enchereList;

    @NotNull
    private Adresse adresseRetrait;
    @NotNull
    private Categorie categorie;

    private EtatVente etatVente;




    public Long getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(Long noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public LocalDateTime getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Enchere> getEnchereList() {
        return enchereList;
    }

    public void setEnchereList(List<Enchere> enchereList) {
        this.enchereList = enchereList;
    }

    public Adresse getAdresseRetrait() {
        return adresseRetrait;
    }

    public void setAdresseRetrait(Adresse adresseRetrait) {
        this.adresseRetrait = adresseRetrait;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public EtatVente getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(EtatVente etatVente) {
        this.etatVente = etatVente;
    }
}
