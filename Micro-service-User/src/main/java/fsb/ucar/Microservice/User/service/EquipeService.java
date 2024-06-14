package fsb.ucar.Microservice.User.service;


import fsb.ucar.Microservice.User.client.QuizClient;
import fsb.ucar.Microservice.User.dto.EquipeModel;
import fsb.ucar.Microservice.User.dto.QuizModel;
import fsb.ucar.Microservice.User.entity.Equipe;
import fsb.ucar.Microservice.User.entity.UserEntity;
import fsb.ucar.Microservice.User.repository.EquipeRepository;
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
public class EquipeService {

    private EquipeRepository equipeRepository;
    private UserRepository userRepository;
    private MentorService mentorService;
    private QuizClient quizClient;
    private EmailService emailService;

    public ResponseEntity<Equipe> createEquipe(EquipeModel model) {
        Equipe equipe = new Equipe();
        equipe.setName(model.getName());
        equipe.setDepartment(model.getDepartment());
        equipe.setUsers(model.getUsers());
        return ResponseEntity.status(HttpStatus.CREATED).body(equipeRepository.save(equipe));
    }

    public ResponseEntity<List<Equipe>> getEquipes(){
        return ResponseEntity.ok(this.equipeRepository.findAll());
    }


    public ResponseEntity<Equipe> getEquipeById(long equipeId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        return optionalEquipe.map(equipe -> ResponseEntity.status(HttpStatus.OK).body(equipe))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Equipe> getEquipeByName(String name) {
        Optional<Equipe> optionalEquipe = equipeRepository.findByName(name);
        return optionalEquipe.map(equipe -> ResponseEntity.status(HttpStatus.OK).body(equipe))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<List<UserEntity>> getUsersByEquipeId(long equipeId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        return optionalEquipe.map(equipe -> ResponseEntity.status(HttpStatus.OK).body(equipe.getUsers()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Equipe> addUserToEquipe(long equipeId, String userId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalEquipe.isPresent() && optionalUser.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            UserEntity user = optionalUser.get();

            if (equipe.getUsers() == null) {
                equipe.setUsers(new ArrayList<>());
            }

            equipe.getUsers().add(user);
            equipeRepository.save(equipe);

            String to = user.getEmail();
            String subject = "Vous avez été ajouté à une équipe";
            String body = "Bonjour " + user.getFirstName() + ",\n\n"
                    + "Vous avez été ajouté à l'équipe " + equipe.getName() + ". \n\n"
                    + "Cordialement,\n"
                    + "RH";

            this.emailService.sendMail(to,null,subject,body);


            return ResponseEntity.status(HttpStatus.OK).body(equipe);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<Equipe> removeUserFromEquipe(long equipeId, String userId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalEquipe.isPresent() && optionalUser.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            UserEntity user = optionalUser.get();

            if (equipe.getUsers() != null) {
                equipe.getUsers().remove(user);
                equipeRepository.save(equipe);
                return ResponseEntity.status(HttpStatus.OK).body(equipe);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<Object> AddQuizToUsersEquipe(long equipeId, long id_quiz){
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        if(optionalEquipe.isPresent()){
            Equipe equipe = optionalEquipe.get();
            if(equipe.getId_Quiz() == null)
                equipe.setId_Quiz(new ArrayList<>());
            equipe.getId_Quiz().add(id_quiz);
            equipe.getUsers().forEach(user -> mentorService.AffectQuizUser(id_quiz, user.getId()));
            return ResponseEntity.status(HttpStatus.OK).body(equipe);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<List<QuizModel>> findAllQuizNotSubscrip(Long equipeId){
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        if(optionalEquipe.isPresent()){
            Equipe equipe = optionalEquipe.get();
            List<QuizModel> list = this.quizClient.findAll().getBody().stream().filter(
                    quizs -> !equipe.getId_Quiz().contains(quizs.getId())
            ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<List<UserEntity>> getAllUsersNotSubscrip(long idEquipe){
        Optional<Equipe> optionalEquipe = this.equipeRepository.findById(idEquipe);
        if(optionalEquipe.isPresent()){
            Equipe equipe = optionalEquipe.get();
            List<UserEntity> list = this.userRepository.findAll().stream()
                    .filter(
                            user -> ! equipe.getUsers().contains(user)
                    )
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
