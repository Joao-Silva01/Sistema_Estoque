package br.com.springboot.model;

import org.hibernate.validator.constraints.br.CNPJ;

// javax.persistence foi substituído por jakarta.persistence
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="suppliers")
public class Supplier{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length= 50)
	@NotBlank(message = "informe o nome fantasia")
	@Size(min = 3, max=50)
	private String nomeFornecedor;
	
	@Column(nullable=false, length= 50)
	@NotBlank(message = "informe a razão social")
	@Size(min = 3, max=50)
	private String razaoSocial;
	
	@Column(length= 18)
	@CNPJ(message="CNPJ inválido")
	private String cnpj;
	
	@Column(length=14)
	private String telefone;
	
	@Column(length=15)
	private String celular;
	
	@Column(length=50)
	@Email(message = "E-mail inválido")
	private String email;
	
	private boolean ativo = true;
	
	public Supplier() {
		this.ativo=true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
}
