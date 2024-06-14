package fsb.ucar.Microservice.User.service;

import fsb.ucar.Microservice.User.entity.Competence;
import fsb.ucar.Microservice.User.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public ResponseEntity<List<Competence>> getAllCompetences() {
        List<Competence> competences = competenceRepository.findAll();
        return new ResponseEntity<>(competences, HttpStatus.OK);
    }

    public ResponseEntity<Competence> getCompetenceById(Long id) {
        Optional<Competence> CompetenceOptional = competenceRepository.findById(id);
        if (CompetenceOptional.isPresent()) {
            return new ResponseEntity<>(CompetenceOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Competence> createCompetence(Competence Competence) {
        Competence newCompetence = competenceRepository.save(Competence);
        return new ResponseEntity<>(newCompetence, HttpStatus.CREATED);
    }

    public ResponseEntity<Competence> updateCompetence(Long id, Competence CompetenceDetails) {
        Optional<Competence> CompetenceOptional = competenceRepository.findById(id);
        if (CompetenceOptional.isPresent()) {
            Competence Competence = CompetenceOptional.get();
            Competence.setName(CompetenceDetails.getName());
            Competence.setDescription(CompetenceDetails.getDescription());
            Competence updatedCompetence = competenceRepository.save(Competence);
            return new ResponseEntity<>(updatedCompetence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteCompetence(Long id) {
        Optional<Competence> CompetenceOptional = competenceRepository.findById(id);
        if (CompetenceOptional.isPresent()) {
            competenceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Competence>> getCompetenceByName(String name) {
        List<Competence> competences = competenceRepository.findByNameContaining(name);
        return ResponseEntity.ok().body(competences);
    }
}

