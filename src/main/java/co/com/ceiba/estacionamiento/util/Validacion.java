package co.com.ceiba.estacionamiento.util;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.exception.CapacidadExcedidaException;
import co.com.ceiba.estacionamiento.exception.NoAutorizadoException;
import co.com.ceiba.estacionamiento.exception.VehiculoParqueadoException;
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
	 * @return True si pasa con todas las validaciones de ingreso, false en caso
	 *         contrario
	 * @throws NoAutorizadoException
	 * @throws VehiculoParqueadoException
	 * @throws CapacidadExcedidaException
	 */
	public boolean ingresarVehiculo(ParqueoEntradaDto parqueoEntradaDto, ParqueoRepository parqueoRepository)
			throws NoAutorizadoException, VehiculoParqueadoException, CapacidadExcedidaException {

		// Valida si el vehiculo se encuentra parqueado
		if (parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca()) == null) {

			if (validarCapacidadCarro(parqueoRepository.countByTipoVehiculo(Constante.TIPO_CARRO))
					&& parqueoEntradaDto.getTipoVehiculo().equalsIgnoreCase(Constante.TIPO_CARRO)) {

				if (validarPlaca(parqueoEntradaDto)) {
					if (!validarDia(parqueoEntradaDto)) {
						throw new NoAutorizadoException();
					}
				}
				return true;

			}

			if (validarCapacidadMoto(parqueoRepository.countByTipoVehiculo(Constante.TIPO_MOTO))
					&& parqueoEntradaDto.getTipoVehiculo().equalsIgnoreCase(Constante.TIPO_MOTO)) {
				if (validarPlaca(parqueoEntradaDto)) {

					if (!validarDia(parqueoEntradaDto)) {

						throw new NoAutorizadoException();
					}
				}
				return true;

			}

			throw new CapacidadExcedidaException();

		}

		throw new VehiculoParqueadoException();
	}

	/**
	 * Metodo que permite validar la capacidad del parqueadero para carros
	 * 
	 * @param cantCarro La cantidad de carros almacenados
	 * @return True si aun se cuenta con espacios disponibles, False en caso
	 *         contrario
	 */
	public boolean validarCapacidadCarro(long cantCarro) {
		return cantCarro < Constante.CAPACIDAD_CARRO;
	}

	/**
	 * Metodo que permite validar la capacidad del parqueadero para motos
	 * 
	 * @param cantMoto La cantidad de motos almacenados
	 * @return True si aun se cuenta con espacios disponibles, False en caso
	 *         contrario
	 */
	public boolean validarCapacidadMoto(long cantMoto) {
		return cantMoto < Constante.CAPACIDAD_MOTO;
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
