package br.ssad.livraria.session;

import java.util.List;

import br.ssad.livraria.model.Editora;

public interface IEditoraDAO {
	public void cadastrar(Editora editora);
	public List<Editora> listar();
	public Editora buscar(Long id);
}
