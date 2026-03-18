package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long noArticle;

    @NotBlank
    @Length(min = 2, max = 250, message = "Le Nom doit avoir au moins 2 caractères")
    private String nomArticle;

    private String nomImage;

    private String description;

    @NotNull(message = "L'enchère doit avoir une date de lancement")
    private LocalDateTime dateDebutEnchere;

    @NotNull(message = "L'enchère doit avoir une date de clôture")
    private LocalDateTime dateFinEnchere;

    @NotBlank
    @Range(min = 1, message = "La valeur minimale doit être de 1 point")
    @Positive
    private int miseAPrix;

    private int prixVenteEnCours;
    private Utilisateur utilisateur;
    private List<Enchere> enchereList;

    @NotNull
    private Adresse adresseRetrait;

    @NotNull
    private Categorie categorie;

    private EtatVente etatVente;



}
