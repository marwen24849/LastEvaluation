package fsb.ucar.Microservice.User.dto;

import fsb.ucar.Microservice.User.entity.UserEntity;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class EquipeModel {



    private String name;
    private String department;

    private List<UserEntity> users = new ArrayList<>();
}
