package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Categorie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategorieRowMapper implements RowMapper<Categorie> {
    @Nullable
    @Override
    public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categorie categorie = new Categorie();

        categorie.setNoCategorie(rs.getLong("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));

        return categorie;
    }
}
