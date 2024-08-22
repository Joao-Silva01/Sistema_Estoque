package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.SupplierDAO;
import br.com.springboot.model.Client;
import br.com.springboot.model.Supplier;

@Service
public class SupplierBO implements CRUD<Supplier, Long>{
	
	@Autowired
	private SupplierDAO dao;
	
	@Override
	public Supplier searchByID(Long id) {
		return dao.searchByID(id);
	}

	@Override
	public List<Supplier> list() {
		return dao.list();
	}

	@Override
	public void insert(Supplier sup) {
		dao.insert(sup);
		
	}

	@Override
	public void update(Supplier sup) {
		dao.update(sup);
		
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
		
	}
	
	public void inativa(Supplier sup){
		sup.setAtivo(true);
		dao.update(sup);
	}
	public void ativa(Supplier sup){
		sup.setAtivo(false);
		dao.update(sup);
	}
}
