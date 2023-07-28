package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.domain.dto.ClienteDTO;
import cl.awakelab.SprintModulo6.web.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    private final ClienteService service;

    public ClienteRestController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return service.findAll()
                .map(clienteDTO -> new ResponseEntity<>(clienteDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable int clienteId){
        return service.findById(clienteId)
                .map(clienteDTO -> new ResponseEntity<>(clienteDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/create")
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO){
        return service.create(clienteDTO)
                .map(t-> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    }

    @PatchMapping("/update")
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO clienteDTO){
        return service.update(clienteDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<Boolean> delete(@PathVariable int clienteId){
        return new ResponseEntity<Boolean>(service.delete(clienteId), HttpStatus.OK);
    }
}
