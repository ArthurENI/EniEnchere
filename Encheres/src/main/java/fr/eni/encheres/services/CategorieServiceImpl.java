package fr.eni.encheres.services;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.IDAOArticle;
import fr.eni.encheres.dao.IDAOCategorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService{

    private final IDAOCategorie idaoCategorie;
    private final IDAOArticle  idaoArticle;

    public CategorieServiceImpl(IDAOCategorie idaoCategorie, IDAOArticle idaoArticle) {
        this.idaoCategorie = idaoCategorie;
        this.idaoArticle = idaoArticle;
    }

    @Override
    public Categorie read(Long id) {
        Categorie categorie = idaoCategorie.selectCategorieById(id);
        if(categorie != null){
            chargerArticles(categorie);
        }
        return categorie;
    }

    @Override
    public List<Categorie> selectAllCategories() {
        List<Categorie> categories = idaoCategorie.findAll();
        /*if(categories != null){
            categories.forEach(this::chargerArticles);

        }*/
        return categories;
    }

    @Override
    public Categorie create(Categorie categorie) {
        return null;
    }

    @Override
    public Categorie edit(Categorie categorie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private void chargerArticles(Categorie categorie){
        Article article = idaoArticle.selectArticleById(categorie.getNoCategorie());
        categorie.setArticles(List.of(article));
    }
}
