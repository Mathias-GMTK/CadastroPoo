package cadastro.com.poo.controller;

import cadastro.com.poo.dto.CriarFilmeRequest;
import cadastro.com.poo.dto.EditarFilmeRequest;
import cadastro.com.poo.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filme2")
public class FilmeDinamicoController {

    private final FilmeService filmeService;

    public FilmeDinamicoController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/agrupado-por-generos")
    public ModelAndView agrupadoPorGeneros() {
        var mv = new ModelAndView("filme/agrupado-por-generos");
        var lista = filmeService.obterAgrupadoPorGenero();
        // CORREÇÃO: Renomeado de "genero" para "grupos" para refletir que é uma lista
        mv.addObject("grupos", lista);
        return mv;
    }

    // Este método responde por GET /filme2
    @GetMapping
    public ModelAndView index() {
        var mv = new ModelAndView("filme/lista_dinamica");
        var lista = filmeService.findAll();
        // CORREÇÃO: Renomeado de "filme" para "filmes" (plural) pois é uma lista
        mv.addObject("filmes", lista);
        return mv;
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable("id") Long id) {
        filmeService.deletarPeloId(id);
        return "redirect:/filme2";
    }

    @GetMapping("/{id}")
    public ModelAndView vizualizar(@PathVariable("id") Long id) {
        var optional = filmeService.obterPeloId(id);
        if (optional.isEmpty()) {
            return new ModelAndView("filme/nao_ha_filme");
        }
        var mv = new ModelAndView("filme/visualizar_filme");
        mv.addObject("filme", optional.get());
        return mv;
    }

    @GetMapping("/criar")
    public ModelAndView criar() {
        var mv = new ModelAndView("filme/novo_filmes");
        mv.addObject("criarFilmeRequest", new CriarFilmeRequest(null, null, null, null, null));
        return mv;
    }

    @PostMapping("/criar")
    public ModelAndView criar(@ModelAttribute CriarFilmeRequest request) {
        ModelAndView mv;
        try {
            var filme = filmeService.criar(request);
            return new ModelAndView("redirect:/filme2/" + filme.getId());
        } catch (Exception e) {
            mv = new ModelAndView("filme/novo_filmes");
            mv.addObject("criarFilmeRequest", request);
            mv.addObject("erro", e.getMessage());
            return mv;
        }
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        var optional = filmeService.obterPeloId(id);
        if (optional.isEmpty()) {
            return new ModelAndView("filme/nao_ha_filme");
        }
        var mv = new ModelAndView("filme/editar_filme");
        var filme = optional.get();
        var request = new EditarFilmeRequest(filme.getId(), filme.getNome(), filme.getGenero(), filme.getDuracao(), filme.getSinopse(), filme.getDataLancamento());
        mv.addObject("editarFilmeRequest", request);
        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView editar(@ModelAttribute EditarFilmeRequest request) {
        ModelAndView mv;
        try {
            var filme = filmeService.editar(request);
            return new ModelAndView("redirect:/filme2/" + filme.getId());
        } catch (Exception e) {
            mv = new ModelAndView("filme/editar_filme");
            mv.addObject("editarFilmeRequest", request);
            mv.addObject("erro", e.getMessage());
            return mv;
        }
    }

    @GetMapping("/aleatorio")
    public ModelAndView aleatorio() {
        var optional = filmeService.obterFilmeAleatorio();
        if (optional.isEmpty()) {
            return new ModelAndView("filme/nao_ha_filme");
        }
        var mv = new ModelAndView("filme/filme_aleatorio");
        mv.addObject("filme", optional.get());
        return mv;
    }
}