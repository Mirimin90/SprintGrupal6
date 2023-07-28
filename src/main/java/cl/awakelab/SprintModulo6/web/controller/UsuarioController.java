package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")

public class UsuarioController {
    @GetMapping
    public  String getInicio(){
        return"usuario";
    }
    @GetMapping("/{id}")
    public String mostrarUsuario(@PathVariable("id") int usuarioId, Model model) {
        return "usuario";
    }
}
