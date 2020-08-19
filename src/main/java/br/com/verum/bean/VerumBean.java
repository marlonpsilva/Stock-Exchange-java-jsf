package br.com.verum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;

import br.com.verum.graficos.GeradorDeGraficos;
import br.com.verum.indicadores.IndicadorFactory;
import br.com.verum.modelo.Candle;
import br.com.verum.modelo.CandleFactory;
import br.com.verum.modelo.Negociacao;
import br.com.verum.modelo.SerieTemporal;
import br.com.verum.ws.ClientWebservice;

@ViewScoped
@ManagedBean
public class VerumBean implements Serializable {

	private List<Negociacao> negociacoes;

	private LineChartModel modeloGrafico;

	private String nomeMedia;

	private String nomeIndicadorBase;

	public VerumBean() {
		this.negociacoes = new ClientWebservice().getNegociacoes();
		geraGrafico();
	}

	public void geraGrafico() {
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorDeGraficos geradorModelo = new GeradorDeGraficos(serie, 2, serie.getUltimaPosicao());
		IndicadorFactory fabrica = new IndicadorFactory(nomeMedia, nomeIndicadorBase);
		geradorModelo.plotaIndicador(fabrica.defineIndicador());
		this.modeloGrafico = geradorModelo.getModeloGrafico();
	}

	

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}

	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	

}
