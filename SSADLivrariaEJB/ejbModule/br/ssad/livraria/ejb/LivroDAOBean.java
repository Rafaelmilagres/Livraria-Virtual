package br.ssad.livraria.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ssad.livraria.model.Livro;
import br.ssad.livraria.session.ILivroDAO;

@Stateless
@Remote(ILivroDAO.class)
public class LivroDAOBean implements ILivroDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void cadastrar(Livro livro) {
		em.persist(livro);
	}

	@Override
	public void editarPreco(Livro livro) {
		em.merge(livro);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Livro> listar() {
		Query q = em.createQuery("SELECT p FROM Livro p");
		return q.getResultList();
	}

	@Override
	public Livro buscar(Long id) {
		return em.find(Livro.class, id);
	}

	@Override
	public List<Livro> buscarCategoria(Long id) {
		Query q = em.createQuery("SELECT p FROM Livro p where p.categoria.id = :id");
		q.setParameter("id", id);
		return (List<Livro>) q.getResultList();
	}

	@Override
	public List<Livro> buscarNome(String nome) {
		Query q = em.createQuery("SELECT p FROM Livro p WHERE p.nome LIKE :nome");
		q.setParameter("nome", "%" + nome + "%");
		
		return (List<Livro>) q.getResultList();
	}

	@Override
	public List<Livro> buscarIsbn(String isbn) {
		Query q = em.createQuery("SELECT p FROM Livro p WHERE p.isbn LIKE :isbn");
		q.setParameter("isbn", "%" + isbn + "%");
		
		return (List<Livro>) q.getResultList();
	}
	
	@Override
	public List<Livro> buscarAutor(String autor) {
		Query q = em.createQuery("SELECT p FROM Livro p WHERE p.autor LIKE :autor");
		q.setParameter("autor", "%" + autor + "%");
		
		return (List<Livro>) q.getResultList();
	}
}
