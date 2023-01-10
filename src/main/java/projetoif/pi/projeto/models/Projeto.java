package projetoif.pi.projeto.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String servico;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotNull
	private LocalTime horario;


	@Override
	public String toString() {
		return "Projeto [id=" + id + ", nome=" + nome + ", servico=" + servico + ", data=" + data + ", horario="
				+ horario + "]";
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

}
