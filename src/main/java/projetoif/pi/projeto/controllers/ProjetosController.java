package projetoif.pi.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projetoif.pi.projeto.models.Projeto;
import projetoif.pi.projeto.repositories.ProjetoRepository;

@Controller
public class ProjetosController {
	
	@Autowired
	private ProjetoRepository pr;

	@RequestMapping("/projeto/form")
	public String form() {
		return "formProjeto";
	}
	
	@PostMapping("/projeto")
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);
		pr.save(projeto);
		
		return "consulta-adicionada";
	}
}
