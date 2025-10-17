package cadastro.com.poo.controller;

import cadastro.com.poo.entity.Filme;
import cadastro.com.poo.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private final FilmeService filmeService;

    public HomeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping
    @RequestMapping("/adicionar-filme")
    public ModelAndView adicionarFilme(){
        filmeService.adicionarFilme();
        return new ModelAndView("redirect:/filme");
    }

    @GetMapping
    @RequestMapping("/deletar-filme")
    public ModelAndView deletarFilme(){
        filmeService.deletarFilme();
        return new ModelAndView("redirect:/filme");
    }


}
