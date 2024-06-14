package fsb.ucar.Microservice.User.service;

import fsb.ucar.Microservice.User.client.QuizClient;
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
public class MentorService {

    private QuizClient quizClient;
    private UserRepository userRepository;
    private EmailService emailService;

    public ResponseEntity<QuizModel> AffectQuizUser(Long id_quiz, String id_user){
        ResponseEntity<QuizModel> responseEntity = this.quizClient.getQuiz(id_quiz);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            QuizModel quizModel = (QuizModel) responseEntity.getBody();
            Optional<UserEntity> optionalUser = this.userRepository.findById(id_user);
            if(optionalUser.isPresent()){
                UserEntity user = optionalUser.get();
                user.getQuiz().add(quizModel);
                if (user.getId_quiz() == null) {
                    user.setId_quiz(new ArrayList<>());
                }
                user.getId_quiz().add(id_quiz);
                this.userRepository.save(user);
                String to = user.getEmail();
                String subject = "Nouveau Quiz disponible";
                String body = "Bonjour "+user.getFirstName()+",\n\n"
                        + "Nous avons le plaisir de vous informer qu'un nouveau quiz est disponible sur la plateforme.\n\n"
                        + "Titre du quiz: " + quizModel.getTitle() + "\n"
                        + "Catégorie: " + quizModel.getCategory() + "\n"
                        + "Niveau de difficulté: " + quizModel.getDifficultylevel() + "\n"
                        + "Pourcentage de réussite minimum: " + quizModel.getMinimumSuccessPercentage() + "\n\n"
                        + "Veuillez vous connecter à la plateforme pour passer ce quiz.\n\n"
                        + "Cordialement,\n"
                        + "L'équipe Quiz";

                emailService.sendMail(to, null, subject, body);
                return new ResponseEntity<>(quizModel, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<QuizModel> getQuizById(Long id){
        return this.quizClient.getQuiz(id);
    }

    public ResponseEntity<Object> deleteQuizFromUser(long id_quiz, String id_user){
        Optional<UserEntity> optionalUser = this.userRepository.findById(id_user);
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            boolean test = user.getId_quiz().remove(id_quiz);
            if(test){
                QuizModel model = this.getQuizById(id_quiz).getBody();
                user.getQuiz().remove(model);
                return new ResponseEntity<>(this.userRepository.save(user), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("Not Found ", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<QuizModel>> getAllQuizExceptUserSubscribed(String id_user){
        Optional<UserEntity> optionalUser = this.userRepository.findById(id_user);
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(this.quizClient.findAll().getBody().stream().filter(
                    quiz -> !optionalUser.get().getId_quiz().contains(quiz.getId())
            ).collect(Collectors.toList()));
        }
        return null;
    }

    public void setLevelCollo(String id_user , String level, int yersExp){
        Optional<UserEntity> optionalUser = this.userRepository.findById(id_user);
        if(optionalUser.isPresent()){
            optionalUser.get().setLevel(level);
            optionalUser.get().setYearsexperience(yersExp);
            this.userRepository.save(optionalUser.get());
        }

    }


    public ResponseEntity<Boolean> deleteQuizUsers(Long idQuiz){
        List<UserEntity> users = this.userRepository.findAll();
        users.forEach(user ->
        {
            user.getId_quiz().remove(idQuiz);
            user.getQuizResponses().remove(idQuiz);
            user.getId_quiz_Passer().remove(idQuiz);
            this.userRepository.save(user);
        });
        return ResponseEntity.ok(Boolean.TRUE);
    }



}
