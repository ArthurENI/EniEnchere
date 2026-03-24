/*
package fr.eni.encheres.dao.jdbc;


import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.IDAOCategorie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DAOCategorieImpl implements IDAOCategorie {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOCategorieImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Categorie selectCategorieById(Long id) {
        return null;
    }

    @Override
    public List<Categorie> findAll() {
        return List.of();
    }

    @Override
    public Categorie create(Categorie categorie) {
        return null;
    }

    @Override
    public Categorie edit(Categorie categorie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
*/
