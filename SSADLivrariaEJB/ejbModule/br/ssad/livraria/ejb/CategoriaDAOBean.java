package br.ssad.livraria.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ssad.livraria.model.Categoria;
import br.ssad.livraria.session.ICategoriaDAO;

@Stateless
@Remote(ICategoriaDAO.class)
public class CategoriaDAOBean implements ICategoriaDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar() {
		Query q = em.createQuery("SELECT p FROM Categoria p");
		return q.getResultList();
	}

	@Override
	public Categoria buscar(Long id) {
		return em.find(Categoria.class, id);
	}
}
