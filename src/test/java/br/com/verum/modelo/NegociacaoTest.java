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

}
