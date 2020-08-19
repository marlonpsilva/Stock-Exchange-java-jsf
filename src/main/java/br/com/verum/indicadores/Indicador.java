package br.com.verum.indicadores;

import br.com.verum.modelo.SerieTemporal;

public interface Indicador {

	double calcula(int posicao, SerieTemporal serie);

}