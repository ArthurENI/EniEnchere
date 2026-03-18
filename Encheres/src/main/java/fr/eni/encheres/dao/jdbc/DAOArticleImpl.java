package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private final String FIND_ALL = "SELECT ID, NOM_ARTICLE, NOM_IMAGE, DESCRIPTION,DATE_DEBUT_ENCHERE, DATE_FIN_ENCHERE" +
            "MISE_A_PRIX, PRIX_VENTE_EN_COURS , ID_UTILISATEUR, ID_CATEGORIE FROM ARTICLES ";
    //TODO COMMENT AJOUTER ENCHERES ADRESSE CATEGORIE ETATVENTE

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> selectAllArticles() {
        return jdbcTemplate.query(FIND_ALL, new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                Article article = new Article();
                article.setNoArticle(rs.getLong("ID"));
                article.setNomArticle(rs.getString("NOM_ARTICLE"));
                article.setNomImage(rs.getString("NOM_IMAGE"));
                article.setDescription(rs.getString("DESCRIPTION"));
                article.setMiseAPrix(rs.getInt("MISE_A_PRIX"));
                article.setPrixVenteEnCours(rs.getInt("PRIX_VENTE_EN_COURS"));
                //TODO AJOUTER DATES
                //TODO AJOUTER UTLISATEUR ETC
                //Association Realisateur
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNoUtilisateur(rs.getLong("ID_UTILISATEUR"));
                article.setUtilisateur(utilisateur);
                //Association Categorie
                /*Categorie categorie = new Categorie();
                categorie.setNoCategorie(rs.getLong("ID_CATEGORIE"));
                article.setCategorie(categorie);*/
                return article;
            }
        });
    }

    @Override
    public Article selectArticleById(Long id) {
        return null;
    }

    @Override
    public List<Article> filterArticleParNom(String nom) {
        return List.of();
    }

    @Override
    public List<Article> selectArticleByUtilisateur(Utilisateur utilisateur) {
        return List.of();
    }

    @Override
    public List<Article> selectArticleByEtat(EtatVente etatVente) {
        return List.of();
    }

    @Override
    public List<Article> filterArticles(String nom, Long categorieId) {
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
