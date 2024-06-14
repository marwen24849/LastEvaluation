package fsb.ucar.Microservice.User.controller;

import fsb.ucar.Microservice.User.entity.Competence;
import fsb.ucar.Microservice.User.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Competence>> getAllSkills() {
        return skillService.getAllCompetences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competence> getSkillById(@PathVariable("id") Long id) {
        return skillService.getCompetenceById(id);
    }

    @PostMapping
    public ResponseEntity<Competence> createSkill(@RequestBody Competence competence) {
        return skillService.createCompetence(competence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competence> updateSkill(@PathVariable("id") Long id, @RequestBody Competence competenceDetails) {
        return skillService.updateCompetence(id, competenceDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable("id") Long id) {
        return skillService.deleteCompetence(id);
    }

    @GetMapping("/nom/{name}")
    public ResponseEntity<List<Competence>> searchSkillsByName(@PathVariable("name") String name) {
        return skillService.getCompetenceByName(name);
    }
}
