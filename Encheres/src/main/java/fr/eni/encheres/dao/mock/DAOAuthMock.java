package fr.eni.encheres.dao.mock;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOUtilisateur;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DAOAuthMock implements IDAOUtilisateur {

    public List<Utilisateur> DBUsers;

    public DAOAuthMock() {

        DBUsers = new ArrayList<>();
        DBUsers.add(new Utilisateur("user1","Jean","VIER","a@a.fr",null,"aze",0,false,null));
        DBUsers.add(new Utilisateur("user2","François","DUPONT","a@a.fr",null,"mdp",0,false,null));
    }


    @Override
    public List<Utilisateur> selectAllUtilisateurs() {
        return DBUsers;
    }

    @Override
    public Utilisateur selectUtilisateurById(Long id) {
        Utilisateur utilisateur = DBUsers.stream()
                .filter((user) ->  user.getNoUtilisateur().equals(id) )
                .findFirst()
                .orElse(null);
        return utilisateur;
    }

    @Override
    public Utilisateur selectUtilisateurByPseudoAndPassword(String pseudo, String password) {
        Utilisateur utilisateur = DBUsers.stream()
                .filter((user) ->  user.getPseudo().equals(pseudo) && user.getMotDePasse().equals(password) )
                .findFirst()
                .orElse(null);
        return utilisateur;
    }

    @Override
    public void saveSessionUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public void createCompte(Utilisateur utilisateur) {
        Utilisateur u1 = new Utilisateur("test","François","DUPONT","a@a.fr",null,"mdp",0,false,null);
        DBUsers.add(u1);
    }

    @Override
    public void deleteCompte(Utilisateur utilisateur) {
    }
}
