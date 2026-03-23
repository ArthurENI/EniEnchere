package fr.eni.encheres.services;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.dao.IDAOAdresse;
import fr.eni.encheres.exception.BusinessCode;
import fr.eni.encheres.exception.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class AdresseServiceImpl implements AdresseService {

    private final IDAOAdresse idaoAdresse;

    public AdresseServiceImpl(IDAOAdresse idaoAdresse) {
        this.idaoAdresse = idaoAdresse;

    }

    @Override
    public Adresse selectAdresseById(Long id) {
        return null;
    }

    @Override
    public void create(Adresse adresse) {
        BusinessException be = new BusinessException();
        boolean isValid = validerAdresse(adresse, be);
        if(isValid){
            try{
                idaoAdresse.create(adresse);
            }catch (DataAccessException e) {
                be.add(BusinessCode.BLL_ADRESSE_CREER_ERREUR);
            }
        }
    }

    @Override
    public Adresse edit(Adresse adresse) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private boolean validerRue(String rue,BusinessException be){
        if(rue == null || rue.trim().isEmpty()){
            be.add(BusinessCode.BLL_ADRESSE_VALIDER_RUE);
            return false;
        }
        return true;
    }

    private boolean validerCodePostal(String codePostal, BusinessException be){
        if(codePostal == null || codePostal.trim().isEmpty()){
            be.add(BusinessCode.BLL_ADRESSE_VALIDER_CODE_POSTAL);
            return false;
        }
        return true;
    }

    private boolean validerVille(String ville,BusinessException be){
        if(ville == null || ville.trim().isEmpty()){
            be.add(BusinessCode.BLL_ADRESSE_VALIDER_VILLE);
            return false;
        }
        return true;
    }

    private boolean validerAdresse(Adresse adresse, BusinessException be){
        String rue, codePostal, ville;
        rue = adresse.getRue().toLowerCase();
        codePostal = adresse.getCodePostal().toLowerCase();
        ville = adresse.getVille().toLowerCase();
        boolean isValid = true;
        if(!validerRue(rue, be)){
            isValid = false;
        }
        if(!validerCodePostal(codePostal, be)){
            isValid = false;
        }
        if(!validerVille(ville, be)){
            isValid = false;
        }
        return isValid;
    }
}
