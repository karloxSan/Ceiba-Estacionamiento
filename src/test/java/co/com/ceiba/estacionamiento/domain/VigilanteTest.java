package co.com.ceiba.estacionamiento.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
import co.com.ceiba.estacionamiento.exception.ValidacionException;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VigilanteTest {

	private ParqueoBuilderInicial parqueoBuilderInicial;
	private CarroSinPlacaSinDia carroSinPlacaSinDia;
	private MotoSinPlacaSinCilindraje motoSinPlacaSinCilindraje;

	@Autowired
	private Vigilante vigilante;

	@Autowired
	private ParqueoRepository parqueoRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

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

			ParqueoEntradaDto parqueo = vigilante.ingresarVehiculo(parqueoEntradaDto);
			parqueoRepository.deleteAll();

			assertNotNull(parqueo);

		} catch (ValidacionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ingresarVehiculoNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		try {
			vigilante.ingresarVehiculo(parqueoEntradaDto);

			ParqueoEntradaDto parqueo = vigilante.ingresarVehiculo(parqueoEntradaDto);
			parqueoRepository.deleteAll();

			assertNull(parqueo);

		} catch (ValidacionException e) {
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

		ParqueoEntradaDto parqueo = vigilante.retirarVehiculo(parqueoEntradaDto.getPlaca());
		parqueoRepository.deleteAll();

		assertNotNull(parqueo);
	}

	@Test
	public void retirarVehiculoNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(motoSinPlacaSinCilindraje);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		ParqueoEntradaDto parqueo = vigilante.retirarVehiculo(parqueoEntradaDto.getPlaca());
		parqueoRepository.deleteAll();

		assertNotEquals(parqueoEntradaDto, parqueo);
	}

	// ----------------------------LISTAR VEHICULOS--------------------------

	@Test
	public void listarVehiculo() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class));

		List<ParqueoEntradaDto> parqueo = vigilante.findAllParqueados();
		parqueoRepository.deleteAll();

		assertTrue(parqueo.size() > 0);
	}

	@Test
	public void listarVehiculoVacio() {

		List<ParqueoEntradaDto> parqueo = vigilante.findAllParqueados();

		assertFalse(parqueo.size() > 0);
	}

	// ---------------BUSCAR VEHICULO----------------

	@Test
	public void buscarVehiculoNotNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();

		parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class));

		ParqueoEntradaDto parqueo = vigilante.findByPlaca(parqueoEntradaDto.getPlaca());
		parqueoRepository.deleteAll();

		assertNotNull(parqueo);
	}

	@Test
	public void buscarVehiculoVacioNull() {
		parqueoBuilderInicial.setParqueoEntradaDtoBuilder(carroSinPlacaSinDia);
		parqueoBuilderInicial.crearParqueo();

		ParqueoEntradaDto parqueoEntradaDto = parqueoBuilderInicial.getParqueoEntradaDtoBuilder();
		ParqueoEntradaDto parqueo = vigilante.findByPlaca(parqueoEntradaDto.getPlaca());

		assertNotEquals(parqueoEntradaDto, parqueo);
	}

}
