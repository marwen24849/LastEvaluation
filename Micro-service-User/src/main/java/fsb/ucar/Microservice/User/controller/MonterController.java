package fsb.ucar.Microservice.User.controller;

import fsb.ucar.Microservice.User.client.FormationClient;
import fsb.ucar.Microservice.User.client.QuizClient;
import fsb.ucar.Microservice.User.dto.Formation;
import fsb.ucar.Microservice.User.dto.QuizModel;
import fsb.ucar.Microservice.User.service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monter")
@AllArgsConstructor
public class MonterController {

    private MentorService service;
    private QuizClient client;
    private FormationClient formationClient;
    @PostMapping("Quiz/{id_Q}/User/{id_U}")
public ResponseEntity<QuizModel> AffectQuizUser(@PathVariable("id_Q") long id_Q, @PathVariable("id_U") String id_U){
        return this.service.AffectQuizUser(id_Q, id_U);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<QuizModel> GetQuiz(@PathVariable long id){
        return this.service.getQuizById(id);
    }

   @DeleteMapping("Quiz/{id_Q}/User/{id_U}")
    public ResponseEntity<Object> DeleteQuizUSer(@PathVariable("id_Q") long id_Q, @PathVariable("id_U") String id_U){
        return this.service.deleteQuizFromUser(id_Q, id_U);
    }

    @GetMapping("QuizUser/{id}")
    public ResponseEntity<List<QuizModel>> getAllQuizExceptUserSubscribed(@PathVariable String id){
        return this.service.getAllQuizExceptUserSubscribed(id);
    }

    @PutMapping("user/{id}")
    public void setlevelUser(@PathVariable String id, @RequestParam String level, @RequestParam int years){
        this.service.setLevelCollo(id,level,years);
    }

    @GetMapping("/qui")
    ResponseEntity<List<QuizModel>> findAll(){
        return this.client.findAll();
    }
    @GetMapping("/for")
    List<Formation> getAllFormations(){
        return this.formationClient.getAllFormations();
    }

    @DeleteMapping("/userQuiz/{QuizId}")
    ResponseEntity<Boolean> deleteQuizUser (@PathVariable("QuizId") long QuizId){
        return this.service.deleteQuizUsers(QuizId);
    };
}
