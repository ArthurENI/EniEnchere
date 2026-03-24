package fr.eni.encheres.services;

import fr.eni.encheres.bo.*;
import fr.eni.encheres.dao.IDAOArticle;
import fr.eni.encheres.dao.IDAOCategorie;
import fr.eni.encheres.dao.IDAOEnchere;
import fr.eni.encheres.dao.IDAOUtilisateur;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
//@AllArgsConstructor
public class EnchereServiceImpl  implements EnchereService {
    private final IDAOArticle idaoArticle;
    private final IDAOCategorie idaoCategorie;
    private final IDAOUtilisateur idaoUtilisateur;
    private final IDAOEnchere idaoEnchere;


    public EnchereServiceImpl(IDAOArticle idaoArticle, IDAOCategorie idaoCategorie, IDAOUtilisateur idaoUtilisateur, IDAOEnchere idaoEnchere) {
        this.idaoArticle = idaoArticle;
        this.idaoCategorie = idaoCategorie;
        this.idaoUtilisateur = idaoUtilisateur;
        this.idaoEnchere = idaoEnchere;
    }

    @Override
    public Enchere selectEnchereById(Long id) {
        Enchere e = idaoEnchere.selectById(id);

        return e;

    }


    @Override
    public List<Enchere> selectAllEncheres() {
        List<Enchere> encheres = idaoEnchere.findAll();

        return encheres;
    }


    @Override
    public List<Enchere> selectEncheresByArticle(Long noArticle) {
        List<Enchere> encheres = idaoEnchere.findByArticle(noArticle);

        return encheres;

    }


    @Override
    public Enchere selectMeilleureEnchere(Long noArticle) {
        List<Enchere> encheres = idaoEnchere.findByArticle(noArticle);

        return encheres.stream()
                .max(Comparator.comparingInt(Enchere::getMontant_enchere))
                .orElse(null);
    }


    @Override
    public void placerEnchere(Long articleId, Long utilisateurId, int montant) {

        Article a = idaoArticle.selectArticleById(articleId);
        if (a == null) {
            throw new RuntimeException("Aucun article trouver");
        }

        if (a.getUtilisateur().getNoUtilisateur().equals(utilisateurId)) {
            throw new RuntimeException("Le vendeur de l'article ne peut pas mettre d'enchere sur son article");

        }
        Enchere meilleure = selectMeilleureEnchere(articleId);
        if (meilleure != null && montant <= meilleure.getMontant_enchere()) {
            throw new RuntimeException("L'enchere n'est pas assez haute");
        }

        Enchere enchere = new Enchere();
        enchere.setDateEnchere(LocalDateTime.now());
        enchere.setMontant_enchere(montant);

        enchere.setArticle(a);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNoUtilisateur(utilisateurId);
        enchere.setUtilisateur(utilisateur);


        idaoEnchere.create(enchere);


    }

    @Override
    public Enchere getLastEnchere(Long articleId) {
        List<Enchere> encheres = idaoEnchere.findByArticle(articleId);

        if (encheres == null || encheres.isEmpty()) {
            return null;
        }

        return encheres.stream()
                .max(Comparator.comparingInt(Enchere::getMontant_enchere))
                .orElse(null);
    }

}








