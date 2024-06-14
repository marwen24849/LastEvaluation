package fsb.ucar.Microservice.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizModel {

    private long id;
    private String Title;
    private String Category;
    private double minimumSuccessPercentage;
    private String difficultylevel;
    private boolean passer= false;
    private List<Question> Questions = new ArrayList<>();
    private List<String> id_Responses = new ArrayList<>();




}
