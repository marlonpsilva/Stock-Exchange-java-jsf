package br.com.verum.indicadores;

import br.com.verum.modelo.SerieTemporal;

public class MediaMovelPonderada {

	public double calcula(int posicao, SerieTemporal serie) {

		double soma = 0;
		int peso = 3;

		for (int i = posicao; i > posicao - 3; i--) {
			soma += serie.getCandle(i).getFechamento() * peso;
			peso--;
		}
		return soma / 6;
	}
}
