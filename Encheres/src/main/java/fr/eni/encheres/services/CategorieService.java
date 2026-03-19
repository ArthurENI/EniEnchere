package fr.eni.encheres.services;

import fr.eni.encheres.bo.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategorieService {
    Categorie read(Long id);
    List<Categorie> selectAllCategories();
    Categorie create(Categorie categorie);
    Categorie edit(Categorie categorie);
    void delete(Long id);
}
