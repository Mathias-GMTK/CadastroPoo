package cadastro.com.poo.repository;

import cadastro.com.poo.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    // vou obter filmes pelo genero
    List<Filme> findAllByGeneroOrderByNome(String genero);



}
