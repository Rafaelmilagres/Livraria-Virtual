package br.ssad.livraria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	private String cnpj;
	

	@OneToMany(mappedBy="editora")
	private List<Livro> livros = new ArrayList<Livro>();


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Livro> getLivros() {
		return livros;
	}


	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	
	
}
