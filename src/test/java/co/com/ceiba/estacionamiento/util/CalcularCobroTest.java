package co.com.ceiba.estacionamiento.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalcularCobroTest {

	private CalcularCobro calcularCobro;

	@Before
	public void inicial() {
		calcularCobro = new CalcularCobro();

	}

	// ------------------------COBRO TARIFA PARA LA
	// MOTO------------------------------------------
	@Test
	public void cobrarMotoDiaConCilidraje() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaIngreso = sdf.parse("2018-08-26 15:03:23");
			Date fechaSalida = sdf.parse("2018-08-27 15:03:23");
			int cilindraje = 650;

			assertEquals(6000.0,
					calcularCobro.calcularCobro(fechaIngreso, fechaSalida, Constante.TIPO_MOTO, cilindraje), 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cobrarMotoHoraConCilidraje() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaIngreso = sdf.parse("2018-08-26 15:03:23");
			Date fechaSalida = sdf.parse("2018-08-26 17:03:23");
			int cilindraje = 650;

			assertEquals(3000.0,
					calcularCobro.calcularCobro(fechaIngreso, fechaSalida, Constante.TIPO_MOTO, cilindraje), 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cobrarMotoDiaSinCilidraje() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaIngreso = sdf.parse("2018-08-26 15:03:23");
			Date fechaSalida = sdf.parse("2018-08-27 15:03:23");
			int cilindraje = 150;

			assertEquals(4000.0,
					calcularCobro.calcularCobro(fechaIngreso, fechaSalida, Constante.TIPO_MOTO, cilindraje), 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cobrarMotoHoraSinCilidraje() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaIngreso = sdf.parse("2018-08-26 15:03:23");
			Date fechaSalida = sdf.parse("2018-08-26 17:03:23");
			int cilindraje = 150;

			assertEquals(1000.0,
					calcularCobro.calcularCobro(fechaIngreso, fechaSalida, Constante.TIPO_MOTO, cilindraje), 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// ------------------------COBRO TARIFA PARA LA
	// CARRO------------------------------------------
	@Test
	public void cobrarCarroHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaIngreso = sdf.parse("2018-08-26 15:03:23");
			Date fechaSalida = sdf.parse("2018-08-26 17:03:23");
			int cilindraje = 0;

			assertEquals(2000.0,
					calcularCobro.calcularCobro(fechaIngreso, fechaSalida, Constante.TIPO_CARRO, cilindraje), 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cobrarCarroDia() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaIngreso = sdf.parse("2018-08-26 15:03:23");
			Date fechaSalida = sdf.parse("2018-08-27 15:03:23");
			int cilindraje = 0;

			assertEquals(8000.0,
					calcularCobro.calcularCobro(fechaIngreso, fechaSalida, Constante.TIPO_CARRO, cilindraje), 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
