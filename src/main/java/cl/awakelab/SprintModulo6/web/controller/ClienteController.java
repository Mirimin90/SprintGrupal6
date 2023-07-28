package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping
    public  String getInicio(){
        return"cliente";
    }

    @GetMapping("/{id}")
    public String mostrarCliente(@PathVariable("id") int clienteId, Model model) {
        return "cliente";
    }
}
