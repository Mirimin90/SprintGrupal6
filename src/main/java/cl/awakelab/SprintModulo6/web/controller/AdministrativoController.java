package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrativo")
public class AdministrativoController {
    @GetMapping
    public  String getInicio(){
        return"administrativo";
    }

    @GetMapping("/{id}")
    public String mostrarAdministrativo(@PathVariable("id") int administrativoId, Model model) {
        return "administrativo";
    }
}
