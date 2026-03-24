package fr.eni.encheres.services;

import fr.eni.encheres.bo.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategorieService {
    Categorie read(Long id);
    List<Categorie> selectAllCategories();
    void create(Categorie categorie);
    void edit(Categorie categorie);
    void delete(Long id);
}
