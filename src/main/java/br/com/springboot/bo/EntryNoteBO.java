package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.EntryNoteDAO;
import br.com.springboot.model.EntryNote;

@Service
public class EntryNoteBO implements CRUD<EntryNote, Long>{
	
	@Autowired
	private EntryNoteDAO entryNotedao;
	
	@Override
	public EntryNote searchByID(Long id) {
		return entryNotedao.searchByID(id);
	}

	@Override
	public List<EntryNote> list() {
		return entryNotedao.list();
	}

	@Override
	public void insert(EntryNote en) {
		entryNotedao.insert(en);
		
	}

	@Override
	public void update(EntryNote en) {
		entryNotedao.update(en);
		
	}

	@Override
	public void remove(Long id) {
		entryNotedao.remove(id);
		
	}
	
	
}
