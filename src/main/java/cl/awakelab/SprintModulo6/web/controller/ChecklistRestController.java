package cl.awakelab.SprintModulo6.web.controller;


import cl.awakelab.SprintModulo6.model.domain.dto.ChecklistDTO;
import cl.awakelab.SprintModulo6.web.service.ChecklistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistRestController {
    private final ChecklistService service;

    public ChecklistRestController(ChecklistService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ChecklistDTO>> findAll(){
        return service.findAll()
                .map(checklistDTO -> new ResponseEntity<>(checklistDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{checklistId}")
    public ResponseEntity<ChecklistDTO> findById(@PathVariable int checklistId){
        return service.findById(checklistId)
                .map(checklistDTO -> new ResponseEntity<>(checklistDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<ChecklistDTO> create(@RequestBody ChecklistDTO checklistDTO){
        return service.create(checklistDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<ChecklistDTO> update(@RequestBody ChecklistDTO checklistDTO){
        return service.update(checklistDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{checklistId}")
    public ResponseEntity<Boolean> delete(@PathVariable int checklistId){
        return new ResponseEntity<Boolean>(service.delete(checklistId), HttpStatus.OK);
    }
}
