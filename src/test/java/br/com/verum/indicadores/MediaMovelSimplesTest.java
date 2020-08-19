package br.com.verum.indicadores;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.verum.modelo.GeradorDeSerie;
import br.com.verum.modelo.SerieTemporal;

public class MediaMovelSimplesTest {

	@Test
	public void sequenciaSimplesDeCandles() {

		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 5, 5);

		Indicador mms = new MediaMovelSimples();

		assertEquals(2.0, mms.calcula(2, serie), 0.000001);
		assertEquals(3.0, mms.calcula(3, serie), 0.000001);
		assertEquals(4.0, mms.calcula(4, serie), 0.000001);
		assertEquals(14.0 / 3, mms.calcula(5, serie), 0.000001);
		assertEquals(5, mms.calcula(6, serie), 0.000001);
	}

}
