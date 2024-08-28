package br.com.springboot.bo;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.springboot.model.Category;
import br.com.springboot.model.Product;
import br.com.springboot.model.Supplier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductsBOTest {
	
	@Autowired
	private ProductBO prodBO;
	
	@Test
	@Order(1)
	public void insert() {
		Product product = new Product();
		product.setNomeProduto("Xiaomi 12");
		product.setCategoria(Category.CELULARES);
		product.setAtivo(true);
		//prodBO.insert(product);
		System.out.println(product);	
	}
	
	@Test
	@Order(2)
	public void searchByID() {
		Product product = prodBO.searchByID(1l);
		System.out.println(product);	
	}
	
	@Test
	@Order(3)
	public void update() {
		Product product = prodBO.searchByID(2l);	
		product.setNomeProduto("Iphone 16");
		prodBO.update(product);	
	}
	
	@Test
	@Order(4)
	public void list() {
		List<Product> products = prodBO.list();
		for(Product product : products) {
			System.out.println(product);
		}
	}
	
	@Test
	@Order(5)
	public void inativa() {
		Product product = prodBO.searchByID(2L);
		prodBO.Inativa(product);
	}
	
	@Test
	@Order(6)
	public void remove() {
		prodBO.remove(2l);
	}
}
