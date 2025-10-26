package cadastro.com.poo.controller;

import cadastro.com.poo.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String home() {
        return "index";
    }

    @PostMapping("/criar-filmes")
    public ModelAndView criarFilmes() {
        // CORREÇÃO: Chamando o método renomeado no service
        filmeService.adicionarFilmesExemplo();
        return new ModelAndView("redirect:/filme2");
    }

    @PostMapping("/deletar-filmes")
    public ModelAndView deletarFilme() {
        // CORREÇÃO: Chamando o método renomeado no service
        filmeService.deletarTodosOsFilmes();
        return new ModelAndView("redirect:/filme2");
    }

}