package fsb.ucar.Microservice.QCM.dto;


import fsb.ucar.Microservice.QCM.entity.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultatModel {

    private int Score;
    private Response response;
}
