package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.springboot.bo.ClientBO;
import br.com.springboot.model.Client;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClientBO clientBO;
	
	@RequestMapping(value = "/novos", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("cliente", new Client());
		return new ModelAndView("/cliente/formulario", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String salva(@ModelAttribute("cliente") Client cliente) {
		if(cliente.getId() == null) {
			clientBO.insert(cliente);
		}
		else {
			clientBO.update(cliente);
		}
		return "redirect:/clientes";
	}
	
	@RequestMapping(value= "", method= RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("clientes", clientBO.list());
		return new ModelAndView("/cliente/lista", model);
	}
	
	@RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id,ModelMap model) {
		model.addAttribute("cliente", clientBO.searchByID(id));
		return new ModelAndView("/cliente/formulario", model);
	}
	
	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id) {
		try {
		Client cliente = clientBO.searchByID(id);
		clientBO.inativa(cliente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/clientes";
	}
	
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id) {
		try {
		Client cliente = clientBO.searchByID(id);
		clientBO.ativa(cliente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/clientes";
	}
}
