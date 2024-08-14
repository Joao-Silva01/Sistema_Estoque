package br.com.springboot.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Cliente;

@Repository
public class ClienteDAO implements CRUD<Cliente, Long>{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cliente pesquisaPeloId(Long id) {
		return entityManager.find(Cliente.class, id);
	}
	
	@Override
	public List<Cliente> list() {
		Query query = entityManager.createQuery("Select c FROM Cliente c");
		return (List<Cliente>) query.getResultList();
	}
	
	@Override
	public void insert(Cliente cliente) {
		 entityManager.persist(cliente);
	}
	
	@Override
	public void update(Cliente cliente) {
		 entityManager.merge(cliente);
	}
	
	@Override
	public void remove(Cliente cliente) {
		 entityManager.remove(cliente);
	}
}
