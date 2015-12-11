package br.ssad.livraria.session;

import java.util.List;

import br.ssad.livraria.model.Livro;

public interface ILivroDAO {
	public void cadastrar(Livro livro);
	public List<Livro> listar();
	public List<Livro> listarComQuantidade();
	public void editarPreco(Livro livro);
	public Livro buscar(Long id);
	public List<Livro> buscarCategoria(Long id);
	public List<Livro> buscarNome(String nome);
	public List<Livro> buscarIsbn(String isbn);
	public List<Livro> buscarAutor(String autor);
}
