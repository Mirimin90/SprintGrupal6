package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.domain.dto.AdministrativoDTO;
import cl.awakelab.SprintModulo6.web.service.AdministrativoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrativos")
public class AdministrativoRestController {
    private final AdministrativoService service;

    public AdministrativoRestController(AdministrativoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AdministrativoDTO>>findAll(){
        return service.findAll()
                .map(AdministrativoDTO -> new ResponseEntity<>(AdministrativoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{administrativoId}")
    public ResponseEntity<AdministrativoDTO> findById(@PathVariable int administrativoId){
        return service.findById(administrativoId)
                .map(administrativoDTO -> new ResponseEntity<>(administrativoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/create")
    public ResponseEntity<AdministrativoDTO> create(@RequestBody AdministrativoDTO administrativoDTO){
        return service.create(administrativoDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<AdministrativoDTO> update(@RequestBody AdministrativoDTO administrativoDTO){
        return service.update(administrativoDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{administrativoId}")
    public ResponseEntity<Boolean> delete(@PathVariable int administrativoId){
        return new ResponseEntity<Boolean>(service.delete(administrativoId), HttpStatus.OK);
    }
}
