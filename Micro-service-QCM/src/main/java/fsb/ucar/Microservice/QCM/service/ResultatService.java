package fsb.ucar.Microservice.QCM.service;

import fsb.ucar.Microservice.QCM.dto.ResultatModel;
import fsb.ucar.Microservice.QCM.entity.Quiz;
import fsb.ucar.Microservice.QCM.entity.Resultat;
import fsb.ucar.Microservice.QCM.entity.Response;
import fsb.ucar.Microservice.QCM.repository.QuizRepository;
import fsb.ucar.Microservice.QCM.repository.ResultatRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResultatService {

    private ResultatRepository resultatRepository;
    private QuizRepository quizRepository;

    public ResponseEntity<Object> getResultatById(long id){
        Optional<Resultat> resultat = resultatRepository.findById(id);
        if(resultat.isPresent()){
            return new ResponseEntity<>(resultat.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Résultat non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> addResultat(Resultat resultat){

        return new ResponseEntity<>(resultatRepository.save(resultat), HttpStatus.CREATED);

    }

    public ResponseEntity<Object> getAllResultats(){
        return new ResponseEntity<>(resultatRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateResultat(ResultatModel model, long id){
        Optional<Resultat> resultat = resultatRepository.findById(id);
        if(resultat.isPresent()){
            resultat.get().setScore(model.getScore());
            return new ResponseEntity<>(resultatRepository.save(resultat.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Résultat non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteResultat(long id){
        Optional<Resultat> resultat = resultatRepository.findById(id);
        if(resultat.isPresent()){
            resultatRepository.deleteById(id);
            return new ResponseEntity<>("Suppression terminée", HttpStatus.OK);
        }
        return new ResponseEntity<>("Résultat non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> findByIdQuiz(long id_quiz){
        Quiz quiz = this.quizRepository.findById(id_quiz).get();
        List<Resultat> resultats = this.resultatRepository.findResultatQuiz(quiz);
        return new ResponseEntity<>(resultats,HttpStatus.OK);
    }

}
