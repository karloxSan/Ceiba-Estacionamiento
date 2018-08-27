package co.com.ceiba.estacionamiento.builder;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;

/**
 * Clase encargada de crear objetos de tipo ParqueoEntradaDto
 *
 */
public class ParqueoBuilderInicial {
	private ParqueoEntradaDtoBuilder parqueoEntradaDtoBuilder;

	public ParqueoEntradaDto getParqueoEntradaDtoBuilder() {
		return parqueoEntradaDtoBuilder.getParqueoEntradaDto();
	}

	public void setParqueoEntradaDtoBuilder(ParqueoEntradaDtoBuilder parqueoEntradaDtoBuilder) {
		this.parqueoEntradaDtoBuilder = parqueoEntradaDtoBuilder;
	}

	public void crearParqueo() {
		parqueoEntradaDtoBuilder.crearParqueoEntradaDto();
		parqueoEntradaDtoBuilder.bluidCilindraje();
		parqueoEntradaDtoBuilder.bluidFechaIngreso();
		parqueoEntradaDtoBuilder.bluidPlaca();
		parqueoEntradaDtoBuilder.bluidTipoVehiculo();
	}

}
