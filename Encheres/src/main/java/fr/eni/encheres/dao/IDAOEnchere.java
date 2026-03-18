package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Enchere;

import java.util.List;

public interface IDAOEnchere {
    Enchere selectById(long id);
    List<Enchere> findAll();
    List<Enchere> findByArticle(long idArticle);
}
