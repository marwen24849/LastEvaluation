package fsb.ucar.Microservice.User.controller;


import fsb.ucar.Microservice.User.client.CompetenceClient;
import fsb.ucar.Microservice.User.client.FormationClient;
import fsb.ucar.Microservice.User.dto.Competence;
import fsb.ucar.Microservice.User.dto.Formation;
import fsb.ucar.Microservice.User.dto.QuizModel;
import fsb.ucar.Microservice.User.entity.UserEntity;
import fsb.ucar.Microservice.User.service.ColloborateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colloborateurs")
@AllArgsConstructor
public class ColloborateurController {

    private ColloborateurService colloborateurService;
    private FormationClient formationClient;

    private CompetenceClient competenceClient;

    @PostMapping("/inscriptionFormation")
    public ResponseEntity<UserEntity> inscriptionFormation(@RequestParam long id_formation, @RequestParam String id_user) {
        return colloborateurService.inscriptionFormation(id_formation, id_user);
    }

    @GetMapping("/formations/{userId}")
    public ResponseEntity<List<Formation>> getFormationsByUserId(@PathVariable String userId) {
        return colloborateurService.getFormationsByUserId(userId);
    }

    @GetMapping("/Quiz/{username}")
    public ResponseEntity<List<QuizModel>> getQuizUserPasser(@PathVariable String username) {
        return colloborateurService.getQuizUserPasser(username);
    }



    @GetMapping("/formations/available/{userId}")
    public ResponseEntity<List<Formation>> getAllFormationsExceptUserSubscribed(@PathVariable String userId) {
        return colloborateurService.getAllFormationsExceptUserSubscribed(userId);
    }

    @GetMapping("QuizUser/{id}")
    public ResponseEntity<List<QuizModel>> findQuizUser(@PathVariable String id){
        return this.colloborateurService.getAllQuizUser(id);
    }

    @GetMapping("/{username}/badges")
    public ResponseEntity<Object> allBadgeUser(@PathVariable String username){
        return this.colloborateurService.AllBadgeUser(username);
    }

    @GetMapping("/for/{id}")
    public Formation getAllFormations(@PathVariable long id){
        return this.formationClient.getFormationById(id);
    }

    @GetMapping("/cometence")
    public List<Competence> getAllcometence (){
        return this.competenceClient.getAllCompetences();
    }


    @GetMapping("/{username}/Quiz/{id}")
    public ResponseEntity<Object> updateStatuQuiz(@PathVariable("username") String username,@PathVariable("id") long id_Quiz){

        return this.colloborateurService.updateStatuQuiz(username, id_Quiz);

    }

    @PostMapping("/ajouter-competence")
    public ResponseEntity<Object> ajouterCompetence(@RequestParam String username, @RequestParam long id) {
        return colloborateurService.ajouterCompetence(username, id);
    }

    @GetMapping("/competences/{username}")
    public ResponseEntity<List<Competence>> getCompetencesByUsername(@PathVariable String username) {
        return colloborateurService.getCompetencesByUsername(username);
    }

    @GetMapping("/competences-disponibles/{username}")
    public ResponseEntity<Object> getAllCompetencesExceptUserSubscribed(@PathVariable String username) {
        return colloborateurService.getAllCometenceExceptUserSubscribed(username);
    }

    @GetMapping("/{username}/Badge/{id}")
    public ResponseEntity<Object> AddBadgeUser(@PathVariable("username") String username,@PathVariable("id") long id){

        return this.colloborateurService.addBadgeUser(username, id);
    }

    @GetMapping("/{username}/Quiz/{id_q}/Response/{id_r}")
    public ResponseEntity<Object> QuizResponse(@PathVariable("username") String username,@PathVariable("id_q") long id_Quiz, @PathVariable("id_r") String id_r){

        return this.colloborateurService.addQuizResponse(username, id_r, id_Quiz);

    }

    @GetMapping("/name/{username}/Quiz/{id}")
    public ResponseEntity<String> idResponse(@PathVariable("username") String username,@PathVariable("id") long id_Quiz){

        return this.colloborateurService.idResponse(username, id_Quiz);

    }
}
