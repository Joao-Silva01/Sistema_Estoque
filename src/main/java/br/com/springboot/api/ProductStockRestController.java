package br.com.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.bo.ProductBO;
import br.com.springboot.bo.ProductStockBO;
import br.com.springboot.model.Product;
import br.com.springboot.model.ProductStock;

@RestController
public class ProductStockRestController {

	@Autowired
	private ProductStockBO productStockBO;
	
	@Autowired
	private ProductBO productBO;
	
	@RequestMapping(value="/api/stock", method= RequestMethod.GET)
	public List<ProductStock> listAll(){
		return productStockBO.list();
	}
	
	@RequestMapping(value="/api/stock/{id}", method= RequestMethod.GET)
	public ProductStock buscaId(@PathVariable Long id) {
		return productStockBO.searchByID(id);
	}
	
	@RequestMapping(value="/api/stock", method= RequestMethod.POST)
	public ProductStock insert(@RequestBody ProductStock productStock ) {
		Product product = productBO.searchByID(productStock.getProduto().getId());
		productStock.setProduto(product);
		productStockBO.insert(productStock);
		return productStock;
	}
	
	@RequestMapping(value="/api/stock/{id}" , method= RequestMethod.PUT)
	public ProductStock update(@PathVariable Long id, @RequestBody ProductStock productStock) {
		productStock.setId(id);
		productStock.setProduto(productBO.searchByID(productStock.getProduto().getId()));
		productStockBO.update(productStock);
		return productStock;
	}
	
	@RequestMapping(value="/api/stock/{id}", method= RequestMethod.DELETE)
	public ProductStock remove(@PathVariable Long id, @RequestBody ProductStock productStock) {
		ProductStock de = productStockBO.searchByID(productStock.getProduto().getId());
		productStockBO.remove(productStock.getProduto().getId());
		return productStock;
	}
}
