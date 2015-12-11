package br.ssad.livraria.session;

import java.util.List;

import br.ssad.livraria.model.Carrinho;
import br.ssad.livraria.model.Item;

public interface ICarrinhoDAO {
	public void cadastrar(Carrinho carrinho);
	public List<Carrinho> listar();
	public List<Carrinho> listar(Long idUsuario);
	public Item buscarItem(Long id);
	public List<Item> buscarItensCarrinho(Long id);
	public void remover(Carrinho carrinho);
	public void atualizar(Carrinho carrinho);
	public Carrinho buscar(Long id);

} // class CarrinhoDAOBean
