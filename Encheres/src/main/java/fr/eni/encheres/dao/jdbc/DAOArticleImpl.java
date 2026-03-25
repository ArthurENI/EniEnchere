package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOArticle;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
//@Primary == Prioriser la class à utiliser pour la DAL
@Primary
public class DAOArticleImpl implements IDAOArticle {


    private final String FIND_ALL_JOIN = """
                SELECT no_article, nom_article, nom_image, description_article,date_debut_encheres,
                			date_fin_encheres ,prix_initial, a.no_utilisateur,pseudo, a.no_adresse,rue, code_postal, ville, etat ,
                			a.no_categorie, libelle FROM ARTICLES as a LEFT OUTER JOIN CATEGORIES as c ON a.no_categorie = c.no_categorie
                			LEFT OUTER JOIN ADRESSE as ad ON a.no_adresse = ad.no_adresse LEFT OUTER JOIN UTILISATEURS as u ON a.no_utilisateur = u.no_utilisateur;
            """;

    private final String FIND_BY_ID = """
           SELECT no_article, nom_article, nom_image, description_article,date_debut_encheres, date_fin_encheres ,prix_initial, u.no_utilisateur, 
                  pseudo, a.no_categorie,c.libelle, a.no_adresse, rue,code_postal,ville, etat FROM ARTICLES as a
               LEFT OUTER JOIN CATEGORIES as c ON a.no_categorie = c.no_categorie
               LEFT OUTER JOIN UTILISATEURS as u ON a.no_utilisateur = u.no_utilisateur
               LEFT OUTER JOIN ADRESSE as ad ON a.no_adresse = ad.no_adresse
               WHERE no_article = :id
           """;

    private final String FIND_BY_USER = """
           SELECT no_article, nom_article, nom_image, description_article,date_debut_encheres, date_fin_encheres ,prix_initial, u.no_utilisateur, pseudo, a.no_categorie,c.libelle, a.no_adresse, etat FROM ARTICLES as a
               LEFT OUTER JOIN CATEGORIES as c ON a.no_categorie = c.no_categorie
               LEFT OUTER JOIN UTILISATEURS as u ON a.no_utilisateur = u.no_utilisateur
               LEFT OUTER JOIN ADRESSE as ad ON a.no_adresse = ad.no_adresse
               WHERE u.no_utilisateur = :id
           """;
    private final String FIND_BY_NAME = "SELECT * FROM ARTICLES WHERE  nom_article = :nom";
    private final String FIND_BY_STATE = "SELECT * FROM ARTICLES WHERE  etat = :etat";
    private final String INSERT = """
            INSERT INTO ARTICLES(nom_article, nom_image, description_article, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_adresse, etat)\s
            VALUES (:nom, :image, :description, :date_debut, :date_fin, :prix, :idUtilisateur, :idCategorie, :idAdresse, :etat)
           \s""";

    private final String UPDATE = """
            UPDATE ARTICLES SET nom_article = :nom,
            nom_image = :image, description_article = :description,
            date_debut_encheres = :date_debut, date_fin_encheres = :date_fin, prix_initial = :prix,
            no_utilisateur = :idUtilisateur, no_categorie = :idCategorie,
            no_adresse = :idAdresse, etat = :etat
            WHERE no_article = :id
           """;

    private final String UPDATE_ADRESS = """
            UPDATE ADRESSE SET rue = :rue, code_postal = :codePostal, ville = :ville
            WHERE no_adresse = :id
           """;


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOArticleImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Article> selectAllArticles() {
        return jdbcTemplate.query(FIND_ALL_JOIN, new ArticleRowMapper());
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
    public List<Article> selectArticleByUtilisateur(Long idUser) {
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("id", idUser);
        return namedParameterJdbcTemplate.query(FIND_BY_USER, namedParameter, new ArticleRowMapper());
    }

    @Override
    public List<Article> selectArticleByEtat(EtatVente etatVente) {
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("etat", etatVente.name());
        return namedParameterJdbcTemplate.query(FIND_BY_STATE, namedParameter, new ArticleRowMapper());
    }

    @Override
    public List<Article> filterArticles(String nom, Long categorieId) {
        return List.of();
    }

    @Override
    public void createArticle(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO adresse (rue,code_postal,ville) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, article.getAdresseRetrait().getRue());
            ps.setString(2, article.getAdresseRetrait().getCodePostal());
            ps.setString(3, article.getAdresseRetrait().getVille());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();

        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("nom", article.getNomArticle());
        namedParameter.addValue("image", article.getNomImage());
        namedParameter.addValue("description", article.getDescription());
        namedParameter.addValue("date_debut", article.getDateDebutEnchere());
        namedParameter.addValue("date_fin", article.getDateFinEnchere());
        namedParameter.addValue("prix", article.getMiseAPrix());
        namedParameter.addValue("idUtilisateur", article.getUtilisateur().getNoUtilisateur());
        namedParameter.addValue("idCategorie", article.getCategorie().getNoCategorie());
        namedParameter.addValue("idAdresse", id);
        namedParameter.addValue("etat", article.getEtatVente().name());

        namedParameterJdbcTemplate.update(INSERT, namedParameter);

    }

    @Override
    public Article deleteArticle() {
        return null;
    }

    @Override
    public void updateArticle(Article article) {
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("id", article.getNoArticle());
        namedParameter.addValue("nom", article.getNomArticle());
        namedParameter.addValue("image", article.getNomImage());
        namedParameter.addValue("description", article.getDescription());
        namedParameter.addValue("date_debut", article.getDateDebutEnchere());
        namedParameter.addValue("date_fin", article.getDateFinEnchere());
        namedParameter.addValue("prix", article.getMiseAPrix());
        namedParameter.addValue("idUtilisateur", article.getUtilisateur().getNoUtilisateur());
        namedParameter.addValue("idCategorie", article.getCategorie().getNoCategorie());
        namedParameter.addValue("idAdresse", article.getAdresseRetrait().getNoAdresse());
        namedParameter.addValue("etat", article.getEtatVente().name());

        namedParameterJdbcTemplate.update(UPDATE, namedParameter);

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

        //Gestion d'une date si elle est null
        LocalDateTime dateDebutEncheres = rs.getTimestamp("date_debut_encheres").toLocalDateTime();
        if(dateDebutEncheres != null){
            article.setDateDebutEnchere(dateDebutEncheres);
        }
        LocalDateTime dateFinEncheres = rs.getTimestamp("date_fin_encheres").toLocalDateTime();
        if(dateFinEncheres != null) {
            article.setDateFinEnchere(dateFinEncheres);
        }


        article.setMiseAPrix(rs.getInt("prix_initial"));
        //Timestamp.valueOf(localDateTime); pour set la valeur inverse (POUR LE CREATE)

        //Association Utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNoUtilisateur(rs.getLong("no_utilisateur"));
        utilisateur.setPseudo(rs.getString("pseudo"));
        article.setUtilisateur(utilisateur);

        //Association Categorie
        //changer le nom de la categorie
        Categorie categorie = new Categorie();
        categorie.setNoCategorie(rs.getLong("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));
        article.setCategorie(categorie);

        //Association Adresse
        Adresse adresse = new Adresse();
        adresse.setNoAdresse(rs.getLong("no_adresse"));
        adresse.setRue(rs.getString("rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));
        article.setAdresseRetrait(adresse);
        article.setEtatVente(EtatVente.valueOf(rs.getString("etat")));
        return article;
    }
}
