package fr.eni.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enchere implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dateEnchere;
    private int montantEnchere;
    private Utilisateur utilisateur;
    private Article article;

}
