package fsb.ucar.Microservice.QCM.dto;

import fsb.ucar.Microservice.QCM.entity.Question;
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


    private String Title;
    private String Category;
    private int nbQuestion;
    private double minimumSuccessPercentage;
    private String difficultylevel;
    private String name;
    private String description;
    private String type;
    private List<Question> Questions = new ArrayList<>();
}
