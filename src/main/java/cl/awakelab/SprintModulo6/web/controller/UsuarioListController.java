package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarioList")
public class UsuarioListController {
    @GetMapping
    public  String getInicio(){
        return"usuarioList";
    }


}
