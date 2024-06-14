package fsb.ucar.Microservice.QCM.controller;


import fsb.ucar.Microservice.QCM.client.BadgeClient;
import fsb.ucar.Microservice.QCM.dto.Badge;
import fsb.ucar.Microservice.QCM.dto.QuizModel;
import fsb.ucar.Microservice.QCM.entity.Question;
import fsb.ucar.Microservice.QCM.entity.Quiz;
import fsb.ucar.Microservice.QCM.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Quiz")
public class QuizController {

    private QuizService service;
    private BadgeClient client;


    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable long id){
        return this.service.getQuizById(id);
    }

    @GetMapping("/Cat/{cat}")
    public ResponseEntity<Object> getQuizCat(@PathVariable String cat){
        return this.service.getQuizByCategorie(cat);
    }

    @GetMapping("/All")
    public ResponseEntity<Object> findAll(){
        return this.service.getAllQuizzes();
    }

    @PostMapping("/All")
    public ResponseEntity<Object> addQuiz(@RequestBody QuizModel model){
        return this.service.addQuiz(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatQuiz(@PathVariable long id, @RequestBody QuizModel model){
       return this.service.updateQuiz(id, model);
    }

    @PutMapping("/{id}/AddQuestion/{id_Question}")
    public ResponseEntity<Object> updatQuestionQuiz(@PathVariable("id") long id, @PathVariable("id_Question") long id_Question){
        return this.service.ajoutQuestionsQuiz(id, id_Question);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable long id, @RequestBody List<Question> questions){
        return this.service.deleteQuiz(id, questions);
    }

    @DeleteMapping("/{id_Quiz}/Question/{id_Question}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable("id_Quiz") long id_Quiz, @PathVariable("id_Question") long id_Question){
        return this.service.deleteQuestionFromQuiz(id_Quiz, id_Question);
    }

    @GetMapping("/badge")
    public ResponseEntity<List<Badge>> getById(){
        return client.getAllBadges();
    }

    @PostMapping("/badAd")
    public ResponseEntity<Long> add(@RequestBody Badge badge){
        return client.addBadge(badge);
    }


    @GetMapping("cat/{Categories}/level/{level}")
    public ResponseEntity<Object> QuizcatLevel(@PathVariable  String Categories, @PathVariable String level){
        return this.service.QuizcatLevel(Categories, level);
    }

    @GetMapping("Question/{idquiz}")
    public ResponseEntity<List<Question>>findQuestionNotExist(@PathVariable long idquiz){
        return this.service.findQuestionNotExist(idquiz);
    }

}
