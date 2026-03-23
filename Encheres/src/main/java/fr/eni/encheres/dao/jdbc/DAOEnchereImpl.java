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
        String sql = "SELECT * FROM encheres WHERE no_enchere = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Enchere ench = new Enchere();
            ench.setNoEnchere(rs.getLong("no_enchere"));
            ench.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
            ench.setMontant_enchere(rs.getInt("montant_enchere"));

            return ench;
            }, id);
    }

    @Override
    public List<Enchere> findAll() {
        String sql = "SELECT * FROM encheres";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enchere ench = new Enchere();
            ench.setNoEnchere(rs.getLong("no_enchere"));
            ench.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
            ench.setMontant_enchere(rs.getInt("montant_enchere"));

            return ench;
        });
    }

    @Override
    public List<Enchere> findByArticle(Long noArticle) {
        String sql = "SELECT * FROM encheres WHERE no_article = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enchere ench = new Enchere();
            ench.setNoEnchere(rs.getLong("no_enchere"));
            ench.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
            ench.setMontant_enchere(rs.getInt("montant_enchere"));
            return ench;
            }, noArticle);
    }

    @Override
    public Enchere create(Enchere enchere) {
        String sql = "INSERT INTO encheres (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                enchere.getDateEnchere(),
                enchere.getMontant_enchere(),
                enchere.getArticle().getNoArticle(),
                enchere.getUtilisateur().getNoUtilisateur()
        );
        return enchere;
    }

    @Override
    public Enchere edit(Enchere enchere) {

        String sql = "UPDATE encheres SET date_enchere = ?, montant_enchere = ? WHERE no_enchere = ?";
        jdbcTemplate.update(sql, enchere.getDateEnchere(), enchere.getMontant_enchere(), enchere.getNoEnchere());
        return enchere;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM encheres WHERE no_enchere = ?";
        jdbcTemplate.update(sql, id);
    }
}