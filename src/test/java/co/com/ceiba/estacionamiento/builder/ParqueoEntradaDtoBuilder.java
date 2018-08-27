package co.com.ceiba.estacionamiento.builder;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;

/**
 * Clase Builder para la construccion de objetos ParqueoEntradaDto
 *
 */
public abstract class ParqueoEntradaDtoBuilder {

	protected ParqueoEntradaDto parqueoEntradaDto;

	public ParqueoEntradaDto getParqueoEntradaDto() {
		return parqueoEntradaDto;
	}

	public void crearParqueoEntradaDto() {
		parqueoEntradaDto = new ParqueoEntradaDto();
	}

	public abstract void bluidPlaca();

	public abstract void bluidTipoVehiculo();

	public abstract void bluidCilindraje();

	public abstract void bluidFechaIngreso();
	
	public abstract void bluidFechaSalida();
}
