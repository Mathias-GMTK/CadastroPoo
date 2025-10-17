package cadastro.com.poo.service;

import cadastro.com.poo.entity.Filme;
import cadastro.com.poo.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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


    }


}
