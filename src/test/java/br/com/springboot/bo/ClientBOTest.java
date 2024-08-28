package br.com.springboot.bo;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.springboot.model.Client;
import br.com.springboot.model.Genero;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
final public class ClientBOTest {
	
	@Autowired
	private ClientBO bo;
	
	@Test
	@Order(1)
	public void insert() {
		Client cliente = new Client();
		cliente.setNome("José da Silva");
		cliente.setCpf("03064318233");
		cliente.setDataDeNascimento(LocalDate.of(2000, 1, 8));
		cliente.setGenero(Genero.MASCULINO);
		cliente.setEmail("email@gmail.com");
		cliente.setTelefone("4133333333");
		cliente.setCelular("41999999999");
		cliente.setAtivo(true);
		bo.insert(cliente);
		System.out.println(cliente);
	}
	
	@Test
	@Order(2)
	public void searchByID() {
		Client cliente = bo.searchByID(1L);
		System.out.println(cliente);
	}
	
	@Test
	@Order(3)
	public void update() {
		Client cliente = bo.searchByID(1L);
		cliente.setTelefone("9698121933");
		cliente.setCelular("96981219337");
		bo.update(cliente);
	}
	
	@Test
	@Order(4)
	public void list() {
		List<Client> clientes = bo.list();
		for (Client cliente : clientes) {
			System.out.println(cliente);
		}
	}
	
	@Test
	@Order(5)
	public void inativa() {
		Client cliente = bo.searchByID(1L);
		bo.inativa(cliente);
	}
	
	@Test
	@Order(6)
	public void remove() {
		bo.remove(1L);
	}
}
