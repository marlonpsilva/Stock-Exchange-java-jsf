package br.com.verum.modelo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CandleFactoryTest {

	@Test
	public void sequenciaDeNegociacaoSimples() {

		LocalDateTime hoje = LocalDateTime.now();

		Negociacao negociacao1 = new Negociacao(40.0, 100, hoje);
		Negociacao negociacao2 = new Negociacao(35.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao4 = new Negociacao(20.0, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.geraCandleParaData(negociacoes, hoje);

		assertEquals(4, negociacoes.size());
		assertEquals(20.0, candle.getMinimo(), 0.000001);
		assertEquals(45, candle.getMaximo(), 0.000001);
		assertEquals(40, candle.getAbertura(), 0.000001);
		assertEquals(20, candle.getFechamento(), 0.000001);
		assertEquals(14000, candle.getVolume(), 0.000001);
	}

	@Test
	public void geraCandleComUmaNegociacao() {

		LocalDateTime hoje = LocalDateTime.now();

		Negociacao negociacao1 = new Negociacao(20.0, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.geraCandleParaData(negociacoes, hoje);

		assertEquals(1, negociacoes.size());
		assertEquals(20.0, candle.getMinimo(), 0.000001);
		assertEquals(20.0, candle.getMaximo(), 0.000001);
		assertEquals(20.0, candle.getAbertura(), 0.000001);
		assertEquals(20.0, candle.getFechamento(), 0.000001);
		assertEquals(2000, candle.getVolume(), 0.000001);

	}

	@Test
	public void geraCandleComZeroNegociacao() {
		
		LocalDateTime hoje = LocalDateTime.now();

		List<Negociacao> negociacoes = new ArrayList<Negociacao>();

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.geraCandleParaData(negociacoes, hoje);
		
		assertEquals(0, negociacoes.size());
		
		assertEquals(0.0, candle.getMinimo(), 0.000001);
		assertEquals(0.0, candle.getMaximo(), 0.000001);
		assertEquals(0.0, candle.getAbertura(), 0.000001);
		assertEquals(0.0, candle.getFechamento(), 0.000001);
		assertEquals(0.0, candle.getVolume(), 0.000001);	
	}
	
	@Test
	public void negociacaoDeTresDiasDiferentesGeraTresCandlesDiferentes() {
		
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao negociacao1 = new Negociacao(40.0, 10, hoje);
		Negociacao negociacao2 = new Negociacao(35.0, 10, hoje);
		Negociacao negociacao3 = new Negociacao(45.0, 10, hoje);
		
		LocalDateTime amanha = hoje.plusDays(1);
		
		Negociacao negociacao4 = new Negociacao(10.0, 10, amanha);
		Negociacao negociacao5 = new Negociacao(30.0, 10, amanha);
		Negociacao negociacao6 = new Negociacao(20.0, 10, amanha);
		
		LocalDateTime depoisDeAmanha = amanha.plusDays(1);
		
		Negociacao negociacao7 = new Negociacao(60.0, 10, depoisDeAmanha);
		Negociacao negociacao8 = new Negociacao(40.0, 10, depoisDeAmanha);
		Negociacao negociacao9 = new Negociacao(70.0, 10, depoisDeAmanha);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4,
				negociacao5, negociacao6, negociacao7, negociacao8, negociacao9);
		
		
		CandleFactory fabrica = new CandleFactory();
		
		List<Candle> candles = fabrica.constroiCandles(negociacoes);
				
		assertEquals(3, candles.size());
		assertTrue(negociacoes.get(0).isMesmoDia(candles.get(0).getData()));
		assertTrue(negociacoes.get(3).isMesmoDia(candles.get(1).getData()));
		assertTrue(negociacoes.get(6).isMesmoDia(candles.get(2).getData()));
	}
}
