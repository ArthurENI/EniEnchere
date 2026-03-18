package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.IDAOCategorie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOCategorieImpl implements IDAOCategorie {

    @Override
    public Categorie read(Long id) {
        return null;
    }

    @Override
    public List<Categorie> findAll() {
        return List.of();
    }
}
