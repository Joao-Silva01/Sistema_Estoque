package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.EntryNote;
import br.com.springboot.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EntryNoteDAO implements CRUD<EntryNote, Long>{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public EntryNote searchByID(Long id) {
		return em.find(EntryNote.class, id);
	}

	@Override
	public List<EntryNote> list() {
		Query query = em.createQuery("SELECT en from EntryNote en");
		return query.getResultList();
	}

	@Override
	public void insert(EntryNote en) {
		em.persist(en);
		
	}

	@Override
	public void update(EntryNote en) {
		em.merge(en);
		
	}

	@Override
	public void remove(Long id) {
		EntryNote entryNote = em.find(EntryNote.class, id);
        if (entryNote != null) {
            em.remove(entryNote);
        } else {}
		
	}

}
