package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesional")
public class ProfesionalController {

    @GetMapping
    public  String getInicio(){
        return"profesional";
    }

    @GetMapping("/{id}")
    public String mostrarProfesional(@PathVariable("id") int profesionalId, Model model) {
        return "profesional";
    }
}
