package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ProductDAO;
import br.com.springboot.model.Product;

@Service
public class ProductBO implements CRUD<Product, Long>{
	
	@Autowired
	private ProductDAO dao;
	
	@Override
	public Product searchByID(Long id) {
		return dao.searchByID(id);
	}

	@Override
	public List<Product> list() {
		return dao.list();
	}

	@Override
	public void insert(Product t) {
		dao.insert(t);
		
	}

	@Override
	public void update(Product t) {
		dao.update(t);
		
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
		
	}
	
	public void Ativa(Product p) {
		p.setAtivo(true);
		dao.update(p);
	}
	
	public void Inativa(Product p) {
		p.setAtivo(false);
		dao.update(p);
	}
}
