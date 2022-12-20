package projetoif.pi.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projetoif.pi.projeto.models.Projeto;

@Controller
public class ProjetosController {

	@RequestMapping("/projeto/form")
	public String form() {
		return "formProjeto";
	}
	
	@PostMapping("/projeto")
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);	
		
		return "consulta-adicionada";
	}
}
