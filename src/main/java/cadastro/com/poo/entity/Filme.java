package cadastro.com.poo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String genero;
    private Long duracao;
    private String sinopse;
    private LocalDate dataLancamento;

    public Filme() {
    }

    public Filme(String nome, String genero, Long duracao, String sinopse, LocalDate dataLancamento) {
        this.nome = nome;
        this.genero = genero;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

}
