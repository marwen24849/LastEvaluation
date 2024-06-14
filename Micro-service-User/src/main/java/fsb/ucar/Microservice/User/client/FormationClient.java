package fsb.ucar.Microservice.User.client;


import fsb.ucar.Microservice.User.dto.Formation;
import fsb.ucar.Microservice.User.dto.QuizModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "formation-service", url = "http://localhost:3000")
public interface FormationClient {
    @GetMapping(value = "/formations/{id}",  consumes = "application/json")
    Formation getFormationById(@PathVariable long id);

    @GetMapping( value = "/formations",consumes = "application/json")

    List<Formation> getAllFormations();



    default ResponseEntity<Formation> fallbackForFormation(long id, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    default ResponseEntity<List<Formation>> fallbackForAllFormations(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
