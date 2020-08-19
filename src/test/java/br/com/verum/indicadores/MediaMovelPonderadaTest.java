package br.com.verum.indicadores;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.verum.modelo.GeradorDeSerie;
import br.com.verum.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {

	@Test
	public void sequenciaSimplesDeCandles() {

		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);

		MediaMovelPonderada mmp = new MediaMovelPonderada();

		assertEquals(14.0 / 6, mmp.calcula(2, serie), 0.00001);
		assertEquals(20.0 / 6, mmp.calcula(3, serie), 0.00001);
		assertEquals(26.0 / 6, mmp.calcula(4, serie), 0.00001);
		assertEquals(32.0 / 6, mmp.calcula(5, serie), 0.00001);
	}

}
