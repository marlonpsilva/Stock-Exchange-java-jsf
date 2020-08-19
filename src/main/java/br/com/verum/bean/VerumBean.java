package br.com.verum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;

import br.com.verum.graficos.GeradorDeGraficos;
import br.com.verum.modelo.Candle;
import br.com.verum.modelo.CandleFactory;
import br.com.verum.modelo.Negociacao;
import br.com.verum.modelo.SerieTemporal;
import br.com.verum.ws.ClientWebservice;

@ViewScoped
@ManagedBean
public class VerumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Negociacao> negociacoes;
	
	private LineChartModel modeloGrafico;
	

	public VerumBean() {
		this.negociacoes = new ClientWebservice().getNegociacoes();
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorDeGraficos geradorModelo = new GeradorDeGraficos(serie, 2, serie.getUltimaPosicao());
		geradorModelo.plotaMediaMovelSimples();
		this.modeloGrafico = geradorModelo.getModeloGrafico();
	}
	
	public List<Negociacao> getNegociacoes(){
		return this.negociacoes;
	}
	
	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

}
