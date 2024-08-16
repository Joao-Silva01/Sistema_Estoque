package br.com.springboot.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Client;

@Repository
@Transactional
public class ClientDAO implements CRUD<Client, Long>{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Client searchByID(Long id) {
		return entityManager.find(Client.class, id);
	}
	
	@Override
	public List<Client> list() {
		Query query = entityManager.createQuery("Select c FROM Client c");
		return (List<Client>) query.getResultList();
	}
	
	@Override
	public void insert(Client cliente) {
		 entityManager.persist(cliente);
	}
	
	@Override
	public void update(Client cliente) {
		 entityManager.merge(cliente);
	}
	
	@Override
	public void remove(Long id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        } else {}
    }
}
