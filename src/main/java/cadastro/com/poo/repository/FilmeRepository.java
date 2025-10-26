package cadastro.com.poo.repository;

import cadastro.com.poo.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {


    @Query(value = "SELECT * FROM filme " +
            "ORDER BY TRANSLATE(genero, 'ÁÉÍÓÚÂÊÎÔÛÃÕÀÈÌÒÙÇáéíóúâêîôûãõàèìòùç', 'AEIOUAEIOUAEOUCaeiouaeiouaoaeiouc')",
            nativeQuery = true)
    List<Filme> findAllOrderByGeneroIgnoringAccent();


    @Query("SELECT DISTINCT f.genero FROM Filme f ORDER BY f.genero")
    List<String> findDistinctGeneros();


    List<Filme> findByGenero(String genero);

}