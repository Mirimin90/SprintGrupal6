package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.domain.dto.PagoDTO;
import cl.awakelab.SprintModulo6.web.service.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoRestController {
    private final PagoService service;

    public PagoRestController(PagoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> findAll(){
        return service.findAll()
                .map(pagoDTO -> new ResponseEntity<>(pagoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{pagoId}")
    public ResponseEntity<PagoDTO> findById(@PathVariable int pagoId){
        return service.findById(pagoId)
                .map(pagoDTO -> new ResponseEntity<>(pagoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<PagoDTO> create(@RequestBody PagoDTO pagoDTO){
        return service.create(pagoDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<PagoDTO> update(@RequestBody PagoDTO pagoDTO){
        return service.update(pagoDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{pagoId}")
    public ResponseEntity<Boolean> delete(@PathVariable int pagoId){
        return new ResponseEntity<Boolean>(service.delete(pagoId), HttpStatus.OK);
    }

}