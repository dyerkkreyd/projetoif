package projetoif.pi.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projetoif.pi.projeto.models.Projeto;
import projetoif.pi.projeto.repositories.ProjetoRepository;

@Controller
public class ProjetosController {
	
	@Autowired
	private ProjetoRepository pr;
	
	@RequestMapping("/projeto/form")
	public String form() {
		return "projetos/formProjeto";
	}
	
	@PostMapping("/projeto")
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);
		pr.save(projeto);
		
		return "projetos/consulta-adicionada";
	}
	
	@GetMapping("/projeto")
	public ModelAndView listar() {
		List<Projeto> projetos = pr.findAll();
		ModelAndView mv = new ModelAndView("lista");
		mv.addObject("projetos", projetos);
		return mv; 
		
		
	}
	
}
