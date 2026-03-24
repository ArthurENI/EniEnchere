
package fr.eni.encheres.dao.jdbc;


import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOCategorie;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Primary
@Repository
public class DAOCategorieImpl implements IDAOCategorie {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CategorieRowMapper categorieRowMapper;

    public DAOCategorieImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, CategorieRowMapper categorieRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.categorieRowMapper = categorieRowMapper;
    }


    @Override
    public Categorie selectCategorieById(Long id) {
        String sql = """
                SELECT no_categorie,libelle
                FROM categories
                WHERE no_categorie =?
                """;
        List<Categorie> categorieList = jdbcTemplate.query(sql, categorieRowMapper,id);
        if (!categorieList.isEmpty()) {
            // Je prend le premier resultat pour simuler un "get by id"
            return categorieList.get(0);
        } else return null;
    }

    @Override
    public List<Categorie> findAll() {
        String sql = """
                SELECT no_categorie,libelle
                FROM categories
                """;
        List<Categorie> categorieList = jdbcTemplate.query(sql, categorieRowMapper);
        return categorieList;
    }

    @Override
    public void create(Categorie categorie) {
        String sql = """
                INSERT INTO categories (libelle)
                VALUES(?)
                """;
       jdbcTemplate.update(sql, categorie.getLibelle());
    }

    @Override
    public void edit(Categorie categorie) {
        String sql = """
                UPDATE categories
                SET libelle = ?
                WHERE no_categorie = ?
                """;
         jdbcTemplate.update(sql, categorie.getLibelle(), categorie.getNoCategorie());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM categories WHERE no_categorie = ?", id);
    }
}

