package br.ssad.livraria.session;

import java.util.List;

import br.ssad.livraria.model.Cliente;

public interface IClienteDAO {
	public void cadastrar(Cliente cliente);
	public Cliente buscar(Cliente cliente);
	public List<Cliente> listar();
}
