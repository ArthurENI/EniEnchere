package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Adresse;

public interface IDAOAdresse {

    Adresse selectAdresseById(Long id);
    void create(Adresse adresse);
    Adresse edit(Adresse adresse);
    void delete(Long id);
}
