package projetoif.pi.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "formProjeto";
	}
	
	@PostMapping("/projeto")
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);
		pr.save(projeto);
		return "consulta-adicionada";
	}
	
	@GetMapping("/projeto")
	public ModelAndView listar() {
		List<Projeto> projetos = pr.findAll();
		ModelAndView mv = new ModelAndView("lista");
		mv.addObject("projetos", projetos);
		return mv; 
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Projeto> opt = pr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/projeto");
			return md;
		}
		md.setViewName("projeto/detalhes");
		Projeto projeto = opt.get();
		md.addObject("projeto", projeto);

		return md;
	}
	
}
