package fsb.ucar.Microservice.User.controller;

import fsb.ucar.Microservice.User.dto.EquipeModel;
import fsb.ucar.Microservice.User.dto.QuizModel;
import fsb.ucar.Microservice.User.entity.Equipe;
import fsb.ucar.Microservice.User.entity.UserEntity;
import fsb.ucar.Microservice.User.service.EquipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeController {

    private EquipeService equipeService;

    @PostMapping("/create")
    public ResponseEntity<Equipe> createEquipe(@RequestBody EquipeModel model) {
        return equipeService.createEquipe(model);
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> getEquipes(){
        return this.equipeService.getEquipes();
    }

    @GetMapping("/{equipeId}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable long equipeId) {
        return equipeService.getEquipeById(equipeId);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable String name) {
        return equipeService.getEquipeByName(name);
    }

    @GetMapping("/{equipeId}/users")
    public ResponseEntity<List<UserEntity>> getUsersByEquipeId(@PathVariable long equipeId) {
        return equipeService.getUsersByEquipeId(equipeId);
    }

    @PostMapping("/{equipeId}/addUser/{userId}")
    public ResponseEntity<Equipe> addUserToEquipe(@PathVariable long equipeId, @PathVariable String userId) {
        return equipeService.addUserToEquipe(equipeId, userId);
    }

    @DeleteMapping("/{equipeId}/removeUser/{userId}")
    public ResponseEntity<Equipe> removeUserFromEquipe(@PathVariable long equipeId, @PathVariable String userId) {
        return equipeService.removeUserFromEquipe(equipeId, userId);
    }

    @PostMapping("/{equipeId}/addQuiz/{id_quiz}")
    public ResponseEntity<Object> AddQuizToUsersEquipe(@PathVariable long equipeId, @PathVariable long id_quiz) {
        return equipeService.AddQuizToUsersEquipe(equipeId, id_quiz);
    }

    @GetMapping("Quiz/{equipeId}")
    public ResponseEntity<List<QuizModel>> findAllQuizNotSubscrip(@PathVariable Long equipeId){
        return this.equipeService.findAllQuizNotSubscrip(equipeId);
    }

    @GetMapping("users/{idEquipe}")
    public ResponseEntity<List<UserEntity>> getAllUsersNotSubscrip(@PathVariable long idEquipe){
        return this.equipeService.getAllUsersNotSubscrip(idEquipe);
    }
}
