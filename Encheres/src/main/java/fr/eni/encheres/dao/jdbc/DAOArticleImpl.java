package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
//@Primary == Prioriser la class à utiliser pour la DAL
@Primary
public class DAOArticleImpl implements IDAOArticle {

    private final String FIND_ALL = "SELECT no_article, nom_article, nom_image, description_article,date_debut_encheres, date_fin_encheres ,prix_initial, no_utilisateur, no_categorie, no_adresse, etat FROM ARTICLES ";
    private final String FIND_BY_ID = "SELECT no_article, nom_article, nom_image, description_article,date_debut_encheres, date_fin_encheres ,prix_initial, no_utilisateur, no_categorie, no_adresse, etat FROM ARTICLES "
            + "WHERE no_article = :id";
    private final String FIND_BY_NAME = "SELECT * FROM ARTICLES WHERE  nom_article = :nom";
    private final String FIND_BY_STATE = "SELECT * FROM ARTICLES WHERE  etat = :etat";
    private final String INSERT = "INSERT INTO ARTICLES(nom_article, nom_image, description_article, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_adresse, etat) "
            + "VALUES (:nom, :image, :description, :date_debut, :date_fin, :prix, :idUtilisateur, :idCategorie, :idAdresse, :etat)";


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOArticleImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Article> selectAllArticles() {
        return jdbcTemplate.query(FIND_ALL, new ArticleRowMapper());
    }

    @Override
    public Article selectArticleById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new ArticleRowMapper());
    }

    @Override
    public List<Article> selectArticleParNom(String nom) {
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        //Utilisation de wildcards pour une recherche partielle
        //namedParameter.addValue("nom", "%" + nom + "%");
        namedParameter.addValue("nom", nom);
        return namedParameterJdbcTemplate.query(FIND_BY_NAME, namedParameter, new ArticleRowMapper());
    }

    @Override
    public List<Article> selectArticleByUtilisateur(Utilisateur utilisateur) {
        return List.of();
    }

    @Override
    public List<Article> selectArticleByEtat(EtatVente etatVente) {
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("etat", etatVente.name());
        return namedParameterJdbcTemplate.query(FIND_BY_STATE, namedParameter, new ArticleRowMapper());
    }

    @Override
    public List<Article> filterArticles(String nom, Long categorieId, String etat) {
        return List.of();
    }

    @Override
    public Article createArticle() {
        return null;
    }

    @Override
    public Article deleteArticle() {
        return null;
    }

    @Override
    public Article updateArticle() {
        return null;
    }


}
class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setNoArticle(rs.getLong("no_article"));
        article.setNomArticle(rs.getString("nom_article"));
        article.setNomImage(rs.getString("nom_image"));
        article.setDescription(rs.getString("description_article"));
        article.setDateDebutEnchere(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
        article.setDateFinEnchere(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
        article.setMiseAPrix(rs.getInt("prix_initial"));
        //Timestamp.valueOf(localDateTime); pour set la valeur inverse (POUR LE CREATE)

        //Association Utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNoUtilisateur(rs.getLong("no_utilisateur"));
        article.setUtilisateur(utilisateur);

        //Association Categorie
        Categorie categorie = new Categorie();
        categorie.setNoCategorie(rs.getLong("no_categorie"));
        article.setCategorie(categorie);

        //Association Adresse
        Adresse adresse = new Adresse();
        adresse.setNoAdresse(rs.getLong("no_adresse"));
        article.setAdresseRetrait(adresse);
        article.setEtatVente(EtatVente.valueOf(rs.getString("etat")));
        return article;
    }
}
