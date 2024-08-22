package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductDAO implements CRUD<Product, Long>{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Product searchByID(Long id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> list() {
		Query query= em.createQuery("select p from product p");
		return query.getResultList();
	}

	@Override
	public void insert(Product p) {
		em.persist(p);
		
	}

	@Override
	public void update(Product p) {
		em.merge(p);
		
	}

	@Override
	public void remove(Long id) {
		em.remove(id);
		
	}

}
