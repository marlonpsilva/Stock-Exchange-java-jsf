package br.com.verum.indicadores;

import br.com.verum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private Indicador outroIndicador = new IndicadorFechamento();

	public MediaMovelPonderada() {
	}

	public MediaMovelPonderada(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {

		double soma = 0;
		int peso = 3;

		for (int i = posicao; i > posicao - 3; i--) {
			soma += outroIndicador.calcula(i, serie) * peso;
			peso--;
		}
		return soma / 6;
	}

	@Override
	public String toString() {
		return "MMP - " + outroIndicador.toString();
	}
}
