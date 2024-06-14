package fsb.ucar.Microservice.User.client;



import fsb.ucar.Microservice.User.dto.Competence;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "competences-service", url = "http://localhost:3000")
public interface CompetenceClient {


    @GetMapping(value = "/competences/{id}",  consumes = "application/json")
    Competence getCompetenceById(@PathVariable long id);

    @GetMapping( value = "/competences",consumes = "application/json")
    List<Competence> getAllCompetences();
}
