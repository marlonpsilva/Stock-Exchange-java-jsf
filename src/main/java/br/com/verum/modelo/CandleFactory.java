package br.com.verum.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandleFactory {

	public Candle geraCandleParaData(List<Negociacao> negociacoes, LocalDateTime data) {

		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();

		double volume = 0;
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();

			double preco = negociacao.getPreco();
			if (preco > maximo) {
				maximo = preco;
			} else if (preco < minimo) {
				minimo = preco;
			}
		}

		return new Candle(abertura, fechamento, maximo, minimo, volume, data);

	}

	public List<Candle> constroiCandles(List<Negociacao> negociacoes) {
		List<Candle> candles = new ArrayList<Candle>();

		List<Negociacao> negociacaoDoDia = new ArrayList<Negociacao>();

		LocalDateTime dataAtual = negociacoes.get(0).getData();
		for (Negociacao negociacao : negociacoes) {

			if (negociacao.isMesmoDia(dataAtual)) {
				negociacaoDoDia.add(negociacao);
			} else {
				geraAdicionaCandle(candles, negociacaoDoDia, dataAtual);
				negociacaoDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
		}

		geraAdicionaCandle(candles, negociacaoDoDia, dataAtual);
		return candles;
	}

	private void geraAdicionaCandle(List<Candle> candles, List<Negociacao> negociacaoDoDia, LocalDateTime dataAtual) {
		Candle candle = geraCandleParaData(negociacaoDoDia, dataAtual);
		candles.add(candle);
	}

}
