package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ProductStockDAO;
import br.com.springboot.model.ProductStock;

@Service
public class ProductStockBO implements CRUD<ProductStock, Long>{
	
	@Autowired
	private ProductStockDAO psDAO;

	@Override
	public ProductStock searchByID(Long id) {
		return psDAO.searchByID(id);
	}

	@Override
	public List<ProductStock> list() {
		return psDAO.list();
	}

	@Override
	public void insert(ProductStock ps) {
		psDAO.insert(ps);
		
	}

	@Override
	public void update(ProductStock ps) {
		psDAO.update(ps);
		
	}

	@Override
	public void remove(Long de) {
		psDAO.remove(de);
		
	}
	
}
