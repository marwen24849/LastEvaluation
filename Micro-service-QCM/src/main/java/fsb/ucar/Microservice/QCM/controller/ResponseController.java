package fsb.ucar.Microservice.QCM.controller;

import fsb.ucar.Microservice.QCM.dto.ResponseModel;
import fsb.ucar.Microservice.QCM.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/Response")
public class ResponseController {
    private ResponseService responseService;


    @GetMapping("/All")
    public ResponseEntity<Object> getAllResponse() {
        return this.responseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        return this.responseService.findById(id);
    }


    @PostMapping("/Add")
    public ResponseEntity<Object> addResponse(@RequestBody ResponseModel model) {
        return this.responseService.addResponse(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResponse(@PathVariable String id, @RequestBody ResponseModel model) {
        return this.responseService.updateResponse(id, model);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        return this.responseService.deleteResponse(id);
    }

}