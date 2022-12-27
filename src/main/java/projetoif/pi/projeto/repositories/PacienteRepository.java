package projetoif.pi.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoif.pi.projeto.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
