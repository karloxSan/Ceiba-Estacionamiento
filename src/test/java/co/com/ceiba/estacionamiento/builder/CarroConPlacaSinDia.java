package co.com.ceiba.estacionamiento.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.ceiba.estacionamiento.util.Constante;

/**
 * Clase que construye un parqueo de carro con la restriccion de la placa
 *
 */
public class CarroConPlacaSinDia extends ParqueoEntradaDtoBuilder {
	@Override
	public void bluidPlaca() {
		parqueoEntradaDto.setPlaca("AVS201");
	}

	@Override
	public void bluidTipoVehiculo() {
		parqueoEntradaDto.setTipoVehiculo(Constante.TIPO_CARRO);
	}

	@Override
	public void bluidCilindraje() {
	}

	@Override
	public void bluidFechaIngreso() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse("2018-08-22 15:03:23");
			parqueoEntradaDto.setFechaIngreso(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void bluidFechaSalida() {
	}
}
