package co.com.ceiba.estacionamiento.domain;

import co.com.ceiba.estacionamiento.util.Constante;

public class FactoryCelda {

	public Celda getCelda(String tipo) {
		Celda celda = null;

		switch (tipo) {
		case Constante.TIPO_CARRO:
			celda = new CeldaCarro();
			break;
		case Constante.TIPO_MOTO:
			celda = new CeldaMoto();
			break;
		default:
			break;
		}

		return celda;
	}


}
