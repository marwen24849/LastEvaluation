package fsb.ucar.Microservice.User.service;


import com.fasterxml.jackson.databind.node.LongNode;
import fsb.ucar.Microservice.User.client.BadgeClient;
import fsb.ucar.Microservice.User.client.CompetenceClient;
import fsb.ucar.Microservice.User.client.FormationClient;
import fsb.ucar.Microservice.User.client.QuizClient;
import fsb.ucar.Microservice.User.dto.Badge;
import fsb.ucar.Microservice.User.dto.Competence;
import fsb.ucar.Microservice.User.dto.Formation;
import fsb.ucar.Microservice.User.dto.QuizModel;
import fsb.ucar.Microservice.User.entity.UserEntity;
import fsb.ucar.Microservice.User.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColloborateurService {

    private FormationClient formationClient;
    private QuizClient quizClient;
    private UserRepository userRepository;
    private CompetenceClient competenceClient;
    private BadgeClient badgeClient;
    private EmailService emailService;

    public ResponseEntity<UserEntity> inscriptionFormation(long id_formation, String username){
        Formation formation = this.formationClient.getFormationById(id_formation);
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            if(user.getId_formations() == null){
                user.setId_formations(new ArrayList<Long>());
            }
            user.getId_formations().add(id_formation);
            return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.save(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<List<Formation>> getFormationsByUserId(String name) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<Formation> formations = this.formationClient.getAllFormations().stream().filter(
                    f -> user.getId_formations().contains(f.getId())
            ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(formations);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }




    public ResponseEntity<List<Formation>> getAllFormationsExceptUserSubscribed(String name) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<Formation> allFormations = formationClient.getAllFormations();
            List<Long> subscribedFormationIds = user.getId_formations();
            List<Formation> availableFormations;
            if (subscribedFormationIds != null) {
                availableFormations = allFormations.stream()
                        .filter(formation -> !subscribedFormationIds.contains(formation.getId()))
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(availableFormations);
            } else {
                availableFormations = allFormations;
                return ResponseEntity.status(HttpStatus.OK).body(availableFormations);
            }


        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    public ResponseEntity<List<QuizModel>> getAllQuizUser(String username){
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            List<Long> id_quiz = user.getId_quiz().stream().filter(
                    id-> !user.getId_quiz_Passer().contains(id)
            ).collect(Collectors.toList());

            for(long id : id_quiz){
                QuizModel model = this.quizClient.getQuiz(id).getBody();
                if(model != null)
                    user.getQuiz().add(model);
            }

            return ResponseEntity.ok().body(user.getQuiz());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<List<QuizModel>> getQuizUserPasser(String username){
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            for(long id : user.getId_quiz_Passer()){
                QuizModel model = this.quizClient.getQuiz(id).getBody();
                if(model != null)
                    user.getQuiz().add(model);
            }
            return ResponseEntity.ok().body(user.getQuiz());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<Object> updateStatuQuiz(String username, long id_Quiz){
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            if(user.getId_quiz_Passer() == null)
                user.setId_quiz_Passer(new ArrayList<>());
            user.getId_quiz_Passer().add(id_Quiz);
            return ResponseEntity.ok().body(this.userRepository.save(user));
        }
        return null;
    }


    public ResponseEntity<Object> ajouterCompetence(String username, long id){
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            Competence competence = this.competenceClient.getCompetenceById(id);
            if(user.getId_competences() == null)
                user.setId_competences(new ArrayList<>());
            user.getId_competences().add(id);
            return ResponseEntity.ok().body(this.userRepository.save(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    public ResponseEntity<Object> getAllCometenceExceptUserSubscribed(String name) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<Competence> allCompetences = competenceClient.getAllCompetences();
            List<Long> subscribedCompetenceIds = user.getId_competences();
            List<Competence> availableCompetences;
            if (subscribedCompetenceIds != null) {
                availableCompetences = allCompetences.stream()
                        .filter(competence -> !subscribedCompetenceIds.contains(competence.getId()))
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(availableCompetences);
            } else {
                availableCompetences = allCompetences;
                return ResponseEntity.status(HttpStatus.OK).body(availableCompetences);
            }


        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<Competence>> getCompetencesByUsername(String name) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<Competence> competences = new ArrayList<>();
            if(user.getId_competences() != null )
                competences = this.competenceClient.getAllCompetences().stream().filter(
                        c -> user.getId_competences().contains(c.getId())
                ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(competences);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<Object> addBadgeUser(String username, long id){

        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            if(user.getId_Badge()== null)
                user.setId_Badge(new ArrayList<>());
            user.getId_Badge().add(id);
            Badge badge = this.badgeClient.getBadgeById(id).getBody();
            String to = user.getEmail();
            String subject = "Nouveau badge attribué";
            String body = "Bonjour " + user.getFirstName() + ",\n\n"
                    + "Nous avons le plaisir de vous informer qu'un nouveau badge vous a été attribué sur la plateforme.\n\n"
                    + "Badge attribué: " + badge.getName() + "\n"
                    + "Description: " + badge.getDescription() + "\n\n"
                    + "Félicitations pour cette réalisation !\n\n"
                    + "Cordialement,\n"
                    + "L'équipe Quiz";

            emailService.sendMail(to, null, subject, body);
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    public ResponseEntity<Object> AllBadgeUser(String username){

        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<Badge> badges = new ArrayList<>();
            if(user.getId_Badge() != null)
                user.getId_Badge().forEach(id -> badges.add(this.badgeClient.getBadgeById(id).getBody()));
            return ResponseEntity.status(HttpStatus.OK).body(badges);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    public ResponseEntity<Object> addQuizResponse(String username, String id_res, long id_quiz){
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.getQuizResponses().put(id_quiz, id_res);
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<String> idResponse(String username, long id_quiz){
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            String id_r = user.getQuizResponses().get(id_quiz);
            if(id_r != null)
                return ResponseEntity.status(HttpStatus.OK).body(id_r);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }





}
