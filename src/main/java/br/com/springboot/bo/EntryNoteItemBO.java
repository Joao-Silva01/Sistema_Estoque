package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.EntryNoteItemDAO;
import br.com.springboot.model.EntryNoteItem;

@Service
public class EntryNoteItemBO implements CRUD<EntryNoteItem, Long>{

	@Autowired
	private EntryNoteItemDAO dao;
	
	@Override
	public EntryNoteItem searchByID(Long id) {
		return dao.searchByID(id);
	}

	@Override
	public List<EntryNoteItem> list() {
		return dao.list();
	}

	@Override
	public void insert(EntryNoteItem item) {
		dao.insert(item);
		
	}

	@Override
	public void update(EntryNoteItem item) {
		dao.update(item);
		
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
		
	}
	
	public boolean ItemExistente(EntryNoteItem entryNoteItem) {
		Long notaEntradaid = entryNoteItem.getNotaEntrada().getId();
		List<EntryNoteItem> itens = dao.listItens(notaEntradaid);
		
		Long produtoId = entryNoteItem.getProduto().getId();
		if(entryNoteItem.getId() == null) {
			for(EntryNoteItem item : itens) {
				if(item.getProduto().getId() == produtoId) {return true;}
			}
		} 
		else {
			Long entryNoteItemID =  entryNoteItem.getId();
			for(EntryNoteItem item : itens) {
				if(item.getProduto().getId() == produtoId && entryNoteItemID != item.getId()) {return true;}
			}
		}
		return false;
	}
}
