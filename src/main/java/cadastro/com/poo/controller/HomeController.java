package cadastro.com.poo.controller;

import cadastro.com.poo.entity.Filme;
import cadastro.com.poo.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FilmeService filmeService;

    public HomeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public String home(){
        return "index";
    }

}
