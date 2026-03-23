package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Objects;

public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long noAdresse;
    @NotBlank
    @Length(min=2, max=250, message = "Le nom de la rue doit comporter au moins 2 caractères")
    private String rue;

    @NotBlank
    @Length(min=5, max=5, message = "Le code postal doit comporter 5 caractères")
    private String codePostal;

    @NotBlank
    @Length(min = 2, max = 250, message = "Le nom de la ville doit comporter au moins 2 caractères")
    private String ville;

    public Adresse() {
    }

    public Adresse(Long noAdresse, String rue, String codePostal, String ville) {
        this.noAdresse = noAdresse;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Long getNoAdresse() {
        return noAdresse;
    }

    public void setNoAdresse(Long noAdresse) {
        this.noAdresse = noAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return Objects.equals(getNoAdresse(), adresse.getNoAdresse()) && Objects.equals(getRue(), adresse.getRue()) && Objects.equals(getCodePostal(), adresse.getCodePostal()) && Objects.equals(getVille(), adresse.getVille());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoAdresse(), getRue(), getCodePostal(), getVille());
    }
}
