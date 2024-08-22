package br.com.springboot.controller;

import java.util.Arrays;
import java.util.Locale.Category;

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

import br.com.springboot.bo.ProductBO;
import br.com.springboot.model.Product;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductBO productBO;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", Arrays.asList(Category.values()));
		return new ModelAndView("/product/formulario", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String salva(@Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "/product/formulario";
		}
		if(product.getId() == null) {
			productBO.insert(product);
			attr.addFlashAttribute("feedback", "O produto cadastrado com sucesso");
		}
		else {
			productBO.update(product);
			attr.addFlashAttribute("feedback", "O produto atualizado com sucesso");
		}
		return "redirect:/products";
	}
	
	@RequestMapping(value= "", method= RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("products", productBO.list());
		return new ModelAndView("/product/lista", model);
	}
	
	@RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id,ModelMap model) {
		model.addAttribute("product", productBO.searchByID(id));
		return new ModelAndView("/product/formulario", model);
	}
	
	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id,RedirectAttributes attr) {
		try {
		Product product = productBO.searchByID(id);
		productBO.Inativa(product);
		attr.addFlashAttribute("feedback", "O produto inativado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id,RedirectAttributes attr) {
		try {
		Product product = productBO.searchByID(id);
		productBO.Ativa(product);
		attr.addFlashAttribute("feedback", "O produto ativado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/products";
	}
}
