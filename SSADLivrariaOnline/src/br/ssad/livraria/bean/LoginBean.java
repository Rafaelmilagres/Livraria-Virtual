package br.ssad.livraria.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ssad.livraria.model.Carrinho;
import br.ssad.livraria.model.Cliente;
import br.ssad.livraria.session.IClienteDAO;

@SessionScoped
@ManagedBean
public class LoginBean {
	
	@EJB
	private IClienteDAO dao;
	protected static Cliente cliente = new Cliente();
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String efetuarLogin() {
		Cliente cliente = dao.buscar(this.cliente);
		
		if(cliente != null){
			this.cliente = cliente;
			return "index?faces-redirect=true";
		} else {
			this.cliente = new Cliente();
			return "login-cliente";
		}
	}
		
	public String efetuarLogout() {
		cliente = new Cliente();
		CarrinhoBean.carrinho = new Carrinho();
		
		return "index?faces-redirect=true";
	}
	
	public boolean isLogado() {
		return this.cliente.getLogin() != null;
	}

} // class LoginBean
