package fr.eni.encheres.services;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.controller.locale.LocaleHelper;
import fr.eni.encheres.dao.IDAOUtilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurServiceImpl implements UtilisateurService{
    private final IDAOUtilisateur daoUtilisateur;
    private final LocaleHelper lh;

    public UtilisateurServiceImpl(IDAOUtilisateur daoUtilisateur, LocaleHelper lh) {
        this.daoUtilisateur = daoUtilisateur;
        this.lh = lh;
    }

    @Override
    public ServiceResponse<Utilisateur> login(String pseudo, String password) {
        // essayer de retrouver user
        Utilisateur loggedUtilisateur = daoUtilisateur.selectUtilisateurByPseudoAndPassword(pseudo,password);

        // Si je trouve pas => erreur
        if (loggedUtilisateur == null)
        {
            return new ServiceResponse<Utilisateur>("7025", lh.i18n("Msg_Auth_Error"));
        }

        // Success
        return new ServiceResponse<Utilisateur>("2002", lh.i18n("Msg_Auth_Success"), loggedUtilisateur);
    }
}
