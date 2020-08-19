package br.com.verum.indicadores;

import br.com.verum.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		
		return serie.getCandle(posicao).getAbertura();
	}
	
	@Override
	public String toString() {
		
		return "Abertura";
	}

}
