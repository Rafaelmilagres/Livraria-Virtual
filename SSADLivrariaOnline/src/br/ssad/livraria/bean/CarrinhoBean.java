package br.ssad.livraria.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ssad.livraria.model.Carrinho;
import br.ssad.livraria.model.Item;
import br.ssad.livraria.model.Livro;
import br.ssad.livraria.session.ICarrinhoDAO;
import br.ssad.livraria.session.ILivroDAO;

@SessionScoped
@ManagedBean
public class CarrinhoBean {

	@EJB
	private ICarrinhoDAO carrinhoDao;
	@EJB
	private ILivroDAO livroDao;
	
	private Item item = new Item();
	protected static Carrinho carrinho;
	private int qtdeItem;
	
	public String cadastrarItem(Livro livro) {
		if(carrinho == null)
			 carrinho = new Carrinho();
		
		if(livro.getQtde() <= 0)
			return "index";
		
		for(Item i : this.carrinho.getItens())
			if(i.getLivro().getId() == livro.getId())
				return "index";
				
		this.item.setPrecoUnitario(livro.getPreco());
		this.item.setLivro(livro);
		
		this.carrinho.getItens().add(this.item);
		
		this.item.setCarrinho(this.carrinho);
		this.item = new Item();
		
		return "carrinho";
	}
	
	public String cadastrar() {		
		if(carrinho == null || this.carrinho.getItens() == null || this.carrinho.getItens().size() == 0)
			return "carrinho";
		
		// percorre os itens do carrinho e atuaiza a quantidade de cada um no banco
		for(int i = 0; i < this.carrinho.getItens().size(); i++) {
			this.carrinho.getItens().get(i).getLivro().setQtde(this.carrinho.getItens().get(i).getLivro().getQtde() - 
					this.carrinho.getItens().get(i).getQtde());
			livroDao.editarPreco(this.carrinho.getItens().get(i).getLivro());
		}
		
		// obtem a hora da compra
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		this.carrinho.setDataEmissao(cal);
		this.carrinho.setTotal(getTotal());
		this.carrinho.setIdUsuario(LoginBean.cliente.getId());
		
		this.carrinhoDao.cadastrar(this.carrinho);
		
		this.carrinho = new Carrinho();
		this.item = new Item();
		
		return "tela-compra?faces-redirect=true";
	}
	
	public void atualizaFrete(String str){
		carrinho.setFrete(Double.parseDouble(str));
	}
	
	public String finalizar() {
		return "finaliza?faces-redirect=true";
	}
	
	public List<Carrinho> buscarCarrinho(Long id){
		List<Carrinho> carrinhos = carrinhoDao.listar(id);
		for(Carrinho c : carrinhos){
			c.setItens(carrinhoDao.buscarItensCarrinho(c.getId()));
		}
		return carrinhos;
	}
	
	public List<Item> buscarItensCarrinho(Long id){
		return carrinhoDao.buscarItensCarrinho(id);
	}
	
	public String buscarItem(Long id){
		return "foiiii";
		//return String.format("%d", carrinhoDao.buscarItem(id).getLivro().getId());
	}
	
	public double getTotal() {
		if(carrinho == null)
			return 0;
		
		double total = this.carrinho.getFrete();
		
		for(Item i : this.carrinho.getItens())
			total += i.getLivro().getPreco() * i.getQtde();
			
		return total;
	}
	
	public String aumentarQtde(Item item){
		if(item.getQtde() < item.getLivro().getQtde()){
			
			for(Item i : carrinho.getItens())
				if(i.getLivro().getNome() == item.getLivro().getNome()){
					i.setQtde(item.getQtde() + 1);

					return "carrinho";
				}
		}
		return "carrinho";
	}
	
	public String diminuirQtde(Item item){
		if(item.getQtde() > 1){
			for(Item i : carrinho.getItens())
				if(i.getLivro().getNome() == item.getLivro().getNome()){
					i.setQtde(item.getQtde() - 1);
					
					return "carrinho";
				}
		}
		if(item.getQtde() == 1)
			carrinho.getItens().remove(item);
		return "carrinho";
	}
	
	public void removerItem(Item item) {
		this.carrinho.getItens().remove(item);
	}

	public Item getitem() {
		return item;
	}

	public void setitem(Item item) {
		this.item = item;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public int getQtdeItem() {
		return qtdeItem;
	}

	public void setQtdeItem(int qtdeItem) {
		this.qtdeItem = qtdeItem;
	}
}
