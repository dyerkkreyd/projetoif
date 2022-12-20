package projetoif.pi.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoif.pi.projeto.models.Projeto;

public interface ProjetoRepository extends JpaRepository <Projeto, Long>{ 

}
