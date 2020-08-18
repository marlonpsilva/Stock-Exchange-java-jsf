package br.com.verum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.verum.modelo.Negociacao;
import br.com.verum.ws.ClientWebservice;

@ViewScoped
@ManagedBean
public class VerumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Negociacao> negociacoes;

	public VerumBean() {
		this.negociacoes = new ClientWebservice().getNegociacoes();
	}
	
	public List<Negociacao> getNegociacoes(){
		return this.negociacoes;
	}

}
