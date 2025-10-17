package cadastro.com.poo.service;

import cadastro.com.poo.entity.Filme;
import cadastro.com.poo.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> findAll(){
        return filmeRepository.findAll();
    }

    public void adicionarFilme(String nome, String genero, Long duracao, String sinopse, LocalDate dataLancamento){

        var filme = new Filme();

        filme.setNome(nome);
        filme.setGenero(genero);
        filme.setDuracao(duracao);
        filme.setSinopse(sinopse);
        filme.setDataLancamento(dataLancamento);

        filmeRepository.save(filme);

    }

    public void deletarFilme(){
        filmeRepository.deleteAll();
    }


    /*
    public Optional<Filme> obterFilmeAleatorio(){
        var lista = findAll();
        if(lista.isEmpty()){return Optional.empty();}
        return Optional.of(lista.get((int)(Math.random()*lista.size())));
    }

     */
}
