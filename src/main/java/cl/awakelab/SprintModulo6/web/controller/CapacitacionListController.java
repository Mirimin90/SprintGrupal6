package cl.awakelab.SprintModulo6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/capacitacionList")
public class CapacitacionListController {
    @GetMapping
    public  String getInicio(){
        return"capacitacionList";
    }

}
