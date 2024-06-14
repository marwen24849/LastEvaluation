package fsb.ucar.Microservice.User.entity;


import fsb.ucar.Microservice.User.dto.Competence;
import fsb.ucar.Microservice.User.dto.Formation;
import fsb.ucar.Microservice.User.dto.QuizModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userEntity_T")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String level;
    private int yearsexperience;
    private List<String> roles = new ArrayList<>();

    @Transient
    private List<QuizModel> quiz = new ArrayList<>();
    private List<Long> id_quiz = new ArrayList<>();
    private List<Long> id_quiz_Passer = new ArrayList<>();
    private List<Long> id_Badge = new ArrayList<>();

    private List<Long> id_competences = new ArrayList<>();

    @Transient
    private List<Formation> formations = new ArrayList<>();
    private List<Long> id_formations = new ArrayList<>();


    @ElementCollection
    private Map<Long, String> quizResponses;

}
