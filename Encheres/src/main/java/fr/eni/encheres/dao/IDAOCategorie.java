package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Categorie;

import java.util.List;

public interface IDAOCategorie {

    Categorie selectCategorieById(Long id);
    List<Categorie> findAll();
    void create(Categorie categorie);
    void edit(Categorie categorie);
    void delete(Long id);
}
