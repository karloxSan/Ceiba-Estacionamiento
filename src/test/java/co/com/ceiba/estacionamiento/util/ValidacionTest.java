package co.com.ceiba.estacionamiento.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.builder.CarroConPlacaConDia;
import co.com.ceiba.estacionamiento.builder.CarroConPlacaSinDia;
import co.com.ceiba.estacionamiento.builder.CarroSinPlacaSinDia;
import co.com.ceiba.estacionamiento.builder.MotoConPlacaConDIaSinCilindraje;
import co.com.ceiba.estacionamiento.builder.MotoConPlacaConDiaConCilindraje;
import co.com.ceiba.estacionamiento.builder.MotoConPlacaSinDIaSinCilindraje;
import co.com.ceiba.estacionamiento.builder.MotoSinPlacaConCilindraje;
import co.com.ceiba.estacionamiento.builder.MotoSinPlacaSinCilindraje;
import co.com.ceiba.estacionamiento.builder.ParqueoBuilderInicial;
import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.exception.ValidacionException;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidacionTest {

	private ParqueoBuilderInicial parqueoBuilderInicial;
	private Validacion validacion;
	private CarroConPlacaConDia carroConPlacaConDia;
	private CarroSinPlacaSinDia carroSinPlacaSinDia;
	private CarroConPlacaSinDia carroConPlacaSinDia;
	private MotoConPlacaConDiaConCilindraje motoConPlacaConDiaConCilindraje;
	private MotoConPlacaConDIaSinCilindraje motoConPlacaConDIaSinCilindraje;
	private MotoConPlacaSinDIaSinCilindraje motoConPlacaSinDIaSinCilindraje;
	private MotoSinPlacaConCilindraje motoSinPlacaConCilindraje;
	private MotoSinPlacaSinCilindraje motoSinPlacaSinCilindraje;

	ParqueoRepository parqueoRepository;

	@Before
	public void inicial() {
		validacion = new Validacion();

		parqueoBuilderInicial = new ParqueoBuilderInicial();
		carroConPlacaConDia = new CarroConPlacaConDia();
		carroSinPlacaSinDia = new CarroSinPlacaSinDia();
		carroConPlacaSinDia = new CarroConPlacaSinDia();
		motoConPlacaConDiaConCilindraje = new MotoConPlacaConDiaConCilindraje();
		motoConPlacaConDIaSinCilindraje = new MotoConPlacaConDIaSinCilindraje();
		motoConPlacaSinDIaSinCilindraje = new MotoConPlacaSinDIaSinCilindraje();
		motoSinPlacaConCilindraje = new MotoSinPlacaConCilindraje();
		motoSinPlacaSinCilindraje = new MotoSinPlacaSinCilindraje();

		parqueoRepository = mock(ParqueoRepository.class);

	}

	// ---------------------------- VALIDA EL INGRESO DEL
	// VEHICULO-----------------------

	/**
	 * Valida el ingreso del carro cuando la placa inicia con A y el dia no es
	 * Domingo o Lunes, en este caso debe lanzar NoAutorizadoException , ademas, el
	 * carro no se encuentra parqueado y hay disponibilidad de lugares
	 * 
	 * @throws ValidacionException
	 */
	@Test(expected = ValidacionException.class)
	public void validarIngresoCarroConPlacaSinDia() throws ValidacionException {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroConPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		when(parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca())).thenReturn(null);

		when(parqueoRepository.countByTipoVehiculo(Constante.TIPO_CARRO)).thenReturn((long) 0);

		validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository);
	}

	/**
	 * Valida el ingreso de la moto cuando la placa inicia con A y el dia no es
	 * Domingo o Lunes, en este caso debe lanzar NoAutorizadoException , ademas, el
	 * carro no se encuentra parqueado y hay disponibilidad de lugares
	 * 
	 * @throws ValidacionException
	 */
	@Test(expected = ValidacionException.class)
	public void validarIngresoMotoConPlacaSinDia() throws ValidacionException {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoConPlacaSinDIaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		when(parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca())).thenReturn(null);

		when(parqueoRepository.countByTipoVehiculo(Constante.TIPO_MOTO)).thenReturn((long) 0);

		validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository);
	}

	/**
	 * Valida el ingreso de la moto cuando esta se encuentra parqueado
	 */
	@Test(expected = ValidacionException.class)
	public void validarIngresoMotoParqueda() throws ValidacionException {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaConCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		when(parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca())).thenReturn(new Parqueo());

		validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository);

	}

	/**
	 * Valida el ingreso del carro cuando esta se encuentra parqueado
	 */
	@Test(expected = ValidacionException.class)
	public void validarIngresoCarroParqueda() throws ValidacionException {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		when(parqueoRepository.findByPlaca(parqueoEntradaDto.getPlaca())).thenReturn(new Parqueo());

		validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository);

	}

	/**
	 * Valida el ingreso de la moto cuando no hay disponibilidad del lugares
	 */
	@Test(expected = ValidacionException.class)
	public void validarIngresoMotoSinDiponibilidad() throws ValidacionException {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoConPlacaConDIaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		when(parqueoRepository.countByTipoVehiculo(Constante.TIPO_MOTO)).thenReturn((long) 11);
		validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository);

	}

	/**
	 * Valida el ingreso del carro cuando no hay disponibilidad del lugares
	 */
	@Test(expected = ValidacionException.class)
	public void validarIngresoCarroSinDiponibilidad() throws ValidacionException {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		when(parqueoRepository.countByTipoVehiculo(Constante.TIPO_CARRO)).thenReturn((long) 21);

		validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository);
	}

	// -------------------------VALIDA LA PLACA DEL VEHICULO------------------
	@Test
	public void validarCarroSinPlaca() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertFalse(validacion.validarPlaca(parqueoEntradaDto));
	}

	@Test
	public void validarMotoConPlaca() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoConPlacaConDiaConCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertTrue(validacion.validarPlaca(parqueoEntradaDto));
	}

	// -------------------------VALIDA EL CILINDRAJE DE LAS MOTOS------------------
	@Test
	public void validarMotoConCilindraje() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaConCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertTrue(validacion.validarCilindraje(parqueoEntradaDto));
	}

	@Test
	public void validarMotoSinCilindraje() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertFalse(validacion.validarCilindraje(parqueoEntradaDto));
	}

	// -------------------------VALIDA EL DIA DE INGRESO DEL VECHICULO SI ES DOMINGO
	// O LUNES------------------

	@Test
	public void validarCarroConPlacaConDia() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroConPlacaConDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertTrue(validacion.validarDia(parqueoEntradaDto));
	}

	@Test
	public void validarCarroConPlacaSinDia() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroConPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertFalse(validacion.validarDia(parqueoEntradaDto));
	}

	@Test
	public void validarMotoConPlacaConDia() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoConPlacaConDiaConCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		assertTrue(validacion.validarDia(parqueoEntradaDto));
	}

}
