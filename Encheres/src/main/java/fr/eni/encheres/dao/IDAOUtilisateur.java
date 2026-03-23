package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IDAOUtilisateur {

    public List<Utilisateur> selectAllUtilisateurs() ;

    public Utilisateur selectUtilisateurById(Long id) ;

    public Utilisateur selectUtilisateurByEmail(String email) ;

    public Utilisateur selectUtilisateurByPseudoAndPassword(String pseudo, String password) ;

    public void updateUtilisateur (Utilisateur utilisateur) throws SQLException;

    public void updateMdp (Utilisateur utilisateur) throws SQLException;

    public void createCompte(Utilisateur utilisateur) throws SQLException;

    public void deleteCompte(Utilisateur utilisateur) ;

}
