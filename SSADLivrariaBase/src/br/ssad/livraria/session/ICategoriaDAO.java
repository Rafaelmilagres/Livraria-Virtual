package br.ssad.livraria.session;

import java.util.List;

import br.ssad.livraria.model.Categoria;
import br.ssad.livraria.model.Livro;

public interface ICategoriaDAO {
	public void cadastrar(Categoria categoria);
	public List<Categoria> listar();
	public Categoria buscar(Long id);
}
