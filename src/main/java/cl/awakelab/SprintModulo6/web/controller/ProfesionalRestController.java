package cl.awakelab.SprintModulo6.web.controller;


import cl.awakelab.SprintModulo6.model.domain.dto.ProfesionalDTO;
import cl.awakelab.SprintModulo6.web.service.ProfesionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalRestController {
    private final ProfesionalService service;

    public ProfesionalRestController(ProfesionalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfesionalDTO>> findAll(){
        return service.findAll()
                .map(profesionalDTO -> new ResponseEntity<>(profesionalDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{profesionalId}")
    public ResponseEntity<ProfesionalDTO> findById(@PathVariable int profesionalId){
        return service.findById(profesionalId)
                .map(profesionalDTO -> new ResponseEntity<>(profesionalDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/create")
    public ResponseEntity<ProfesionalDTO> create(@RequestBody ProfesionalDTO profesionalDTO){
        return service.create(profesionalDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<ProfesionalDTO> update(@RequestBody ProfesionalDTO profesionalDTO){
        return service.update(profesionalDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{profesionalId}")
    public ResponseEntity<Boolean> delete(@PathVariable int profesionalId){
        return new ResponseEntity<Boolean>(service.delete(profesionalId), HttpStatus.OK);
    }
}
