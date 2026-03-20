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
        try{
            idaoAdresse.create(adresse);
        }catch (DataAccessException e) {
            be.add(BusinessCode.BLL_ADRESSE_CREER_ERREUR);
        }
    }

    @Override
    public Adresse edit(Adresse adresse) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
