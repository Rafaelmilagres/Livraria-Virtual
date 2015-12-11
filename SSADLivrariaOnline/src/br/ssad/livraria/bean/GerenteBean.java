package br.ssad.livraria.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.ssad.livraria.model.Categoria;
import br.ssad.livraria.model.Cliente;
import br.ssad.livraria.model.Editora;
import br.ssad.livraria.model.Livro;
import br.ssad.livraria.session.ICategoriaDAO;
import br.ssad.livraria.session.IClienteDAO;
import br.ssad.livraria.session.IEditoraDAO;
import br.ssad.livraria.session.ILivroDAO;

@ManagedBean
public class GerenteBean {
	@EJB
	private ILivroDAO livroDao;
	@EJB
	private ICategoriaDAO categoriaDao;
	@EJB
	private IEditoraDAO editoraDao;
	@EJB
	private IClienteDAO clienteDAO;
		
	private Cliente cliente = new Cliente();
	private Livro livro = new Livro();
	private Editora editora = new Editora();
	private Categoria categoria = new Categoria();
	private Long idCategoria;
	private Long idEditora;
	
	public String cadastrarCliente(){
		if(cliente.getNome() != ""){
			clienteDAO.cadastrar(cliente);
			
			this.cliente = new Cliente();
			
			return "gerente-dashboard?faces-redirect=true";
		}else{
			return "gerente-cad-cliente?faces-redirect=true";	
		}
	}
	
	public String cadastrarCategoria(){
		if(categoria.getNome() != ""){
			categoriaDao.cadastrar(categoria);
			
			this.categoria = new Categoria();
			
			return "gerente-dashboard?faces-redirect=true";
		}else{
			return "gerente-cad-categoria?faces-redirect=true";	
		}
	}

	public String cadastrarEditora(){
		if(editora.getNome() != ""){
			editoraDao.cadastrar(editora);
			
			this.editora = new Editora();
			
			return "gerente-dashboard?faces-redirect=true";
		}else{
			return "gerente-cad-editora?faces-redirect=true";	
		}
	}

	public String cadastrarLivro(){
		if(livro.getNome() != ""){
			categoria = categoriaDao.buscar(idCategoria);
			editora = editoraDao.buscar(idEditora);
			
			livro.setCategoria(categoria);
			livro.setEditora(editora);
			
			if(livro.getId() == null){	
				livroDao.cadastrar(livro);
			}else{
				livroDao.editarPreco(livro);
			}
			
			this.livro = new Livro();
			this.categoria = new Categoria();
			this.editora = new Editora();
			this.idCategoria = null;
			this.idEditora = null;
			
			return "gerente-dashboard?faces-redirect=true";
		}else{
			return "gerente-cad-livro?faces-redirect=true";	
		}
	}
	
	public ILivroDAO getLivroDao() {
		return livroDao;
	}

	public void setLivroDao(ILivroDAO livroDao) {
		this.livroDao = livroDao;
	}

	public ICategoriaDAO getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(ICategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public IEditoraDAO getEditoraDao() {
		return editoraDao;
	}

	public void setEditoraDao(IEditoraDAO editoraDao) {
		this.editoraDao = editoraDao;
	}

	public IClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(IClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(Long idEditora) {
		this.idEditora = idEditora;
	}

	
}
