package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private int miseAPrix;

    private int prixVenteEnCours;
    private Utilisateur acquereur;
    private Utilisateur vendeur;
    private List<Enchere> enchereList;

    @NotNull
    private Adresse adresseRetrait;

    @NotNull
    private Categorie categorie;

    private EtatVente etatVente;

    public Article() {
        this.enchereList = new ArrayList<>();
    }

    public Article(Long noArticle, String nomArticle, String nomImage, String description, LocalDateTime dateDebutEnchere,
                   LocalDateTime dateFinEnchere, int miseAPrix, int prixVenteEnCours, Utilisateur acquereur,
                   Utilisateur vendeur, List<Enchere> enchereList,Adresse adresse, Categorie categorie, EtatVente etatVente) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.nomImage = nomImage;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVenteEnCours = prixVenteEnCours;
        this.acquereur = acquereur;
        this.vendeur = vendeur;
        this.enchereList = enchereList;
        this.adresseRetrait = adresse;
        this.categorie = categorie;
        this.etatVente = etatVente;
    }

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

    public int getPrixVenteEnCours() {
        return prixVenteEnCours;
    }

    public void setPrixVenteEnCours(int prixVenteEnCours) {
        this.prixVenteEnCours = prixVenteEnCours;
    }

    public Utilisateur getAcquereur() {
        return acquereur;
    }

    public void setAcquereur(Utilisateur acquereur) {
        this.acquereur = acquereur;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return getMiseAPrix() == article.getMiseAPrix() && getPrixVenteEnCours() == article.getPrixVenteEnCours() && Objects.equals(getNoArticle(), article.getNoArticle()) && Objects.equals(getNomArticle(), article.getNomArticle()) && Objects.equals(getNomImage(), article.getNomImage()) && Objects.equals(getDescription(), article.getDescription()) && Objects.equals(getDateDebutEnchere(), article.getDateDebutEnchere()) && Objects.equals(getDateFinEnchere(), article.getDateFinEnchere()) && Objects.equals(getAcquereur(), article.getAcquereur()) && Objects.equals(getVendeur(), article.getVendeur()) && Objects.equals(getEnchereList(), article.getEnchereList()) && Objects.equals(getAdresseRetrait(), article.getAdresseRetrait()) && Objects.equals(getCategorie(), article.getCategorie()) && getEtatVente() == article.getEtatVente();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoArticle(), getNomArticle(), getNomImage(), getDescription(), getDateDebutEnchere(), getDateFinEnchere(), getMiseAPrix(), getPrixVenteEnCours(), getAcquereur(), getVendeur(), getEnchereList(), getAdresseRetrait(), getCategorie(), getEtatVente());
    }
}
