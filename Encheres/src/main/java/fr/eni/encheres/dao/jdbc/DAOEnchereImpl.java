package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOEnchere;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DAOEnchereImpl implements IDAOEnchere {

    private final JdbcTemplate jdbcTemplate;

    public DAOEnchereImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Enchere> enchereRowMapper = (rs, rowNum) -> {
        Enchere ench = new Enchere();
        ench.setNoEnchere(rs.getLong("no_enchere"));
        ench.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
        ench.setMontant_enchere(rs.getInt("montant_enchere"));
        Article arti = new Article();
        arti.setNoArticle(rs.getLong("no_article"));
        arti.setNomArticle(rs.getString("nom_article"));
        arti.setMiseAPrix(rs.getInt("prix_initial"));
        Utilisateur user = new Utilisateur();
        user.setNoUtilisateur(rs.getLong("no_utilisateur"));
        user.setPseudo(rs.getString("pseudo"));
        ench.setUtilisateur(user);
        ench.setArticle(arti);
        return ench;
    };


    @Override
    public Enchere selectById(Long id) {
        String sql = " SELECT e.*, a.nom_article, a.prix_initial, u.pseudo" +
                "            FROM encheres e" +
                "            JOIN articles a ON e.no_article = a.no_article" +
                "            JOIN utilisateurs u ON e.no_utilisateur = u.no_utilisateur" +
                "            WHERE e.no_enchere = ?";
        return jdbcTemplate.queryForObject(sql, enchereRowMapper, id);
    }

    @Override
    public List<Enchere> findAll() {
        String sql = "SELECT e.*, a.nom_article, a.prix_initial, u.pseudo" +
                "            FROM encheres e" +
                "            JOIN articles a ON e.no_article = a.no_article" +
                "            JOIN utilisateurs u ON e.no_utilisateur = u.no_utilisateur";
        return jdbcTemplate.query(sql, enchereRowMapper);
    }

    @Override
    public List<Enchere> findByArticle(Long noArticle) {
        String sql = "SELECT e.*, a.nom_article, a.prix_initial, u.pseudo" +
                "            FROM encheres e" +
                "            JOIN articles a ON e.no_article = a.no_article" +
                "            JOIN utilisateurs u ON e.no_utilisateur = u.no_utilisateur" +
                "            WHERE e.no_article = ?";
        return jdbcTemplate.query(sql, enchereRowMapper, noArticle);
    }

    @Override
    public Enchere create(Enchere enchere) {
        String sql = "INSERT INTO encheres (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?, ?, ?,?)";
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