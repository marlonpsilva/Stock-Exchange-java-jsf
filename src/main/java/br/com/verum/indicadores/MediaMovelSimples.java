package br.com.verum.indicadores;

import br.com.verum.modelo.SerieTemporal;

public class MediaMovelSimples {

	public double calcula(int posicao, SerieTemporal serie) {

		double soma = 0;
		
		for (int i = posicao; i > posicao - 3; i--) {
			soma += serie.getCandle(i).getFechamento();
		}
		return soma/3;
	}

}
