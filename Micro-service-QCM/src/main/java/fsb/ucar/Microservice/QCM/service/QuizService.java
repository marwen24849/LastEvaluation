package fsb.ucar.Microservice.QCM.service;


import fsb.ucar.Microservice.QCM.client.BadgeClient;
import fsb.ucar.Microservice.QCM.client.UserClient;
import fsb.ucar.Microservice.QCM.dto.Badge;
import fsb.ucar.Microservice.QCM.dto.QuestionModel;
import fsb.ucar.Microservice.QCM.dto.QuizModel;
import fsb.ucar.Microservice.QCM.entity.Question;
import fsb.ucar.Microservice.QCM.entity.Quiz;
import fsb.ucar.Microservice.QCM.entity.Response;
import fsb.ucar.Microservice.QCM.repository.QuestionRepository;
import fsb.ucar.Microservice.QCM.repository.QuizRepository;
import fsb.ucar.Microservice.QCM.repository.ResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizRepository quizRepository;

    private QuestionRepository questionRepository;

    private ResponseRepository responseRepository;

    private BadgeClient badgeclient;

    private UserClient userClient;


    public ResponseEntity<Quiz> getQuizById(Long id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()) {
            return new ResponseEntity<>(quizOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> addQuiz(QuizModel model) {
        Quiz quiz = new Quiz();
        quiz.setTitle(model.getTitle());
        quiz.setCategory(model.getCategory());
        quiz.setMinimumSuccessPercentage(model.getMinimumSuccessPercentage());
        quiz.setDifficultylevel(model.getDifficultylevel());
        List<Long> idQuestion = (List<Long>) this.questionRepository.findRandomQuestionsByCategoryAndDifficultyLevel(model.getCategory(), model.getDifficultylevel(),model.getNbQuestion());
        for(Long id : idQuestion){
            quiz.getQuestions().add(
                    this.questionRepository
                            .findById(id)
                            .get()
            );
        }

        if(!model.getName().isEmpty()){
            Badge badge = new Badge();
            badge.setType(model.getType());
            badge.setName(model.getName());
            badge.setDescription(model.getDescription());
            long id_badge = badgeclient.addBadge(badge).getBody();
            quiz.setBadge(true);
            quiz.setId_badge(id_badge);
        }

        return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateQuiz(Long id, QuizModel model) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            quiz.setTitle(model.getTitle());
            quiz.setMinimumSuccessPercentage(model.getMinimumSuccessPercentage());
            return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Quiz non trouvé", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteQuiz(Long id,List<Question> questions) {
        if (quizRepository.existsById(id)) {
            boolean test = Boolean.TRUE.equals(this.userClient.deleteQuizUser(id).getBody());
            if(test){
                if(!questions.isEmpty()){
                    questions.forEach(question -> deleteQuestionFromQuiz(id, question.getId()));
                }
                List<Response> responses = this.responseRepository.findResponseByQuiz(this.quizRepository.findById(id).get());
                responses.forEach(response -> this.responseRepository.deleteById(response.getId()));
                quizRepository.deleteById(id);
                return new ResponseEntity<>("Suppression terminée", HttpStatus.OK);
            }
        }
            return new ResponseEntity<>("Quiz non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteQuestionFromQuiz(Long id_Quiz,Long id_Question ) {
        int index = 0;
        if (quizRepository.existsById(id_Quiz)) {
            if (questionRepository.existsById(id_Question)) {
                Quiz quiz = this.quizRepository.findById(id_Quiz).get();
                List<Question> questions = quiz.getQuestions();
                for(Question q : questions){
                    if(q.getId() == id_Question){
                        questions.remove(index);
                        quiz.setQuestions(questions);
                        this.quizRepository.save(quiz);
                        return  new ResponseEntity<>("Suppression from list Quiz terminée", HttpStatus.OK);
                    }
                    index = index +1;
                }
            }else{
                return new ResponseEntity<>("Question non trouvé", HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<>("Quiz non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> ajoutQuestionsQuiz(Long id, Long id_question){
        Optional<Quiz> quiz = this.quizRepository.findById(id);
        Optional<Question> question = this.questionRepository.findById(id_question);
        if(quiz.isPresent()){
            quiz.get().getQuestions().add(question.get());
            this.quizRepository.save(quiz.get());
            return  new ResponseEntity<>("Ajout Terminer", HttpStatus.OK);
        }
        return new ResponseEntity<>("Quiz non trouvé", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getQuizByCategorie(String Categories){
        List<Quiz> quiz = this.quizRepository.findByCategory(Categories);
        if(! quiz.isEmpty()){
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }
        return new ResponseEntity<>("Question non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> QuizcatLevel(String Categories, String level){
        List<Quiz> quiz = this.quizRepository.findByCategoryDifficultylevel(Categories, level);
        if(! quiz.isEmpty()){
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }
        return new ResponseEntity<>("Question non trouvé",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Question>>findQuestionNotExist(long idquiz){
        Optional<Quiz> quizOptional = this.quizRepository.findById(idquiz);

        if(quizOptional.isPresent()){
            Quiz quiz = quizOptional.get();
            List<Question> questions = this.questionRepository.getQuestionsByCategoryAndDifficultyLevel(quiz.getCategory(), quiz.getDifficultylevel());
            if(! questions.isEmpty()){
                List<Question> list = questions.stream().filter(q -> !quiz.getQuestions().contains(q)).collect(Collectors.toList());

                return ResponseEntity.ok(list);
            }
        }
        return null;
    }





}
