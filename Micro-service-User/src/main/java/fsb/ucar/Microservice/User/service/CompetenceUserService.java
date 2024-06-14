package fsb.ucar.Microservice.User.service;

import fsb.ucar.Microservice.User.dto.UserCompetenceDto;
import fsb.ucar.Microservice.User.entity.Competence;
import fsb.ucar.Microservice.User.entity.CompetenceUser;
import fsb.ucar.Microservice.User.entity.UserEntity;
import fsb.ucar.Microservice.User.repository.CompUserRepository;
import fsb.ucar.Microservice.User.repository.CompetenceRepository;
import fsb.ucar.Microservice.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetenceUserService {

    @Autowired
    private CompUserRepository competenceUserRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<CompetenceUser>> getAllCompetenceUsers() {
        List<CompetenceUser> competenceUsers = competenceUserRepository.findAll();
        return new ResponseEntity<>(competenceUsers, HttpStatus.OK);
    }

    public ResponseEntity<CompetenceUser> getCompetenceUserById(Long id) {
        Optional<CompetenceUser> competenceUserOptional = competenceUserRepository.findById(id);
        if (competenceUserOptional.isPresent()) {
            return new ResponseEntity<>(competenceUserOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<CompetenceUser> createCompetenceUser(UserCompetenceDto model) {
        Optional<Competence> optionalCompetence = this.competenceRepository.findById(model.getIdCompetence());
        if (!optionalCompetence.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(model.getUserId());
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Date date =  new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Competence competence = optionalCompetence.get();
        UserEntity user = optionalUser.get();


        CompetenceUser competenceUser = new CompetenceUser();
        competenceUser.setUser(user);
        competenceUser.setSkill(competence);
        competenceUser.setLevel(model.getLevel());
        competenceUser.setValide(model.isValide());
        competenceUser.setDateCreation(sdf.format(date));

        CompetenceUser newCompetenceUser = competenceUserRepository.save(competenceUser);
        return new ResponseEntity<>(newCompetenceUser, HttpStatus.CREATED);
    }

    public ResponseEntity<CompetenceUser> updateCompetenceUser(Long id, CompetenceUser competenceUserDetails) {
        Optional<CompetenceUser> competenceUserOptional = competenceUserRepository.findById(id);
        if (competenceUserOptional.isPresent()) {
            CompetenceUser competenceUser = competenceUserOptional.get();
            if(competenceUserDetails.getUser() != null)
                competenceUser.setUser(competenceUserDetails.getUser());
            if(competenceUserDetails.getSkill() != null)
                competenceUser.setSkill(competenceUserDetails.getSkill());
            if(competenceUserDetails.getLevel() != -1)
                competenceUser.setLevel(competenceUserDetails.getLevel());
            competenceUser.setValide(competenceUserDetails.isValide());
            CompetenceUser updatedCompetenceUser = competenceUserRepository.save(competenceUser);
            return new ResponseEntity<>(updatedCompetenceUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteCompetenceUser(Long id) {
        Optional<CompetenceUser> competenceUserOptional = competenceUserRepository.findById(id);
        if (competenceUserOptional.isPresent()) {
            competenceUserRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<CompetenceUser>> getAvailableCompetencesForUser(String userId) {
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<CompetenceUser> competences = this.competenceUserRepository.findAll().stream()
                    .filter(
                            CU -> CU.getUser().equals(user)
                    )
                    .collect(Collectors.toList());
            return ResponseEntity.ok(competences);
        }
        return null;
    }


    public ResponseEntity<List<Competence>> getAvailableCompetences(String userId) {
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<Competence> list = new ArrayList<>();
            this.competenceUserRepository.findAll().forEach(
                    CU ->{
                        if(CU.getUser().equals(user))
                            list.add(CU.getSkill());

                    }
            );
            List<Competence> list1 = this.competenceRepository.findAll().stream()
                    .filter(
                            c -> ! list.contains(c)
                    )
                    .collect(Collectors.toList());
            return ResponseEntity.ok(list1);
        }
        return null;
    }
}
