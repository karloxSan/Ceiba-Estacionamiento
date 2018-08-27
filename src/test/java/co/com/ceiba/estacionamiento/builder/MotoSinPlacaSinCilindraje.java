package co.com.ceiba.estacionamiento.builder;

import java.util.Date;

import co.com.ceiba.estacionamiento.util.Constante;

/**
 * Clase que construye un parqueo de moto sin la restriccion de la placa y
 * cilindraje
 *
 */
public class MotoSinPlacaSinCilindraje extends ParqueoEntradaDtoBuilder {

	@Override
	public void bluidPlaca() {
		parqueoEntradaDto.setPlaca("NEV04D");

	}

	@Override
	public void bluidTipoVehiculo() {
		parqueoEntradaDto.setTipoVehiculo(Constante.TIPO_MOTO);
	}

	@Override
	public void bluidCilindraje() {
		parqueoEntradaDto.setCilindraje(150);
	}

	@Override
	public void bluidFechaIngreso() {
		parqueoEntradaDto.setFechaIngreso(new Date());
	}

	@Override
	public void bluidFechaSalida() {
	}

}
