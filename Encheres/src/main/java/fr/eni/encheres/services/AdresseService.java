package fr.eni.encheres.services;

import fr.eni.encheres.bo.Adresse;

public interface AdresseService {

    Adresse selectAdresseById(Long id);
    void create(Adresse adresse);
    Adresse edit(Adresse adresse);
    void delete(Long id);
}
