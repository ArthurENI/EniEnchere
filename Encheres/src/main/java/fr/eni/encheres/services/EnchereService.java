package fr.eni.encheres.services;

import fr.eni.encheres.bo.Enchere;

import java.util.List;

public interface EnchereService {


    Enchere selectEnchereById(Long id);
    List<Enchere> selectAllEncheres();
    List<Enchere> selectEncheresByArticle(Long articleId);
    Enchere selectMeilleureEnchere(Long articleId);


    void placerEnchere(Long articleId, Long utilisateurId, int montant);
    Enchere getLastEnchere(Enchere enchere);
}

