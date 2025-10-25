package cadastro.com.poo.controller;


import cadastro.com.poo.service.FilmeService;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService){
        this.filmeService = filmeService;
    }

    @GetMapping
    public ModelAndView index(){
        var mv = new ModelAndView("filmes/index");
        return mv;
    }

    @GetMapping("/agrupado_por_genero")
    public ModelAndView agrupadoGenero(){
        var mv = new ModelAndView("filmes/agrupado_por_genero");
    }



}
