package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Categorie;

import java.util.List;

public interface IDAOCategorie {

    Categorie read( long id );
    List<Categorie> findAll();
}
