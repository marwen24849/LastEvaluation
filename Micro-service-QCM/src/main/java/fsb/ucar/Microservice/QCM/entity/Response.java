package fsb.ucar.Microservice.QCM.entity;



import fsb.ucar.Microservice.QCM.dto.ResponseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @Id
    private String Id;

    @ElementCollection
    private List<ResponseModel.QuestionResponse> responses = new ArrayList<>();

    @ManyToOne
    private Quiz quiz;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Resultat resultat;

}
