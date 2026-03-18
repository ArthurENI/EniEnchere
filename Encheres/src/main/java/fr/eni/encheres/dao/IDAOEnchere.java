package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Enchere;

import java.util.List;

public interface IDAOEnchere {
    Enchere selectById(Long id);
    List<Enchere> findAll();
    List<Enchere> findByArticle(Long idArticle);
}
