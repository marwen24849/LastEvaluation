package fsb.ucar.Microservice.QCM.dto;

import lombok.Data;

@Data
public class Badge {

    private Long id;
    private String name;
    private String description;
    private String type;
}
