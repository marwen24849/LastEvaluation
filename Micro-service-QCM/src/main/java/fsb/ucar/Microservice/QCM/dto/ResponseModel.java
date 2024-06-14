package fsb.ucar.Microservice.QCM.dto;

import fsb.ucar.Microservice.QCM.entity.Quiz;
import jakarta.persistence.Embeddable;
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
public class ResponseModel {

    private List<QuestionResponse> responses = new ArrayList<>();
    private Quiz quiz;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class QuestionResponse {
        private String question;
        private String answer;
    }



}
