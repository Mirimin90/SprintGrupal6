package cl.awakelab.SprintModulo6.web.controller;

import cl.awakelab.SprintModulo6.model.persistence.entity.Capacitacion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/capacitacion")
public class CapacitacionController {
    @GetMapping
    public  String getInicio(){
        return"capacitacion";
    }

    @GetMapping("/{id}")
    public String mostrarCapacitacion(@PathVariable("id") int capacitacionId, Model model) {
        return "capacitacion";
    }

}
