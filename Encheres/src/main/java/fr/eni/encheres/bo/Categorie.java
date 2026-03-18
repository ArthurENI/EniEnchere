package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    private long noCategorie;

    @NotNull (message = "Vous devez choisir une catégorie")
    private String libelle;
    private List<Article> articleList;
}
