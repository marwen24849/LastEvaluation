package fsb.ucar.Microservice.QCM.service;

import fsb.ucar.Microservice.QCM.dto.ResponseEntryModel;
import fsb.ucar.Microservice.QCM.dto.ResponseModel;
import fsb.ucar.Microservice.QCM.entity.Question;
import fsb.ucar.Microservice.QCM.entity.Quiz;
import fsb.ucar.Microservice.QCM.entity.Response;
import fsb.ucar.Microservice.QCM.entity.Resultat;
import fsb.ucar.Microservice.QCM.repository.QuizRepository;
import fsb.ucar.Microservice.QCM.repository.ResponseRepository;
import fsb.ucar.Microservice.QCM.repository.ResultatRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResponseService {

    private ResponseRepository responseRepository;
    private ResultatRepository resultatRepository;
    private QuizRepository quizRepository;

    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(this.responseRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<Object> findById(String id){
        Optional<Response> response = this.responseRepository.findById(id);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Response non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> addResponse(ResponseModel model){
        Response response = new Response();
        Resultat resultat = new Resultat();
        int score = 0;
        double percentage =0.0;
        Quiz quiz = model.getQuiz();
        int nbq = 0;
        response.setResponses(model.getResponses());
        response.setQuiz(quiz);
        for(Question q : quiz.getQuestions()){
            for (ResponseModel.QuestionResponse responsee : model.getResponses()) {
                if (q.getRightAnswer().equals(responsee.getAnswer())
                        &&
                        q.getQuestionTitle().equals(responsee.getQuestion())) {
                    score += q.getScore();
                }
            }
            nbq += q.getScore();
        }
        percentage = (double) (score * 100) /nbq;
        resultat.setResultat(quiz.getMinimumSuccessPercentage()<= percentage);
        resultat.setPercentage(percentage);
        response.setResultat(resultat);
        resultat.setScore(score);
        String id_response = RandomStringUtils.randomAlphanumeric(8);
        response.setId(id_response);
        this.resultatRepository.save(resultat);
        if(quiz.getId_Responses() == null)
            quiz.setId_Responses(new ArrayList<>());
        quiz.getId_Responses().add(id_response);
        if(quiz.getResponses() == null)
            quiz.setResponses(new ArrayList<>());
        quiz.getResponses().add(response);
        quiz.setPasser(true);
        this.quizRepository.save(quiz);
        return new ResponseEntity<>(this.responseRepository.save(response),HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateResponse(String id, ResponseModel model){
        int score = 0;
        Optional<Response> optionalResponse = responseRepository.findById(id);
        if(optionalResponse.isPresent()){
            Response response = optionalResponse.get();
            response.setResponses(model.getResponses());
            Quiz quiz = model.getQuiz();
            response.setQuiz(quiz);
            for(Question q : quiz.getQuestions()){
                for (ResponseModel.QuestionResponse responsee : model.getResponses()) {
                    if (q.getRightAnswer().equals(responsee.getAnswer())
                            &&
                            q.getQuestionTitle().equals(responsee.getQuestion())) {
                        score += q.getScore();
                        break;
                    }
                }
            }
            Resultat resultat = response.getResultat();
            resultat.setScore(score);
            resultatRepository.save(resultat);
            responseRepository.save(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Response non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteResponse(String id){
        Optional<Response> optionalResponse = responseRepository.findById(id);
        if(optionalResponse.isPresent()){
            Response response = optionalResponse.get();
            // Suppression du résultat associé
            Resultat resultat = response.getResultat();
            resultatRepository.delete(resultat);
            // Suppression de la réponse
            responseRepository.delete(response);
            return new ResponseEntity<>("Suppression terminée", HttpStatus.OK);
        }
        return new ResponseEntity<>("Response non trouvé", HttpStatus.NOT_FOUND);
    }
}



