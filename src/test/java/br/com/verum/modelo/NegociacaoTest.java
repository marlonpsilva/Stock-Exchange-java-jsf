package br.com.verum.modelo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class NegociacaoTest {

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriaNegociacaoComPrecoNegativo() {

		LocalDateTime hoje = LocalDateTime.now();
		new Negociacao(-10, 100, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriaNegociacaoComDataNula() {

		new Negociacao(10, 100, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriaNegociacaoComQuantidadeZeroOuNegativa() {

		LocalDateTime hoje = LocalDateTime.now();
		new Negociacao(10, -2, hoje);
	}

	@Test
	public void mesmoSegundoEhMesmoDia() {

		LocalDateTime hoje = LocalDateTime.now();
		LocalDateTime agora = hoje;
		Negociacao negociacao = new Negociacao(10.0, 20, hoje);
		assertTrue(negociacao.isMesmoDia(agora));
	}

	@Test
	public void horariosDiferentesMesmoDia() {

		LocalDateTime hoje = LocalDateTime.of(2020, 8, 04, 12, 00);
		LocalDateTime agora = LocalDateTime.of(2020, 8, 04, 11, 00);
		Negociacao negociacao = new Negociacao(10.0, 20, hoje);
		assertTrue(negociacao.isMesmoDia(agora));
	}

	@Test
	public void mesesDiferentesNaoEhMesmoDia() {

		LocalDateTime hoje = LocalDateTime.of(2020, 8, 04, 12, 00);
		LocalDateTime agora = LocalDateTime.of(2020, 9, 04, 11, 00);
		Negociacao negociacao = new Negociacao(10.0, 20, hoje);
		assertFalse(negociacao.isMesmoDia(agora));
	}

	@Test
	public void anosDiferentesNaoEhMesmoDia() {

		LocalDateTime hoje = LocalDateTime.of(2020, 8, 04, 12, 00);
		LocalDateTime agora = LocalDateTime.of(2019, 8, 04, 11, 00);
		Negociacao negociacao = new Negociacao(10.0, 20, hoje);
		assertFalse(negociacao.isMesmoDia(agora));
	}

}
