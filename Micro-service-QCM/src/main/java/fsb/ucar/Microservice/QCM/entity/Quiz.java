package fsb.ucar.Microservice.QCM.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    private String category;
    private double minimumSuccessPercentage;
    private String difficultylevel;
    private boolean passer= false;
    private boolean badge;
    private long id_badge;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Question> Questions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Response> responses = new ArrayList<>();
    private List<String> id_Responses = new ArrayList<>();


}
