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
import br.com.springboot.model.EntryNote;
import br.com.springboot.model.EntryNoteItem;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/entry_notes_itens")
public class EntryNoteItemController {
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private EntryNoteItemBO entryNoteItemBO;
	
	@Autowired
	private EntryNoteBO entryNoteBO;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute EntryNoteItem entryNoteItem,BindingResult result,
			RedirectAttributes attr,ModelMap model) {
		
		//Validação do produto
		
		Long produtoId = entryNoteItem.getProduto().getId();
		if (produtoId == null) {
			result.rejectValue("produto", "field.required");
		}
		if(entryNoteItemBO.ItemExistente(entryNoteItem)) {
			result.rejectValue("produto", "duplicate");
		}
		
		
		
		//Validação duplicidade
		if(result.hasErrors()) {
			model.addAttribute("produtos", productBO.list());
			return "/entry-note-item/formulario";
		}
		
		EntryNote notaEntrada = entryNoteBO.searchByID(entryNoteItem.getNotaEntrada().getId());
		entryNoteItem.setNotaEntrada(notaEntrada);
		
		if(entryNoteItem.getId() == null) {
			entryNoteItemBO.insert(entryNoteItem);
			attr.addFlashAttribute("feedback", "Produto adicionado com sucesso!");
		}else {
			entryNoteItemBO.update(entryNoteItem);
			attr.addFlashAttribute("feedback", "Produto atualizado com sucesso!");
		}
		
		Long notaEntradaId = entryNoteItem.getNotaEntrada().getId();
		return "redirect:/entryNotes/edita/" + notaEntradaId;
	}
	
	@RequestMapping(value="/edita/{id}", method=RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("entryNoteItem", entryNoteItemBO.searchByID(id));
		model.addAttribute("produtos", productBO.list());
		return new ModelAndView("/entry-note-item/formulario",model);
	}
	
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		EntryNoteItem entryNoteItem =entryNoteItemBO.searchByID(id);
		Long noteId = entryNoteItem.getNotaEntrada().getId();
		entryNoteItemBO.remove(entryNoteItem.getId());
		attr.addAttribute("feedback","Item removido com sucesso!");
		return "redirect:/entryNotes/edita/" + noteId;
	}
}
