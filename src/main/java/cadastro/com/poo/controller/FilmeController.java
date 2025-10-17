package cadastro.com.poo.controller;


import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping
    public ModelAndView index(){
        var mv = new ModelAndView("filme/lista_fixa");
        return mv;
    }

}
