package fsb.ucar.Microservice.QCM.controller;


import fsb.ucar.Microservice.QCM.dto.QuestionModel;
import fsb.ucar.Microservice.QCM.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/Question")

public class QuestionController {

    private QuestionService service;


    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuestion(@PathVariable long id){
       return this.service.getQuestionById(id);
    }

    @GetMapping("cat/{cat}")
    public ResponseEntity<Object> getQuestionCategories(@PathVariable String cat){
        return this.service.getQuestionByCategorie(cat);
    }

    @GetMapping("cat/{cat}/level/{lev}")
    public ResponseEntity<Integer> getQuestionCategorieslevel(@PathVariable("cat") String cat, @PathVariable("lev") String level){
        return this.service.CountQuestionCategorieLevel(cat, level);
    }



    @GetMapping("level/{level}")
    public ResponseEntity<Object> getQuestionLevel(@PathVariable String level){
        return this.service.getQuestionBylevel(level);
    }

    @GetMapping("/All")
    public ResponseEntity<Object> findAll(){
        return this.service.getAll();
    }

    @PostMapping("/Add")
    public ResponseEntity<Object> addQuestion(@RequestBody QuestionModel model){
        return this.service.addQuestion(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateQuestion(@RequestBody QuestionModel model, @PathVariable long id){
        return this.service.updateQuetion(model, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        return this.service.delete(id);
    }


}
