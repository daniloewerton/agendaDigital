package br.com.dwtecnologia.agendadigital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {
	
	/*@Autowired
	UsuarioRepository repositorio;*/

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	/*@PostMapping("/login")
	public ModelAndView logar(Model model, Usuario usuario) {
		// falta consultar banco
		return new ModelAndView("logar");
	}*/
	
	/*@PostMapping("/login")
	public ModelAndView loginForm(@ModelAttribute Usuario usuario) {
		ModelAndView model = new ModelAndView("tela_Cadastro");
		model.addObject("login", usuario);
		return model;
	}*/
}
