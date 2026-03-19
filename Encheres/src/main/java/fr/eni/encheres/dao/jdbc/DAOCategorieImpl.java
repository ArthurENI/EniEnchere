package fr.eni.encheres.dao.jdbc;


import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.IDAOCategorie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DAOCategorieImpl implements IDAOCategorie {

    private final JdbcTemplate jdbcTemplate;

    public DAOCategorieImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Categorie read(Long id) {
        String sql = "SELECT * FROM Categorie WHERE NoCategorie = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Categorie cat = new Categorie();
            cat.setNoCategorie(rs.getLong("NoCategorie"));
            cat.setLibelle(rs.getString("Libelle"));
            return cat;
        }, id);
    }

    @Override
    public List<Categorie> findAll() {
        String sql = "SELECT * FROM Categorie";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Categorie cat = new Categorie();
            cat.setNoCategorie(rs.getLong("NoCategorie"));
            cat.setLibelle(rs.getString("Libelle"));
            return cat;
        });
    }

    @Override
    public Categorie create (Categorie categorie) {

        String sql = "INSERT INTO Categorie (Libelle) VALUES (?)";
        jdbcTemplate.update(sql, categorie.getLibelle());
        return categorie;
    }

    @Override
    public Categorie edit (Categorie categorie) {

        String sql = "UPDATE Categorie SET Libelle = ? WHERE NoCategorie = ?";
        jdbcTemplate.update(sql, categorie.getLibelle(), categorie.getNoCategorie());
        return categorie;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Categorie WHERE NoCategorie = ?";
        jdbcTemplate.update(sql, id);
    }


}