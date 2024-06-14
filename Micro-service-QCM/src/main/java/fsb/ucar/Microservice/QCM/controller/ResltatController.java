package fsb.ucar.Microservice.QCM.controller;

import fsb.ucar.Microservice.QCM.dto.ResponseModel;
import fsb.ucar.Microservice.QCM.dto.ResultatModel;
import fsb.ucar.Microservice.QCM.entity.Resultat;
import fsb.ucar.Microservice.QCM.service.ResultatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Resultat")
@AllArgsConstructor
public class ResltatController {

    private ResultatService resultatService;

    @GetMapping("/All")
    public ResponseEntity<Object> getAllResponse() {
        return this.resultatService.getAllResultats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        return this.resultatService.getResultatById(id);
    }


    @PostMapping("/Add")
    public ResponseEntity<Object> addResponse(@RequestBody Resultat model) {
        return this.resultatService.addResultat(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResponse(@PathVariable long id, @RequestBody ResultatModel model) {
        return this.resultatService.updateResultat(model, id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id) {
        return this.resultatService.deleteResultat(id);
    }

    @GetMapping("/ResultatQuiz/{id}")
    public ResponseEntity<Object> getQuizById(@PathVariable long id) {
        return this.resultatService.findByIdQuiz(id);
    }

}
