package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Role;
import fr.eni.encheres.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AuthRowMapper implements RowMapper<Utilisateur> {
    @Nullable
    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNoUtilisateur(rs.getLong("no_utilisateur"));
        utilisateur.setPseudo(rs.getString("pseudo"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setTelephone(rs.getString("telephone"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setCredit(rs.getInt("credit"));
        utilisateur.setActif(rs.getBoolean("actif"));

        // Mapping de l'adresse
        Adresse adresse = new Adresse();
        adresse.setNoAdresse(rs.getLong("no_adresse"));
        adresse.setRue(rs.getString("rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));
        utilisateur.setAdresse(adresse);

        // Mapping du role
        Role role = new Role();
        role.setNo_role(rs.getLong("no_role"));
        role.setLibelle(rs.getString("libelle"));
        utilisateur.setRole(role);

        return utilisateur;
    }
}
