package fsb.ucar.Micro.Service.Badge.dto;


import fsb.ucar.Micro.Service.Badge.entity.BadgeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class BadgeModel {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private BadgeType type;
}
