package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long noUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motDePasse;
    private int credit;
    private boolean isActif;

    private Adresse adresse;
    private List<Enchere> enchereList;
    private List<Role> roleList;

    public Utilisateur() {
        this.enchereList = new ArrayList<>();
        this.roleList = new ArrayList<>();
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String motDePasse,
                       int credit, boolean isActif, Adresse adresse) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.isActif = isActif;
        this.adresse = adresse;
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String motDePasse,
                       int credit, boolean isActif, Adresse adresse, List<Enchere> enchereList, List<Role> roleList) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.credit = credit;

        this.isActif = isActif;
        this.adresse = adresse;
        this.enchereList = enchereList;
        this.roleList = roleList;
    }

    public Long getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(Long noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isActif() {
        return isActif;
    }

    public void setActif(boolean actif) {
        isActif = actif;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Enchere> getEnchereList() {
        return enchereList;
    }

    public void setEnchereList(List<Enchere> enchereList) {
        this.enchereList = enchereList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return getCredit() == that.getCredit() && isActif() == that.isActif() && Objects.equals(getNoUtilisateur(), that.getNoUtilisateur()) && Objects.equals(getPseudo(), that.getPseudo()) && Objects.equals(getNom(), that.getNom()) && Objects.equals(getPrenom(), that.getPrenom()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getTelephone(), that.getTelephone()) && Objects.equals(getMotDePasse(), that.getMotDePasse()) && Objects.equals(getAdresse(), that.getAdresse()) && Objects.equals(getEnchereList(), that.getEnchereList()) && Objects.equals(getRoleList(), that.getRoleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoUtilisateur(), getPseudo(), getNom(), getPrenom(), getEmail(), getTelephone(), getMotDePasse(), getCredit(), isActif(), getAdresse(), getEnchereList(), getRoleList());
    }
}
