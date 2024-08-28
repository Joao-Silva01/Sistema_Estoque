package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.ProductStock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductStockDAO implements CRUD<ProductStock, Long>{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ProductStock searchByID(Long id) {
		return em.find(ProductStock.class, id);
	}

	@Override
	public List<ProductStock> list() {
		Query query = em.createQuery("SELECT ps FROM ProductStock ps");
		return query.getResultList();
	}

	@Override
	public void insert(ProductStock ps) {
		em.persist(ps);
		
	}

	@Override
	public void update(ProductStock ps) {
		em.merge(ps);
		
	}

	@Override
	public void remove(Long id) {
		ProductStock ps = em.find(ProductStock.class, id);
        if (ps != null) {
            em.remove(ps);
        } else {}
        }
}
	
	

