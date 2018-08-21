package co.com.ceiba.estacionamiento.validations;

import java.util.Date;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;

public class ValidacionDiaDomingoLunes {

	/**
	 * Metodo que permite validar si el dia de ingreso es Domingo o Lunes
	 * 
	 * @param parqueoDto Vehiculo que ingresa al parqueadero
	 * @return True si el dia de ingreso es Domingo o Lunes, False en caso contrario
	 */
	public boolean validacion(ParqueoDto parqueoDto) {
		Date date = new Date();

		return ((date.getDay() == 0 && parqueoDto.getFechaIngreso().getDay() == 0)
				|| (date.getDay() == 1 && parqueoDto.getFechaIngreso().getDay() == 1));
	}

}
