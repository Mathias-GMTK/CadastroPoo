package cadastro.com.poo.controller;

import cadastro.com.poo.dto.CriarFilmeRequest;
import cadastro.com.poo.dto.EditarFilmeRequest;
import cadastro.com.poo.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public ModelAndView index() {
        var mv = new ModelAndView("filmes/index");
        var listaDeFilmes = filmeService.findAll();
        mv.addObject("filmes", listaDeFilmes);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView newFilmeForm() {
        var mv = new ModelAndView("filmes/novo");
        mv.addObject("filme", new CriarFilmeRequest(null, null, null, null, null));
        return mv;
    }

    @PostMapping
    public String createFilme(CriarFilmeRequest request) {
        filmeService.criar(request);
        return "redirect:/filmes";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editFilmeForm(@PathVariable Long id) {
        var mv = new ModelAndView("filmes/editar");
        var filme = filmeService.obterPeloId(id).orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        mv.addObject("filme", new EditarFilmeRequest(filme.getId(), filme.getNome(), filme.getGenero(), filme.getDuracao(), filme.getSinopse(), filme.getDataLancamento()));
        return mv;
    }

    @PutMapping("/{id}")
    public String updateFilme(@PathVariable Long id, EditarFilmeRequest request) {
        filmeService.editar(request);
        return "redirect:/filmes";
    }

    @DeleteMapping("/{id}")
    public String deleteFilme(@PathVariable Long id) {
        filmeService.deletarPeloId(id);
        return "redirect:/filmes";
    }
}
