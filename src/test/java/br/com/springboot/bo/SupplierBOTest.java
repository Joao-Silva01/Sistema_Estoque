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

import br.com.springboot.model.Supplier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
final public class SupplierBOTest {
	
	@Autowired
	private SupplierBO supBO;
	Supplier supplier = new Supplier();
	
	@Test
	@Order(1)
	public void insert() {
		supplier.setNomeFornecedor("Adidas");
		supplier.setRazaoSocial("Adidas do Brasil Ltda");
		supplier.setCnpj("42274696000194");
		supplier.setEmail("adidas@gmail.com");
		supplier.setCelular("1234567810");
		supplier.setTelefone("1234567811");
		supplier.setAtivo(true);
		//supBO.insert(supplier);
		System.out.println(supplier);	
	}
	
	@Test
	@Order(2)
	public void searchByID() {
		Supplier supplier = supBO.searchByID(1l);
		System.out.println();	
	}
	
	@Test
	@Order(3)
	public void update() {
		Supplier supplier = supBO.searchByID(1l);
		supplier.setRazaoSocial("Testando a função");
		supBO.update(supplier);	
	}
	
	@Test
	@Order(4)
	public void list() {
		List<Supplier> suppliers = supBO.list();
		for(Supplier supplier : suppliers) {
			System.out.println(supplier);
		}
	}
	
	@Test
	@Order(5)
	public void inativa() {
		Supplier supplier = supBO.searchByID(1L);
		supBO.inativa(supplier);
	}
	
	@Test
	@Order(6)
	public void remove() {
		supBO.remove(1l);
	}
	
}
