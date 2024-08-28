package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Client;
import br.com.springboot.model.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SupplierDAO implements CRUD<Supplier,Long> {

	@PersistenceContext
	private EntityManager em;
		
	@Override
	public Supplier searchByID(Long id) {
		return em.find(Supplier.class, id);
	}

	@Override
	public List<Supplier> list() {
		Query query = em.createQuery("SELECT s FROM Supplier s");
		return query.getResultList();
	}

	@Override
	public void insert(Supplier sup) {
		em.persist(sup);
		
	}

	@Override
	public void update(Supplier sup) {
		em.merge(sup);
		
	}

	@Override
	public void remove(Long id) {
        Supplier sup = em.find(Supplier.class, id);
        if (sup != null) {
            em.remove(sup);
        } else {}
        }
	

}
