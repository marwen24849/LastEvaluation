package fsb.ucar.Microservice.User.dto;

import lombok.Data;

@Data
public class Badge {

    private Long id;
    private String name;
    private String description;
    private String type;
}
