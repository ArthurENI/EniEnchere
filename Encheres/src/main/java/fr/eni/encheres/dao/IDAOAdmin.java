package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Utilisateur;

public interface IDAOAdmin {

    public void desactiverCompteUtilisateur(Utilisateur utilisateur) ;

    public void reactiverCompteUtilisateur(Utilisateur utilisateur) ;

    public void deleteCompteUtilisateur(Utilisateur utilisateur);

    public void createCategorie(Categorie categorie) ;

    public void updateCategorie(Categorie categorie) ;

    public void deleteCategorie(Categorie categorie) ;

}
