package projetoif.pi.projeto.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String adicionar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return form(projeto);
	}
		System.out.println(projeto);
		pr.save(projeto);
		attributes.addFlashAttribute("mensagem", "Projeto adicionado com sucesso!");

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
	public String salvarPaciente(@Valid @PathVariable Long idConsulta, Paciente paciente, BindingResult result, RedirectAttributes attributes) {

		if(result.hasErrors()) {
			return "redirect:/projetos/idConsulta";
	}
		
		
		Optional<Projeto> opt = pr.findById(idConsulta);
		if (opt.isEmpty()) {
			return "redirect:/projetos";
		}

		Projeto projeto = opt.get();
		paciente.setProjeto(projeto);

		pcr.save(paciente);
		attributes.addFlashAttribute("mensagem", "Paciente adicionado com sucesso!");
		

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
	public String apagarconsulta(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Projeto> opt = pr.findById(id);
		if (!opt.isEmpty()) {

			Optional<Paciente> optPacientes = pcr.findById(id);
			Projeto projeto = opt.get();
			pr.delete(projeto);
			attributes.addFlashAttribute("mensagem", "Projeto " + projeto.getNome() + "removido com sucesso!");
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
	public String apagarPaciente(@PathVariable Long idConsulta, @PathVariable Long idPaciente, RedirectAttributes attributes) {
		Optional<Paciente> opt = pcr.findById(idPaciente);
		if (!opt.isEmpty()) {
			Paciente paciente = opt.get();
			pcr.delete(paciente);
			attributes.addFlashAttribute("mensagem", "Paciente " + paciente.getNome() + "removido com sucesso!");

		}
		return "redirect:/projetos/" + idConsulta;

	}
}
