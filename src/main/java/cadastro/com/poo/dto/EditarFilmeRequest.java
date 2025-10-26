package cadastro.com.poo.dto;

import java.time.LocalDate;

public record EditarFilmeRequest (Long id, String nome, String genero, Long duracao, String sinopse, LocalDate dataLancamento ) {
}

