package fsb.ucar.Microservice.User.dto;

import lombok.Data;

@Data
public class UserCompetenceDto {

    private String userId;
    private long idCompetence;
    private int level;
    private boolean valide;
}
