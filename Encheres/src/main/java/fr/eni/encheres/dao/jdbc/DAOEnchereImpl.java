package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dao.IDAOEnchere;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DAOEnchereImpl implements IDAOEnchere {

    private final JdbcTemplate jdbcTemplate;

    public DAOEnchereImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Enchere selectById(Long id) {
        String sql = "SELECT * FROM Enchere WHERE noEnchere = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Enchere ench = new Enchere();
            ench.setNoEnchere(rs.getLong("noEnchere"));
            ench.setDateEnchere(rs.getTimestamp("dateEnchere").toLocalDateTime());
            ench.setMontant_enchere(rs.getInt("montant_enchere"));

            return ench;
            }, id);
    }

    @Override
    public List<Enchere> findAll() {
        String sql = "SELECT * FROM Enchere";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enchere ench = new Enchere();
            ench.setNoEnchere(rs.getLong("noEnchere"));
            ench.setDateEnchere(rs.getTimestamp("dateEnchere").toLocalDateTime());
            ench.setMontant_enchere(rs.getInt("montant_enchere"));

            return ench;
        });
    }

    @Override
    public List<Enchere> findByArticle(Long noArticle) {
        String sql = "SELECT * FROM Enchere WHERE noArticle = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enchere ench = new Enchere();
            ench.setNoEnchere(rs.getLong("noEnchere"));
            ench.setDateEnchere(rs.getTimestamp("dateEnchere").toLocalDateTime());
            ench.setMontant_enchere(rs.getInt("montant_enchere"));
            return ench;
            }, noArticle);
    }

    @Override
    public Enchere create(Enchere enchere) {
        String sql = "INSERT INTO Enchere (dateEnchere, montant_enchere) VALUES (?, ?)";
        jdbcTemplate.update(sql, enchere.getDateEnchere(), enchere.getMontant_enchere());
        return enchere;
    }

    @Override
    public Enchere edit(Enchere enchere) {

        String sql = "UPDATE Enchere SET dateEnchere = ?, montant_enchere = ? WHERE noEnchere = ?";
        jdbcTemplate.update(sql, enchere.getDateEnchere(), enchere.getMontant_enchere(), enchere.getNoEnchere());
        return enchere;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Enchere WHERE NoEnchere = ?";
        jdbcTemplate.update(sql, id);
    }
}