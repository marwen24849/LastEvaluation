package fsb.ucar.Microservice.QCM.service;


import fsb.ucar.Microservice.QCM.dto.QuestionModel;
import fsb.ucar.Microservice.QCM.entity.Question;
import fsb.ucar.Microservice.QCM.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private QuestionRepository questionRepository;


    public ResponseEntity<Object> getQuestionById(long id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Question non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getQuestionByCategorie(String Categories){
        List<Question> question = this.questionRepository.findByCategory(Categories);
        if(! question.isEmpty()){
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        return new ResponseEntity<>("Question non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getQuestionBylevel(String level){
        List<Question> question = this.questionRepository.findByDifficultylevel(level);
        if(! question.isEmpty()){
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        return new ResponseEntity<>("Question non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> CountQuestionCategorieLevel(String cat, String level){
        return ResponseEntity.ok(this.questionRepository.countQuestionsByCategoryAndDifficultyLevel(cat, level));
    }

    public ResponseEntity<Object> addQuestion(QuestionModel model){
        Question question = new Question();
        question.setQuestionTitle(model.getQuestionTitle());
        question.setCategory(model.getCategory());
        question.setDifficultylevel(model.getDifficultylevel());
        question.setOption1(model.getOption1());
        question.setOption2(model.getOption2());
        question.setOption3(model.getOption3());
        question.setOption4(model.getOption4());
        question.setRightAnswer(model.getRightAnswer());
        question.setScore(model.getScore());
        return new ResponseEntity<>(this.questionRepository.save(question),HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(this.questionRepository.findAll(),HttpStatus.OK);
    }
    public ResponseEntity<Object> updateQuetion(QuestionModel model, long id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            question.get().setQuestionTitle(model.getQuestionTitle());
            question.get().setCategory(model.getCategory());
            question.get().setDifficultylevel(model.getDifficultylevel());
            question.get().setOption1(model.getOption1());
            question.get().setOption2(model.getOption2());
            question.get().setOption3(model.getOption3());
            question.get().setOption4(model.getOption4());
            question.get().setRightAnswer(model.getRightAnswer());
            question.get().setScore(model.getScore());
            return new ResponseEntity<>(this.questionRepository.save(question.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>("Question non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> delete(long id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            this.questionRepository.deleteById(id);
            return new ResponseEntity<>("Suppression Terminer", HttpStatus.OK);
        }
        return new ResponseEntity<>("Non Trouve", HttpStatus.NOT_FOUND);
    }




}
