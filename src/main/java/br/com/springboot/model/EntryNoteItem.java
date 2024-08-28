package br.com.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="entry_notes_itens")
public class EntryNoteItem {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne
		@JoinColumn(name="product_id")
		@NotNull
		private Product produto;
		
		@ManyToOne
		@JoinColumn(name="entry_note_id")
		@NotNull
		private EntryNote notaEntrada;
		
		@NotNull(message="Informe a quantidade")
		private Integer quantidade;
		
		@NotNull(message="Informe o valor unitário")
		private Float valorUnitario;
		
		private Float valorTotal;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Product getProduto() {
			return produto;
		}

		public void setProduto(Product produto) {
			this.produto = produto;
		}

		public EntryNote getNotaEntrada() {
			return notaEntrada;
		}

		public void setNotaEntrada(EntryNote notaEntrada) {
			this.notaEntrada = notaEntrada;
		}

		public Integer getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}

		public Float getValorUnitario() {
			return valorUnitario;
		}

		public void setValorUnitario(Float valorUnitario) {
			this.valorUnitario = valorUnitario;
		}

		public Float getValorTotal() {
			return valorTotal;
		}

		public void setValorTotal(Float valorTotal) {
			this.valorTotal = valorTotal;
		}
		
		
}
