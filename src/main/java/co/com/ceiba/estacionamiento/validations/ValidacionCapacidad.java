package co.com.ceiba.estacionamiento.validations;

import co.com.ceiba.estacionamiento.util.Constante;

public class ValidacionCapacidad {

	/**
	 * Metodo que permite conocer si el parqueadero llego a su capadidad de
	 * vehiculos 10 Motos, 20 Carros
	 * 
	 * @param parqueoDto Vehiculo que ingresa al parqueadero
	 * @param cantMoto   Cantidad de motos parqueadas
	 * @param cantCarro  Cantidad de carros parqueados
	 * @return True si el parqueadero aun cuenta con disponibilidad
	 */
	public boolean validacion(long cantMoto, long cantCarro) {

		return cantCarro <= Constante.CAPACIDAD_CARRO || cantMoto <= Constante.CAPACIDAD_MOTO;
	}
}
