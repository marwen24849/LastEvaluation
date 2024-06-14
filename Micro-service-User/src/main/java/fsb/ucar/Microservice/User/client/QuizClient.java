package fsb.ucar.Microservice.User.client;


import fsb.ucar.Microservice.User.dto.QuizModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "MICRO-SERVICE-QCM")
public interface QuizClient {

    @GetMapping("/Quiz/{id}")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<QuizModel> getQuiz(@PathVariable long id);

    @GetMapping("/Quiz/Cat/{cat}")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<Object> getQuizCat(@PathVariable String cat);

    @GetMapping("/Quiz/All")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForAllQuiz")
    ResponseEntity<List<QuizModel>> findAll();

    @PostMapping("/Quiz/All")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<Object> addQuiz(@RequestBody QuizModel model);

    @PutMapping("/Quiz/{id}")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<Object> updateQuiz(@PathVariable long id, @RequestBody QuizModel model);

    @PutMapping("/Quiz/{id}/AddQuestion/{id_Question}")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<Object> updateQuestionQuiz(@PathVariable("id") long id, @PathVariable("id_Question") long id_Question);

    @DeleteMapping("/Quiz/{id}")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<Object> deleteQuiz(@PathVariable long id);

    @DeleteMapping("/Quiz/{id_Quiz}/Question/{id_Question}")
    @CircuitBreaker(name = "qcmService", fallbackMethod = "fallbackForGetQuiz")
    ResponseEntity<Object> deleteQuestionFromQuiz(@PathVariable("id_Quiz") long id_Quiz, @PathVariable("id_Question") long id_Question);

    default ResponseEntity<QuizModel> fallbackForGetQuiz(long id, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    default ResponseEntity<List<QuizModel>> fallbackForAllQuiz(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }





}
