package co.com.ceiba.estacionamiento.util;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.domain.Celda;
import co.com.ceiba.estacionamiento.domain.FactoryCelda;
import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.exception.ValidacionException;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;

/**
 * Clase de soporte al vigilante que permite realizar las respectivas
 * validaciones
 * 
 */
@Component
public class Validacion {

	/**
	 * Metodo que permite validar el ingreso de un vehiculo al parqueadero
	 * 
	 * @param parqueoEntradaDto El vehiculo que desea ingresar
	 * @param parqueoRepository el repositirio de datos
	 * @throws NoAutorizadoException
	 * @throws VehiculoParqueadoException
	 * @throws ValidacionException
	 */
	public void ingresarVehiculo(ParqueoEntradaDto parqueoEntradaDto, ParqueoRepository parqueoRepository)
			throws ValidacionException {

		FactoryCelda factoryCelda = new FactoryCelda();

		Celda celda = factoryCelda.getCelda(parqueoEntradaDto.getTipoVehiculo());

		if (parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca()) != null)
			throw new ValidacionException(Constante.EXCEPTION_VEHICULO_PARQUEADO);

		if (parqueoRepository.countByTipoVehiculo(parqueoEntradaDto.getTipoVehiculo()) > celda.getMaximoCelda())
			throw new ValidacionException(Constante.EXCEPTION_CAPACIDAD);

		if (validarPlaca(parqueoEntradaDto) && !validarDia(parqueoEntradaDto))
			throw new ValidacionException(Constante.EXCEPTION_NO_AUTORIZADO);
	}

	/**
	 * Metodo que permite validar si el cilindraje de la moto es mayor a 500 cc
	 * 
	 * @param parqueoSalidaDto Vehiculo que desea salir del parqueadero
	 * @return True si el cilindraje es mayor a 500cc, false en caso contrario
	 */
	public boolean validarCilindraje(ParqueoEntradaDto parqueoSalidaDto) {
		return (parqueoSalidaDto.getCilindraje() >= Constante.CILINDRAJE_MOTO);
	}

	/**
	 * Metodo que permite validar si el dia de ingreso es Domingo o Lunes
	 * 
	 * @param parqueoEntradaDto Vehiculo que ingresa al parqueadero
	 * @return True si el dia de ingreso es Domingo o Lunes, False en caso contrario
	 */
	public boolean validarDia(ParqueoEntradaDto parqueoEntradaDto) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parqueoEntradaDto.getFechaIngreso());

		return ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
				|| (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY));
	}

	/**
	 * Metodo que permite validar si la placa del vehiculo inicia con la letra A
	 * 
	 * @param parqueoEntradaDto Vehiculo que ingresa al parqueadero
	 * @return True si la placa inicia con una letra A, False en caso contrario
	 */
	public boolean validarPlaca(ParqueoEntradaDto parqueoEntradaDto) {
		return parqueoEntradaDto.getPlaca().charAt(0) == 'A';
	}
}
