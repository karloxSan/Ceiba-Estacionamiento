package co.com.ceiba.estacionamiento.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.builder.CarroSinPlacaSinDia;
import co.com.ceiba.estacionamiento.builder.MotoSinPlacaSinCilindraje;
import co.com.ceiba.estacionamiento.builder.ParqueoBuilderInicial;
import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.exception.NoAutorizadoException;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;
import co.com.ceiba.estacionamiento.services.IParqueoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueoServiceTest {

	private ParqueoBuilderInicial parqueoBuilderInicial;
	private CarroSinPlacaSinDia carroSinPlacaSinDia;
	private MotoSinPlacaSinCilindraje motoSinPlacaSinCilindraje;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IParqueoService parqueoService;

	@Autowired
	private ParqueoRepository parqueoRepository;

	@Before
	public void inicial() {
		parqueoBuilderInicial = new ParqueoBuilderInicial();
		carroSinPlacaSinDia = new CarroSinPlacaSinDia();
		motoSinPlacaSinCilindraje = new MotoSinPlacaSinCilindraje();

	}

	// --------------------- INGRESAR VEHICULO --------------------------
	@Test
	public void ingresarVehiculoNotNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		try {

			ParqueoEntradaDto parqueo = parqueoService.ingresarVehiculo(parqueoEntradaDto);
			parqueoRepository.deleteAll();

			assertNotNull(parqueo);

		} catch (NoAutorizadoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ingresarVehiculoNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		try {
			parqueoService.ingresarVehiculo(parqueoEntradaDto);

			ParqueoEntradaDto parqueo = parqueoService.ingresarVehiculo(parqueoEntradaDto);
			parqueoRepository.deleteAll();

			assertNull(parqueo);

		} catch (NoAutorizadoException e) {
			e.printStackTrace();
		}
	}

	
	// ---------------------------RETITAR VEHICULO------------------------

	@Test
	public void retirarVehiculoNotNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class));

		ParqueoEntradaDto parqueo = parqueoService.retirarVehiculo(parqueoEntradaDto.getPlaca());
		parqueoRepository.deleteAll();

		assertNotNull(parqueo);
	}

	@Test
	public void retirarVehiculoNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		ParqueoEntradaDto parqueo = parqueoService.retirarVehiculo(parqueoEntradaDto.getPlaca());
		parqueoRepository.deleteAll();

		assertNull(parqueo);
	}

	// ----------------------------LISTAR VEHICULOS--------------------------

	@Test
	public void listarVehiculo() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class));

		List<ParqueoEntradaDto> parqueo = parqueoService.findAllParqueados();
		parqueoRepository.deleteAll();

		assertTrue(parqueo.size() > 0);
	}

	@Test
	public void listarVehiculoVacio() {

		List<ParqueoEntradaDto> parqueo = parqueoService.findAllParqueados();

		assertFalse(parqueo.size() > 0);
	}

	// ---------------BUSCAR VEHICULO----------------

	@Test
	public void buscarVehiculoNotNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class));

		ParqueoEntradaDto parqueo = parqueoService.findByPlaca(parqueoEntradaDto.getPlaca());
		parqueoRepository.deleteAll();

		assertNotNull(parqueo);
	}

	@Test
	public void buscarVehiculoVacioNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();
		ParqueoEntradaDto parqueo = parqueoService.findByPlaca(parqueoEntradaDto.getPlaca());

		assertNull(parqueo);
	}

}
