package br.com.springboot.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="entryNotes")
public class EntryNote {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Column(nullable=false,name="data_hora", columnDefinition="DATETIME")
	private LocalDateTime dataHora;
	
	@ManyToOne
	@JoinColumn(name="fornecedor_id", nullable=false)
	private Supplier supplier;
	
	@OneToMany(mappedBy="notaEntrada", cascade = CascadeType.ALL)
	private List<EntryNoteItem> itens;
	
	@Transient
	private Float total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<EntryNoteItem> getItens() {
		return itens;
	}

	public void setItens(List<EntryNoteItem> itens) {
		this.itens = itens;
	}

	public Float getTotal() {
		this.total = 0f;
		if(this.itens != null) {
			for(EntryNoteItem entryNoteItem : itens) {
				total += entryNoteItem.getValorTotal();
			}
		}
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

}
