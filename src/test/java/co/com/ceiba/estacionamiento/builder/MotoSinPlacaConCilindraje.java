package co.com.ceiba.estacionamiento.builder;

import java.util.Date;

import co.com.ceiba.estacionamiento.util.Constante;

/**
 * Clase que construye un parqueo de moto sin la restriccion de la placa y con
 * cilindraje
 *
 */
public class MotoSinPlacaConCilindraje extends ParqueoEntradaDtoBuilder {

	@Override
	public void bluidPlaca() {
		parqueoEntradaDto.setPlaca("OEV04D");

	}

	@Override
	public void bluidTipoVehiculo() {
		parqueoEntradaDto.setTipoVehiculo(Constante.TIPO_MOTO);
	}

	@Override
	public void bluidCilindraje() {
		parqueoEntradaDto.setCilindraje(750);
	}

	@Override
	public void bluidFechaIngreso() {
		parqueoEntradaDto.setFechaIngreso(new Date());
	}

	@Override
	public void bluidFechaSalida() {
	}
}
