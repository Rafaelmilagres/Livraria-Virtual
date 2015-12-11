package br.ssad.livraria.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.ssad.livraria.model.Categoria;
import br.ssad.livraria.model.Editora;
import br.ssad.livraria.model.Livro;
import br.ssad.livraria.session.ICategoriaDAO;
import br.ssad.livraria.session.IEditoraDAO;
import br.ssad.livraria.session.ILivroDAO;

@ManagedBean
public class LivroBean {
	@EJB
	private ILivroDAO dao;
	@EJB
	private ICategoriaDAO categoriaDao;
	@EJB
	private IEditoraDAO editoraDao;

	private String termoBusca;
	private Long tipoBusca;
	
	private Livro livro = new Livro();
	private List<Livro> livros;
	private Long idcat;
	private Long ided;
	
	public String cadastrar() {
		livro.setCategoria(this.categoriaDao.buscar(this.idcat));
		livro.setEditora(this.editoraDao.buscar(this.ided));
		
		if(livro.getId() != null)
			dao.cadastrar(livro);
		else
			dao.editarPreco(livro);
		
		this.livro = new Livro();
		this.livros = dao.listar();
		
		return "gerente-dashboard?faces-redirect=true";
	}
	
	public String buscarCategoria(Categoria c){
		livros = dao.buscarCategoria(c.getId());
		return "index";
	}
	
	public String buscarTermo(){
		if(tipoBusca == 1)
			livros = dao.buscarNome(termoBusca);
		else if (tipoBusca == 2)
			livros = dao.buscarIsbn(termoBusca);
		else if (tipoBusca == 3)
			livros = dao.buscarAutor(termoBusca);
		
		termoBusca = "";
		tipoBusca = (long)1;
		return "index";
	}
	
	public List<Livro> getLivros() {
		if(livros == null)
			livros = dao.listar();
		
		return livros;
	}

	public List<Categoria> getCategorias(){
		return categoriaDao.listar();
	}

	public List<Editora> getEditoras(){
		return editoraDao.listar();
	}

	public String getTermoBusca() {
		return termoBusca;
	}

	public void setTermoBusca(String termoBusca) {
		this.termoBusca = termoBusca;
	}

	public Long getTipoBusca() {
		return tipoBusca;
	}

	public void setTipoBusca(Long tipoBusca) {
		this.tipoBusca = tipoBusca;
	}

	
}
