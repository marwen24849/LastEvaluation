package fsb.ucar.Microservice.User.dto;


import lombok.Data;

@Data
public class Competence {

    private long id;
    private String nom;
    private int niveau;
    private String description;
    private int annees_experience;
}
