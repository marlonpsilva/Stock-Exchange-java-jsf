package br.com.verum.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class CandleTest {

	@Test(expected = IllegalArgumentException.class)
	public void maximoNaoDeveSerMenorQueMinimo() {

		LocalDateTime hoje = LocalDateTime.now();
		CandleBuilder builder = new CandleBuilder();

		Candle candle = builder.comAbertura(10.0).comFechamento(30.0).comMaximo(15).comMinimo(25).comVolume(200)
				.comData(hoje).geraCandle();

	}
	
	@Test
	public void ehAltaSeFechamentoForIgualAbertura() {
		
		LocalDateTime hoje = LocalDateTime.now();
		CandleBuilder builder = new CandleBuilder();

		Candle candle = builder.comAbertura(30.0).comFechamento(30.0).comMaximo(25).comMinimo(15).comVolume(200)
				.comData(hoje).geraCandle();
		
		assertTrue(candle.isAlta());
		assertFalse(candle.isBaixa());
	}

}
