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

import projetoif.pi.projeto.models.Paciente;
import projetoif.pi.projeto.models.Projeto;
import projetoif.pi.projeto.repositories.PacienteRepository;
import projetoif.pi.projeto.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projetos")
public class ProjetosController {

	@Autowired
	private ProjetoRepository pr;
	@Autowired
	private PacienteRepository pcr;

	@RequestMapping("/form")
	public String form(Projeto projeto) {
		return "projetos/formProjeto";
	}

	@PostMapping
	public String adicionar(Projeto projeto) {

		System.out.println(projeto);
		pr.save(projeto);

		return "redirect:/projetos";

	}

	@GetMapping
	public ModelAndView listar() {
		List<Projeto> projetos = pr.findAll();
		ModelAndView mv = new ModelAndView("projetos/lista");
		mv.addObject("projetos", projetos);
		return mv;

	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Paciente paciente) {
		ModelAndView md = new ModelAndView();
		Optional<Projeto> opt = pr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/projetos");
			return md;
		}
		md.setViewName("projetos/detalhes");
		Projeto projeto = opt.get();
		md.addObject("projeto", projeto);
		md.addObject("pacientes", pcr.findAllByProjeto(projeto));
		System.out.println(pcr.findAllByProjeto(projeto));
		
		return md;
	}

	@PostMapping("/{idConsulta}")
	public String salvarPaciente(@PathVariable Long idConsulta, Paciente paciente) {

		Optional<Projeto> opt = pr.findById(idConsulta);
		if (opt.isEmpty()) {
			return "redirect:/projetos";
		}

		Projeto projeto = opt.get();
		paciente.setProjeto(projeto);

		pcr.save(paciente);

		return "redirect:/projetos/{idConsulta}";

	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarConsulta(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Projeto> opt = pr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/projetos");
			return md;
		}
		Projeto projeto = opt.get();
		md.setViewName("projetos/formProjeto");
		md.addObject("projeto", projeto);
		
		return md;
	}

	@GetMapping("/{id}/remover")
	public String apagarconsulta(@PathVariable Long id) {
		Optional<Projeto> opt = pr.findById(id);
		if (!opt.isEmpty()) {
			Projeto projeto = opt.get();
			pr.delete(projeto);
		}
		return "redirect:/projetos";
	}

	@GetMapping("/{idConsulta}/pacientes/{idPaciente}/selecionar")
	public ModelAndView selecionarPaciente(@PathVariable Long idConsulta, @PathVariable Long idPaciente) {
		ModelAndView md = new ModelAndView();

		Optional<Projeto> optConsulta = pr.findById(idConsulta);
		Optional<Paciente> optPaciente = pcr.findById(idPaciente);

		if (optConsulta.isEmpty() || optPaciente.isEmpty()) {
			md.setViewName("redirect:/projetos");
			return md;
		}

		Projeto projeto = optConsulta.get();
		Paciente paciente = optPaciente.get();

		if (projeto.getId() == paciente.getProjeto().getId()) {
			md.setViewName("redirect:/projetos/{idConsulta}");
			return md;
		}
		return md;
	}
	@GetMapping("/{idConsulta}/pacientes/{idPaciente}/remover")
	public String apagarPaciente(@PathVariable Long idConsulta, @PathVariable Long IdPaciente) {
		Optional<Paciente> opt = pcr.findById(IdPaciente);
		if(!opt.isEmpty()) {
			Paciente paciente = opt.get();
			pcr.delete(paciente);
			}
		return "redirect:/projeto{idConsulta}";
		
		
	}
}

