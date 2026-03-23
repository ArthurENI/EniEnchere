package fr.eni.encheres.dao.jdbc;


import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.dao.IDAOAdresse;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@Repository
@Primary
public class DAOAdresseImpl implements IDAOAdresse {

   private final String INSERT = "INSERT INTO ADRESSE (rue, code_postal, ville) VALUES (:rue, :codePostal, :ville)";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOAdresseImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Adresse selectAdresseById(Long id) {
        return null;
    }

    @Override
    public void create(Adresse adresse) {
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("rue", adresse.getRue());
        namedParameter.addValue("codePostal", adresse.getCodePostal());
        namedParameter.addValue("ville", adresse.getVille());
        namedParameterJdbcTemplate.update(INSERT, namedParameter);
    }

    @Override
    public Adresse edit(Adresse adresse) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

class AdresseRowMapper implements RowMapper<Adresse> {

    @Override
    public Adresse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Adresse adresse = new Adresse();
        adresse.setNoAdresse(rs.getLong("no_adresse"));
        adresse.setRue(rs.getString("rue").toLowerCase());
        adresse.setCodePostal(rs.getString("code_postal").toLowerCase());
        adresse.setVille(rs.getString("ville").toLowerCase());
        return adresse;
    }
}
