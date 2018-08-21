package co.com.ceiba.estacionamiento.validations;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;

public class ValidacionPlaca {

	/**
	 * Metodo que permite validar si la placa del vehiculo inicia con la letra A
	 * 
	 * @param parqueoDto Vehiculo que ingresa al parqueadero
	 * @return True si la placa inicia con una letra A, False en caso
	 *         contrario
	 */
	public boolean validacion(ParqueoDto parqueoDto) {
		return parqueoDto.getPlaca().charAt(0) == 'A';
	}

}
