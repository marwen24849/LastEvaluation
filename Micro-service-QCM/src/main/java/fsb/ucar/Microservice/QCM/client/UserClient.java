package fsb.ucar.Microservice.QCM.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MICRO-SERVICE-USER")
public interface UserClient {
    @DeleteMapping("/api/integration/monter/userQuiz/{QuizId}")
    ResponseEntity<Boolean> deleteQuizUser (@PathVariable("QuizId") long QuizId);
}
