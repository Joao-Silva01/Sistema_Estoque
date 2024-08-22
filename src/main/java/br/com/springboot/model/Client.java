package br.com.springboot.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

// javax.persistence foi substituído por jakarta.persistence
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length= 50)
	@NotBlank(message = "informe o nome")
	@Size(min = 3, max=50)
	private String nome;
	
	@Column(length= 14)
	@CPF(message="CPF inválido")
	private String cpf;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name="data_nascimento", columnDefinition="DATE")
	@NotNull(message = "informe a data de nascimento")
	private LocalDate dataDeNascimento;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="informe o gênero")
	private Genero genero;
	
	@Column(length=14)
	private String telefone;
	
	@Column(length=15)
	private String celular;
	
	@Column(length=50)
	@Email(message = "E-mail inválido")
	private String email;
	
	private boolean ativo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		String cliente = "";
		cliente +="CLIENTE\n";
		cliente +="-------------------------\n";
		cliente +="ID.......: " + this.id + "\n";
		cliente +="Nome.....: " + this.nome + "\n";
		cliente +="CPF......: " + this.cpf + "\n";
		cliente +="Data Nasc: " + this.dataDeNascimento + "\n";
		cliente +="Genero.....: " + this.genero.getDescricao() + "\n";
		cliente +="Telefone.: " + this.telefone + "\n";
		cliente +="Celular..: " + this.celular + "\n";
		cliente +="Email....: " + this.email + "\n";
		cliente +="Ativo....: " + (this.ativo ? "Sim" : "Não") + "\n";
		return cliente;
	}
}
