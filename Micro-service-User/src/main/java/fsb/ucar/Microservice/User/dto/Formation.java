package fsb.ucar.Microservice.User.dto;


import lombok.Data;

@Data
public class Formation {

    private Long id;
    private String title;
    private String description;
    private String duration;
    private String instructor;
    private String certification;
}
