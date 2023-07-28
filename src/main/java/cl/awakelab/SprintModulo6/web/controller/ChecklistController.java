package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checklist")
public class ChecklistController {
    @GetMapping
    public  String getInicio(){
        return"checklist";
    }

    @GetMapping("/{id}")
    public String mostrarCheck(@PathVariable("id") int checkId, Model model) {
        return "checklist";
    }
}
