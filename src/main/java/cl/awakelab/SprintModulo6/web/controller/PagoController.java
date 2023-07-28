package cl.awakelab.SprintModulo6.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pago")
public class PagoController {
    @GetMapping
    public  String getInicio(){
        return"pago";
    }

    @GetMapping("/{id}")
    public String mostrarPago(@PathVariable("id") int pagoId, Model model) {
        return "pago";
    }

}