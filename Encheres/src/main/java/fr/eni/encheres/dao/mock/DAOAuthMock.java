package fr.eni.encheres.dao.mock;

import fr.eni.encheres.bo.Adresse;
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
        Adresse a1 = new Adresse("rue 2","97160","Le Moule");
        DBUsers = new ArrayList<>();
        Utilisateur u1 = new Utilisateur("user1","Jean","VIER","a@a.fr",null,"aze",0,false,a1);
        u1.setNoUtilisateur(1L);
        Utilisateur u2 = new Utilisateur("user2","François","DUPONT","a@a.fr",
                null,"mdp",0,false, a1);
        u2.setNoUtilisateur(2L);
        DBUsers.add(u1);
        DBUsers.add(u2);
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
    public void updateUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public void createCompte(Utilisateur utilisateur) {
        Utilisateur u1 = new Utilisateur("test","François","DUPONT","a@a.fr",null,"mdp",0,false,null);
        DBUsers.add(u1);
    }

    @Override
    public void deleteCompte(Utilisateur utilisateur) {
        DBUsers.remove(utilisateur);
    }
}
