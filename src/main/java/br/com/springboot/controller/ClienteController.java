package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.ClientBO;
import br.com.springboot.model.Client;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClientBO clientBO;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("cliente", new Client());
		return new ModelAndView("/cliente/formulario", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String salva(@Valid @ModelAttribute("cliente") Client cliente, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "/cliente/formulario";
		}
		if(cliente.getId() == null) {
			clientBO.insert(cliente);
			attr.addFlashAttribute("feedback", "Cliente cadastrado com sucesso");
		}
		else {
			clientBO.update(cliente);
			attr.addFlashAttribute("feedback", "Cliente atualizado com sucesso");
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
	public String inativa(@PathVariable("id") Long id,RedirectAttributes attr) {
		try {
		Client cliente = clientBO.searchByID(id);
		clientBO.inativa(cliente);
		attr.addFlashAttribute("feedback", "Cliente inativado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/clientes";
	}
	
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id,RedirectAttributes attr) {
		try {
		Client cliente = clientBO.searchByID(id);
		clientBO.ativa(cliente);
		attr.addFlashAttribute("feedback", "Cliente ativado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/clientes";
	}
}




