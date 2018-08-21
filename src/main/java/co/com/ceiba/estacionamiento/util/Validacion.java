package co.com.ceiba.estacionamiento.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;

@Component
public class Validacion {

	@Autowired
	private ParqueoRepository parqueoRepository;

	/**
	 * Metodo que permite validar la entrada de un vehiculo al parquedero
	 * 
	 * @param parqueoEntradaDto Vehiculo que desea ingresar al parqueadero
	 * @return True si pasa todas las validaciones, False en caso contrario
	 */
	public boolean validarEntrada(ParqueoEntradaDto parqueoEntradaDto) {

		// Valida si el vehiculo se encuentra parqueado
		if (parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca()).isEmpty()) {
			if (validarCapacidad(parqueoRepository.countByTipoVehiculo(Constante.TIPO_MOTO),
					parqueoRepository.countByTipoVehiculo(Constante.TIPO_CARRO))) {
				if (validarPlaca(parqueoEntradaDto)) {
					return validarDia(parqueoEntradaDto);
				}
				return true;
			}
			return false;
		}

		return false;
	}

	/**
	 * Metodo que permite conocer si el parqueadero llego a su capadidad de
	 * vehiculos 10 Motos, 20 Carros
	 * 
	 * @param parqueoEntradaDto Vehiculo que ingresa al parqueadero
	 * @param cantMoto          Cantidad de motos parqueadas
	 * @param cantCarro         Cantidad de carros parqueados
	 * @return True si el parqueadero aun cuenta con disponibilidad
	 */
	public boolean validarCapacidad(long cantMoto, long cantCarro) {

		return cantCarro <= Constante.CAPACIDAD_CARRO || cantMoto <= Constante.CAPACIDAD_MOTO;
	}

	/**
	 * Metodo que permite validar si el dia de ingreso es Domingo o Lunes
	 * 
	 * @param parqueoEntradaDto Vehiculo que ingresa al parqueadero
	 * @return True si el dia de ingreso es Domingo o Lunes, False en caso contrario
	 */
	public boolean validarDia(ParqueoEntradaDto parqueoEntradaDto) {
		Date date = new Date();

		return ((date.getDay() == 0 && parqueoEntradaDto.getFechaIngreso().getDay() == 0)
				|| (date.getDay() == 1 && parqueoEntradaDto.getFechaIngreso().getDay() == 1));
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