package br.ssad.livraria.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.ssad.livraria.bean.LoginBean;

public class Autorizador implements PhaseListener {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	
	private final String URL_LOGIN = "/login.xhtml";
	private final String URL_GERENTE_DASHBOARD = "/gerente-dashboard.xhtml";
	private final String URL_GERENTE_CAD_CATEGORIA = "/gerente-cad-categoria.xhtml";
	private final String URL_GERENTE_CAD_CLIENTE = "/gerente-cad-cliente.xhtml";
	private final String URL_GERENTE_CAD_EDITORA = "/gerente-cad-editora.xhtml";
	private final String URL_GERENTE_CAD_LIVRO = "/gerente-cad-livro.xhtml";

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		if(URL_LOGIN.equals(context.getViewRoot().getViewId()))
			return;
		
		LoginBean loginBean = context.getApplication().evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
		
		if(!loginBean.isLogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			context.renderResponse();
		}
		
		if(!loginBean.getCliente().isAdm() && (URL_GERENTE_DASHBOARD.equals(context.getViewRoot().getViewId()) ||
				URL_GERENTE_CAD_CATEGORIA.equals(context.getViewRoot().getViewId()) || URL_GERENTE_CAD_CLIENTE.equals(context.getViewRoot().getViewId()) ||
				URL_GERENTE_CAD_EDITORA.equals(context.getViewRoot().getViewId()) || URL_GERENTE_CAD_LIVRO.equals(context.getViewRoot().getViewId()))){
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "index?faces-redirect=true");
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) { }

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

} // class Autorizador
