package br.com.springboot.bo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.*;
import br.com.springboot.model.Client;



@Service
public class ClientBO implements CRUD<Client, Long>{
	
	@Autowired
	private ClientDAO dao;
	
	@Override
	public Client searchByID(Long id) {
		return dao.searchByID(id);
	}
	@Override
	public List<Client> list(){
		return dao.list();
	}
	@Override
	public void insert(Client cliente){
		dao.insert(cliente);
	}
	@Override
	public void update(Client cliente){
		dao.update(cliente);
	}
	@Override
	public void remove(Long id){
		dao.remove(id);
	}
	public void inativa(Client cliente){
		cliente.setAtivo(false);
		dao.update(cliente);
	}
}
