package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Utilisateur;

import java.util.List;

public interface IDAOUtilisateur {

    public List<Utilisateur> selectAllUtilisateurs() ;

    public Utilisateur selectUtilisateurById(Long id) ;

    public Utilisateur selectUtilisateurByPseudoAndPassword(String pseudo, String password) ;

    public void updateUtilisateur (Utilisateur utilisateur);

    public void createCompte(Utilisateur utilisateur) ;

    public void deleteCompte(Utilisateur utilisateur) ;

}
