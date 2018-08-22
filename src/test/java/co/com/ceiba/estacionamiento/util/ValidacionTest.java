package co.com.ceiba.estacionamiento.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;

public class ValidacionTest {

	private Validacion validacion;
	private ParqueoEntradaDto parqueoEntradaDto;

	@Before
	public void inicial() {
		validacion = new Validacion();
		parqueoEntradaDto = mock(ParqueoEntradaDto.class);
	}

	@Test
	public void validarCapacidadCarroTrue() {
		assertTrue(validacion.validarCapacidadCarro(15));
	}

	@Test
	public void validarCapacidadCarroFalse() {
		assertFalse(validacion.validarCapacidadCarro(20));
	}

	@Test
	public void validarCapacidadMotoTrue() {
		assertTrue(validacion.validarCapacidadMoto(8));
	}

	@Test
	public void validarCapacidadMotoFalse() {
		assertFalse(validacion.validarCapacidadMoto(11));
	}

	@Test
	public void validarPlacaTrue() {
		when(parqueoEntradaDto.getPlaca()).thenReturn("AXM025");
		assertTrue(validacion.validarPlaca(parqueoEntradaDto));
	}

	@Test
	public void validarPlacaFalse() {
		when(parqueoEntradaDto.getPlaca()).thenReturn("PXM025");
		assertFalse(validacion.validarPlaca(parqueoEntradaDto));
	}
}
