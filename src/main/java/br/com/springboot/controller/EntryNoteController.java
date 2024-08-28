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

import br.com.springboot.bo.EntryNoteBO;
import br.com.springboot.bo.EntryNoteItemBO;
import br.com.springboot.bo.ProductBO;
import br.com.springboot.bo.SupplierBO;
import br.com.springboot.model.EntryNote;
import br.com.springboot.model.EntryNoteItem;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/entryNotes")
public class EntryNoteController {
	
	@Autowired
	private EntryNoteBO noteBO;
	
	
	@Autowired 
	private SupplierBO supplierBO;
	
	@Autowired 
	private ProductBO productBO;
	
	@RequestMapping(value="/novo", method= RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("entryNote", new EntryNote());
		model.addAttribute("suppliers", supplierBO.list());
		return new ModelAndView("/entry-note/formulario",model);
	}
	
	@RequestMapping(value="", method= RequestMethod.POST)
	public  String salva(@Valid @ModelAttribute EntryNote entryNote, 
			BindingResult result, 
			RedirectAttributes attr, ModelMap model) {
		
		if(result.hasErrors()) {
			return "/entry-note/formulario";
		}
		
		if(entryNote.getSupplier().getId() == null) {
			result.rejectValue("supplier", "field.required");
		}
		if(result.hasErrors()) {
			model.addAttribute("fornecedores", supplierBO.list());
			return "/entry-note/formulario";
		}
		
		if(entryNote.getId() == null) {
			noteBO.insert(entryNote);
			attr.addFlashAttribute("feedback", "A nota de entrada foi cadastrada com sucesso");
		}else {
			noteBO.update(entryNote);
			attr.addFlashAttribute("feedback", "Os dados da nota de entrada foram atualizados com sucesso");
		}
		return "redirect:/entryNotes";
	}
	
	@RequestMapping(value="", method= RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		model.addAttribute("notas", noteBO.list());
		return new ModelAndView("/entry-note/lista",model);
	}
	
	@RequestMapping(value="/{id}/item", method= RequestMethod.GET)
	public ModelAndView product(@PathVariable("id") Long id, ModelMap model) {
		EntryNoteItem eni = new EntryNoteItem();
		EntryNote en = noteBO.searchByID(id);
		eni.setNotaEntrada(en);
		model.addAttribute("entryNoteItem", eni);
		model.addAttribute("produtos", productBO.list());
		return new ModelAndView("/entry-note-item/formulario",model);
	}
	
	@RequestMapping(value="/edita/{id}", method=RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("notaEntradaItem", new EntryNoteItem());
		model.addAttribute("suppliers", supplierBO.list());
		model.addAttribute("entryNote", noteBO.searchByID(id));
		return new ModelAndView("/entry-note/formulario", model);
	}  
	
	
	
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		EntryNote ne = noteBO.searchByID(id);
		noteBO.remove(ne.getId());
		attr.addFlashAttribute("feedback", "Nota entrada removida com sucesso");
		return "redirect:/entryNotes";
	}
}
