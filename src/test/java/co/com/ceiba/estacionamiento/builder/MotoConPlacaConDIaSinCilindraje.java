package co.com.ceiba.estacionamiento.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.ceiba.estacionamiento.util.Constante;

/**
 * Clase que construye un parqueo de moto con la restriccion de la placa y sin
 * la restriccion del cilindraje
 *
 */
public class MotoConPlacaConDIaSinCilindraje extends ParqueoEntradaDtoBuilder {

	@Override
	public void bluidPlaca() {
		parqueoEntradaDto.setPlaca("AEV04D");
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse("2018-08-26 15:03:23");
			parqueoEntradaDto.setFechaIngreso(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bluidFechaSalida() {
	}

}
