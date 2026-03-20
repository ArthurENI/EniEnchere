package fr.eni.encheres.dao.mock;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOCategorie;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class DAOCategorieMock implements IDAOCategorie {
    
    public List<Categorie> dbCategories;

    public DAOCategorieMock() {
        // Création d'une adresse fictive
        Adresse adresse = new Adresse(1L, "1 rue du Test", "75000", "Paris");
        List<Enchere> encheres = new ArrayList<>();
        Role role = new Role();
        // Création d'un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur(1L,"testuser", "Test", "User", "test@eni.fr", "0102030405", "mdp", 100, true, adresse, encheres, role);
        // Création de la catégorie mockée (vide pour l'instant)
        Categorie mockCategorie = new Categorie(1L, "DIVERS", null);
        // Création d'un article fictif
        Article article = new Article(
                1L,
                "Article Test",
                "image.jpg",
                "Description de test",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(5),
                10,
                utilisateur,
                List.of(),
                adresse,
                mockCategorie,
                fr.eni.encheres.bo.EtatVente.OUVERTE
        );
        // Ajout de l'article à la catégorie
        mockCategorie.setArticles(List.of(article));
        dbCategories = java.util.List.of(mockCategorie);
    }
    
    @Override
    public Categorie selectCategorieById(Long id) {
        if (dbCategories == null) return null;
        System.out.println("READ CATEGORIE : "+id);
        return dbCategories.stream()
                .filter(c -> c.getNoCategorie() != null && c.getNoCategorie().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Categorie> findAll() {
        System.out.println("I'M HERE");
        return dbCategories;
    }

    @Override
    public Categorie create(Categorie categorie) {
        return null;
    }

    @Override
    public Categorie edit(Categorie categorie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
