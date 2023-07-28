package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.domain.dto.UsuarioDTO;
import cl.awakelab.SprintModulo6.web.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    private final UsuarioService service;

    public UsuarioRestController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return service.findAll()
                .map(usuarioDTO -> new ResponseEntity<>(usuarioDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable int usuarioId){
        return service.findById(usuarioId)
                .map(usuarioDTO -> new ResponseEntity<>(usuarioDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO){
        return service.create(usuarioDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO){
        return service.update(usuarioDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{usuarioId}")
    public ResponseEntity<Boolean> delete(@PathVariable int usuarioId){
        return new ResponseEntity<Boolean>(service.delete(usuarioId), HttpStatus.OK);
    }
}
