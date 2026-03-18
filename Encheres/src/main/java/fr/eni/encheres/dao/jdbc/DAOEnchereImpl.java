package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dao.IDAOEnchere;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOEnchereImpl implements IDAOEnchere {
    @Override
    public Enchere selectById(Long id) {
        return null;
    }

    @Override
    public List<Enchere> findAll() {
        return List.of();
    }

    @Override
    public List<Enchere> findByArticle(Long idArticle) {
        return List.of();
    }
}
