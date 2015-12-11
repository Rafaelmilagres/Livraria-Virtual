package br.ssad.livraria.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ssad.livraria.model.Carrinho;
import br.ssad.livraria.model.Item;
import br.ssad.livraria.session.ICarrinhoDAO;

@Stateless
@Remote(ICarrinhoDAO.class)
public class CarrinhoDAOBean implements ICarrinhoDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void cadastrar(Carrinho carrinho) {
		em.persist(carrinho);
	}

	@Override
	public List<Carrinho> listar() {
		Query q = em.createQuery("SELECT u FROM Carrinho u");
		return q.getResultList();
	}
	
	@Override
	public List<Carrinho> listar(Long idUsuario) {
		Query q = em.createQuery("SELECT u FROM Carrinho u WHERE u.idUsuario = :idUsuario");
		q.setParameter("idUsuario", idUsuario);
		
		return q.getResultList();
	}
	
	@Override
	public Item buscarItem(Long id){
		Query q = em.createQuery("SELECT u FROM Item u WHERE u.id = :id");
		q.setParameter("id", id);
		
		return (Item) q.getSingleResult();
	}
	@Override
	public List<Item> buscarItensCarrinho(Long id){
		Query q = em.createQuery("SELECT u FROM Item u WHERE u.carrinho.id = :id");
		q.setParameter("id", id);
		
		return (List<Item>) q.getResultList();
	}

	@Override
	public void remover(Carrinho carrinho) {
		em.remove(em.merge(carrinho));
	}

	@Override
	public void atualizar(Carrinho carrinho) {
		em.merge(carrinho);
	}

	@Override
	public Carrinho buscar(Long id) {
		return em.find(Carrinho.class, id);
	}

} // class CarrinhoDAOBean
