package br.ssad.livraria.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ssad.livraria.model.Cliente;
import br.ssad.livraria.model.Livro;
import br.ssad.livraria.session.IClienteDAO;

@Stateless
@Remote(IClienteDAO.class)
public class ClienteDAOBean implements IClienteDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void cadastrar(Cliente cliente) {
		em.persist(cliente);
	}
	
	@Override
	public Cliente buscar(Cliente cliente) {
		try{
			Query q = em.createQuery("SELECT u FROM Cliente u WHERE u.login =:login AND u.senha =:senha");
			q.setParameter("login", cliente.getLogin());
			q.setParameter("senha", cliente.getSenha());
			
			return (Cliente) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar() {
		Query q = em.createQuery("SELECT p FROM Cliente p");
		return q.getResultList();
	}
}
