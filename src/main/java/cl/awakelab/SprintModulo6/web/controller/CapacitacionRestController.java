package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.domain.dto.CapacitacionDTO;
import cl.awakelab.SprintModulo6.web.service.CapacitacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/listadoCapacitaciones")
public class CapacitacionRestController {
    private final CapacitacionService service;

    public CapacitacionRestController(CapacitacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CapacitacionDTO>> findAll(){
        return service.findAll()
                .map(capacitacionDTO -> new ResponseEntity<>(capacitacionDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{capacitacionId}")
    public ResponseEntity<CapacitacionDTO> findById(@PathVariable int capacitacionId){
        return service.findById(capacitacionId)
                .map(capacitacionDTO -> new ResponseEntity<>(capacitacionDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<CapacitacionDTO> create(@RequestBody CapacitacionDTO capacitacionDTO){
        return service.create(capacitacionDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<CapacitacionDTO> update(@RequestBody CapacitacionDTO capacitacionDTO){
        return service.update(capacitacionDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{capacitacionId}")
    public ResponseEntity<Boolean> delete(@PathVariable int capacitacionId){
        return new ResponseEntity<Boolean>(service.delete(capacitacionId), HttpStatus.OK);
    }

}

