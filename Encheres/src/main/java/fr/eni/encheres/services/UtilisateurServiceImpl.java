package fr.eni.encheres.services;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOUtilisateur;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    private final IDAOUtilisateur daoUtilisateur;

    public UtilisateurServiceImpl(IDAOUtilisateur daoUtilisateur) {
        this.daoUtilisateur = daoUtilisateur;
    }

    @Override
    public ServiceResponse<Utilisateur> login(String pseudo, String password) {
        // essayer de retrouver user
        Utilisateur loggedUtilisateur = daoUtilisateur.selectUtilisateurByPseudoAndPassword(pseudo,password);

        // Si je trouve pas => erreur
        if (loggedUtilisateur == null)
        {
            return new ServiceResponse<Utilisateur>("7025", "pseudo et/ou mot de passe incorrect");
        }

        // Success
        return new ServiceResponse<Utilisateur>("2002", "Authentification réussie", loggedUtilisateur);
    }

    @Override
    public ServiceResponse<Utilisateur> inscriptionUtilisateur(Utilisateur utilisateur) {
        utilisateur.setCredit(0);
        daoUtilisateur.createCompte(utilisateur);
        return new ServiceResponse<Utilisateur>("2002", "Utilisateur créer avec succès", utilisateur);
    }
}
