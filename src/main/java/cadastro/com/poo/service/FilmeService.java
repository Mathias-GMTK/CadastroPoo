package cadastro.com.poo.service;

import cadastro.com.poo.dto.CriarFilmeRequest;
import cadastro.com.poo.dto.EditarFilmeRequest;
import cadastro.com.poo.dto.FilmePorGeneroDTO;
import cadastro.com.poo.entity.Filme;
import cadastro.com.poo.repository.FilmeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    public void adicionarFilmesExemplo() {
        filmeRepository.saveAll(List.of(
                new Filme("O Poderoso Chefão", "Drama", 175L, "Um clássico do cinema.", LocalDate.of(1972, 3, 24)),
                new Filme("Pulp Fiction", "Crime", 154L, "Várias histórias se entrelaçam.", LocalDate.of(1994, 10, 14)),
                new Filme("Interestelar", "Ficção Científica", 169L, "Uma viagem espacial.", LocalDate.of(2014, 11, 7)),
                new Filme("A Origem", "Ficção Científica", 148L, "Roubo de sonhos.", LocalDate.of(2010, 7, 16)),
                new Filme("O Iluminado", "Terror", 146L, "Um hotel isolado.", LocalDate.of(1980, 5, 23)),
                new Filme("Parasita", "Drama", 132L, "Duas famílias, duas realidades.", LocalDate.of(2019, 5, 30))
        ));
    }

    public void deletarTodosOsFilmes() {
        filmeRepository.deleteAll();
    }


    public Optional<Filme> obterFilmeAleatorio() {
        var lista = findAll();
        if (lista.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(lista.get((int) (Math.random() * lista.size())));
    }

    public List<FilmePorGeneroDTO> obterAgrupadoPorGenero() {

        List<String> generos = filmeRepository.findDistinctGeneros();


        return generos.stream()
                .map(genero -> {
                    List<Filme> filmesDoGenero = filmeRepository.findByGenero(genero);
                    return new FilmePorGeneroDTO(genero, filmesDoGenero);
                })
                .collect(Collectors.toList());
    }

    public Filme criar(CriarFilmeRequest request) {
        StringBuilder builder = new StringBuilder();
        if (Strings.isBlank(request.nome())) {
            builder.append("Favor informar o nome do filme").append("\n");
        }
        if (Strings.isBlank(request.genero())) {
            builder.append("Favor informar o genero do filme").append("\n");
        }
        if (request.duracao() == null || request.duracao() <= 0) { // Validação de Long
            builder.append("Favor informar uma duração válida para o filme").append("\n");
        }
        if (Strings.isBlank(request.sinopse())) {
            builder.append("Favor informar a sinopse do filme").append("\n");
        }
        if (request.dataLancamento() == null) { // Validação de LocalDate
            builder.append("Favor informar a data de Lançamento do filme").append("\n");
        }
        if (!builder.isEmpty()) {
            throw new RuntimeException(builder.toString());
        }

        var filme = new Filme(
                request.nome(),
                request.genero(),
                request.duracao(),
                request.sinopse(),
                request.dataLancamento()
        );
        return filmeRepository.save(filme);
    }

    public Optional<Filme> obterPeloId(Long id) {
        return filmeRepository.findById(id);
    }

    public Filme editar(EditarFilmeRequest request) {
        StringBuilder builder = new StringBuilder();
        if (Strings.isBlank(request.nome())) {
            builder.append("Favor informar o nome do filme").append("\n");
        }
        if (Strings.isBlank(request.genero())) {
            builder.append("Favor informar o genero do filme").append("\n");
        }
        if (request.duracao() == null || request.duracao() <= 0) {
            builder.append("Favor informar uma duracao válida do filme").append("\n");
        }
        if (Strings.isBlank(request.sinopse())) {
            builder.append("Favor informar a sinopse do filme").append("\n");
        }
        if (request.dataLancamento() == null) {
            builder.append("Favor informar a data de Lancamento do filme").append("\n");
        }
        if (!builder.isEmpty()) {
            throw new RuntimeException(builder.toString());
        }

        var old = filmeRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        old.setNome(request.nome());
        old.setGenero(request.genero());
        old.setDuracao(request.duracao());
        old.setSinopse(request.sinopse());
        old.setDataLancamento(request.dataLancamento());
        return filmeRepository.save(old);
    }

    public void deletarPeloId(Long id) {
        filmeRepository.deleteById(id);
    }


}