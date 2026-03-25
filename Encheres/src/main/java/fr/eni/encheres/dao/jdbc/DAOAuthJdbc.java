
package fr.eni.encheres.dao.jdbc;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.IDAOUtilisateur;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Primary
@Component
public class DAOAuthJdbc implements IDAOUtilisateur {
    private final JdbcTemplate jdbcTemplate;
    private final AuthRowMapper authRowMapper;

    public DAOAuthJdbc(JdbcTemplate jdbcTemplate, AuthRowMapper authRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.authRowMapper = authRowMapper;
    }

    @Override
    public List<Utilisateur> selectAllUtilisateurs() {
        String sql = """
                SELECT u.no_utilisateur,u.pseudo,u.nom,u.prenom,u.email,u.telephone,
                       u.mot_de_passe,u.credit,u.actif,
                       a.no_adresse,a.rue,a.code_postal,a.ville,
                       r.no_role,r.libelle
                FROM utilisateurs u
                JOIN adresse a ON u.no_adresse = a.no_adresse
                JOIN role r ON u.no_role = r.no_role;
                """;
        List<Utilisateur> utilisateurList = jdbcTemplate.query(sql, authRowMapper);
        return utilisateurList;
    }

    @Override
    public Utilisateur selectUtilisateurById(Long id) {
        String sql = """
                SELECT u.no_utilisateur,u.pseudo,u.nom,u.prenom,u.email,u.telephone,
                       u.mot_de_passe,u.credit,u.actif,
                       a.no_adresse,a.rue,a.code_postal,a.ville,
                       r.no_role,r.libelle
                FROM utilisateurs u
                JOIN adresse a ON u.no_adresse = a.no_adresse
                JOIN role r ON u.no_role = r.no_role
                WHERE u.no_utilisateur = ?;
                """;
        List<Utilisateur> utilisateurList = jdbcTemplate.query(sql, authRowMapper, id);
        // Tester que y'a au moins un element trouvé
        if (!utilisateurList.isEmpty()) {
            // Je prend le premier resultat pour simuler un "get by id"
            return utilisateurList.get(0);
        } else return null;
    }

    @Override
    public Utilisateur selectUtilisateurByEmail(String email) {
        String sql = """
                SELECT u.no_utilisateur,u.pseudo,u.nom,u.prenom,u.email,u.telephone,
                       u.mot_de_passe,u.credit,u.actif,
                       a.no_adresse,a.rue,a.code_postal,a.ville,
                       r.no_role,r.libelle
                FROM utilisateurs u
                JOIN adresse a ON u.no_adresse = a.no_adresse
                JOIN role r ON u.no_role = r.no_role
                WHERE u.email = ?;
                """;

        List<Utilisateur> utilisateurList = jdbcTemplate.query(sql, authRowMapper, email);
        // Tester que y'a au moins un element trouvé
        if (!utilisateurList.isEmpty()) {
            // Je prend le premier resultat pour simuler un "get by id"
            return utilisateurList.get(0);
        } else return null;
    }

    @Override
    public Utilisateur selectUtilisateurByPseudoAndPassword(String pseudo, String password) {
        String sql = """
                SELECT u.no_utilisateur,u.pseudo,u.nom,u.prenom,u.email,u.telephone,
                       u.mot_de_passe,u.credit,u.actif,
                       a.no_adresse,a.rue,a.code_postal,a.ville,
                       r.no_role,r.libelle
                FROM utilisateurs u
                JOIN adresse a ON u.no_adresse = a.no_adresse
                JOIN role r ON u.no_role = r.no_role
                WHERE u.pseudo = ? AND u.mot_de_passe = ?;
                """;
        List<Utilisateur> utilisateurList = jdbcTemplate.query(sql, authRowMapper, pseudo, password);
        // Tester que y'a au moins un element trouvé
        if (!utilisateurList.isEmpty()) {
            return utilisateurList.get(0);

        } else return null;
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {

        // Dans le cas d'une adresse par utilisateur
        String sql = """
                UPDATE utilisateurs
                SET pseudo = ?,nom = ?,prenom = ?,email = ?,telephone = ?,mot_de_passe = ?
                WHERE no_utilisateur = ?
                
                
                """;
        jdbcTemplate.update(sql, utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),
                utilisateur.getTelephone(), utilisateur.getMotDePasse().hashCode(), utilisateur.getNoUtilisateur()
                );
        sql = """
                UPDATE adresse
                SET rue = ?,code_postal = ?,ville = ?
                WHERE no_adresse = ?
                """;
        jdbcTemplate.update(sql,utilisateur.getAdresse().getRue(), utilisateur.getAdresse().getCodePostal(), utilisateur.getAdresse().getVille(),
                utilisateur.getAdresse().getNoAdresse());

    }

    @Override
    public void updateMdp(Utilisateur utilisateur) throws SQLException {
        String sql = """
                UPDATE utilisateurs
                SET mot_de_passe = ?
                WHERE no_utilisateur = ?
                """;
        jdbcTemplate.update(sql, utilisateur.getMotDePasse().hashCode(), utilisateur.getNoUtilisateur());
    }

    @Override
    public void createCompte(Utilisateur utilisateur) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO adresse (rue,code_postal,ville) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, utilisateur.getAdresse().getRue());
            ps.setString(2, utilisateur.getAdresse().getCodePostal());
            ps.setString(3, utilisateur.getAdresse().getVille());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();


        String sql = """
                INSERT INTO utilisateurs (pseudo,nom,prenom,email,telephone,mot_de_passe,
                 credit,actif,no_adresse,no_role)
                VALUES(?,?,?,?,?,?,?,?,?,?)
                """;
        jdbcTemplate.update(sql, utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),
                utilisateur.getTelephone(), utilisateur.getMotDePasse().hashCode(), 1000, utilisateur.isActif(), id, 1);
    }

    @Override
    public void deleteCompte(Utilisateur utilisateur)  {
        jdbcTemplate.update("DELETE FROM utilisateurs WHERE no_utilisateur = ?", utilisateur.getNoUtilisateur());

    }
}



