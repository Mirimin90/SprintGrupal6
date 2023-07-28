package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.domain.dto.VisitaDTO;
import cl.awakelab.SprintModulo6.web.service.VisitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaRestController {
    private final VisitaService service;

    public VisitaRestController(VisitaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VisitaDTO>> findAll(){
        return service.findAll()
                .map(visitaDTO -> new ResponseEntity<>(visitaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{visitaId}")
    public ResponseEntity<VisitaDTO> findById(@PathVariable int visitaId){
        return service.findById(visitaId)
                .map(visitaDTO -> new ResponseEntity<>(visitaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<VisitaDTO> create(@RequestBody VisitaDTO visitaDTO){
        return service.create(visitaDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<VisitaDTO> update(@RequestBody VisitaDTO visitaDTO){
        return service.update(visitaDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{pagoId}")
    public ResponseEntity<Boolean> delete(@PathVariable int visitaId){
        return new ResponseEntity<Boolean>(service.delete(visitaId), HttpStatus.OK);
    }

}