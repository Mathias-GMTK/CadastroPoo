package cadastro.com.poo.dto;

import java.time.LocalDate;

public record CriarFilmeRequest(String nome, String genero, Long duracao, String sinopse, LocalDate dataLancamento) {
}