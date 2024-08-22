package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.SupplierBO;
import br.com.springboot.model.Supplier;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierBO supplierBO;
	
	@RequestMapping(value="/novo", method= RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("supplier", new Supplier());
		return new ModelAndView("/supplier/formulario", model);
	}
	
	@RequestMapping(value="", method= RequestMethod.POST)
	public String save(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "/supplier/formulario";
		}
		if(supplier.getId()==null) {
			supplierBO.insert(supplier);
			attr.addFlashAttribute("feedback", "O fornecedor foi cadastrado com sucesso!");
		}else {
			supplierBO.update(supplier);
			attr.addFlashAttribute("feedback", "O fornecedor foi atualizado com sucesso!");
		}
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value="", method= RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		model.addAttribute("supplier", supplierBO.list());
		return new ModelAndView("/supplier/lista", model);
	}
	
	@RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("supplier", supplierBO.searchByID(id));
		return new ModelAndView("/supplier/formulario",model);
	}
	
	@RequestMapping(value="/inactive/{id}", method= RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Supplier supplier = supplierBO.searchByID(id);
			supplierBO.inativa(supplier);
			attr.addFlashAttribute("feedback", "Fornecedor inativado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value="/active/{id}", method= RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Supplier supplier = supplierBO.searchByID(id);
			supplierBO.ativa(supplier);
			attr.addFlashAttribute("feedback", "Fornecedor ativado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/suppliers";
	}
}
