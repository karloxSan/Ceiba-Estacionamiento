package co.com.ceiba.estacionamiento.domain;

import co.com.ceiba.estacionamiento.util.Constante;

public class CeldaMoto implements Celda {

	@Override
	public int getMaximoCelda() {
		return Constante.CAPACIDAD_MOTO;
	}

}
