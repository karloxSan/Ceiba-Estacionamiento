package co.com.ceiba.estacionamiento.domain;

import co.com.ceiba.estacionamiento.util.Constante;

public class CeldaCarro implements Celda {

	@Override
	public int getMaximoCelda() {
		return Constante.CAPACIDAD_CARRO;
	}

}
