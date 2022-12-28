package projetoif.pi.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoif.pi.projeto.models.Paciente;
import projetoif.pi.projeto.models.Projeto;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	List<Paciente> findAllByProjeto(Projeto projeto);

}
