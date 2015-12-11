package br.ssad.livraria.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Carrinho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private Long idUsuario;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataEmissao = Calendar.getInstance();
	private double frete;
	private double total;
	
	@OneToMany(mappedBy="carrinho", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Item> itens = new ArrayList<Item>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getDataEmissao() {
		return new SimpleDateFormat("dd/MM/yyy").format(dataEmissao.getTime());
	}
	
	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public double getFrete() {
		return frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Carrinho [id=").append(id).append(", idUsuario=").append(idUsuario).append(", dataEmissao=")
				.append(dataEmissao).append(", frete=").append(frete).append(", total=").append(total)
				.append(", itens=").append(itens).append("]");
		return builder.toString();
	}

} // class Carrinho
