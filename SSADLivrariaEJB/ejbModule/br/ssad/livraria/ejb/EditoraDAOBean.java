package br.ssad.livraria.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ssad.livraria.model.Editora;
import br.ssad.livraria.session.IEditoraDAO;

@Stateless
@Remote(IEditoraDAO.class)
public class EditoraDAOBean implements IEditoraDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void cadastrar(Editora editora) {
		em.persist(editora);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Editora> listar() {
		Query q = em.createQuery("SELECT p FROM Editora p");
		return q.getResultList();
	}

	@Override
	public Editora buscar(Long id) {
		return em.find(Editora.class, id);
	}
}
