package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.EntryNoteItem;
import br.com.springboot.model.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EntryNoteItemDAO implements CRUD<EntryNoteItem, Long>{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public EntryNoteItem searchByID(Long id) {
		return em.find(EntryNoteItem.class, id);
	}

	@Override
	public List<EntryNoteItem> list() {
		Query query = em.createQuery("SELECT eni FROM EntryNoteItem eni");
		return query.getResultList();
	}

	@Override
	public void insert(EntryNoteItem t) {
		em.persist(t);
		
	}

	@Override
	public void update(EntryNoteItem t) {
		em.merge(t);
	}

	@Override
	public void remove(Long id) {
		EntryNoteItem nei = em.find(EntryNoteItem.class, id);
        if (nei != null) {
            em.remove(nei);
        } else {}
    }
	
	public List<EntryNoteItem> listItens (Long notaEntradaId){
		Query query=em.createQuery("FROM EntryNoteItem n where n.notaEntrada.id = :notaEntradaId")
				.setParameter("notaEntradaId",notaEntradaId);
		return query.getResultList();
	}
}
	
