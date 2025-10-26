package cadastro.com.poo.dto;

import cadastro.com.poo.entity.Filme;
import java.util.List;

public record FilmePorGeneroDTO(String genero, List<Filme> filmes) {
}
