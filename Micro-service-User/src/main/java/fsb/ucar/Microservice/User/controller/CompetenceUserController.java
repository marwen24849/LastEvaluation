package fsb.ucar.Microservice.User.controller;

import fsb.ucar.Microservice.User.dto.UserCompetenceDto;
import fsb.ucar.Microservice.User.entity.Competence;
import fsb.ucar.Microservice.User.entity.CompetenceUser;
import fsb.ucar.Microservice.User.service.CompetenceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competence-users")
public class CompetenceUserController {

    @Autowired
    private CompetenceUserService competenceUserService;

    @GetMapping
    public ResponseEntity<List<CompetenceUser>> getAllCompetenceUsers() {
        return competenceUserService.getAllCompetenceUsers();
    }

    @GetMapping("/available/{userId}")
    public ResponseEntity<List<CompetenceUser>> getAvailableCompetencesForUser(@PathVariable("userId") String userId) {
        return competenceUserService.getAvailableCompetencesForUser(userId);
    }

    @GetMapping("/DispoCom/{userId}")
    public ResponseEntity<List<Competence>> getAvailableCompetences(@PathVariable("userId") String userId) {
        return competenceUserService.getAvailableCompetences(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceUser> getCompetenceUserById(@PathVariable("id") Long id) {
        return competenceUserService.getCompetenceUserById(id);
    }

    @PostMapping
    public ResponseEntity<CompetenceUser> createCompetenceUser(@RequestBody UserCompetenceDto competenceUser) {
        return competenceUserService.createCompetenceUser(competenceUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenceUser> updateCompetenceUser(@PathVariable("id") Long id, @RequestBody CompetenceUser competenceUserDetails) {
        return competenceUserService.updateCompetenceUser(id, competenceUserDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompetenceUser(@PathVariable("id") Long id) {
        return competenceUserService.deleteCompetenceUser(id);
    }
}
