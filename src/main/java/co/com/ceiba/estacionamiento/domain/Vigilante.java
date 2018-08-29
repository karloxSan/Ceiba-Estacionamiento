package co.com.ceiba.estacionamiento.domain;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.exception.NoAutorizadoException;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;
import co.com.ceiba.estacionamiento.util.CalcularCobro;
import co.com.ceiba.estacionamiento.util.Validacion;

/**
 * 
 * Clase que representa el dominio del parqueadero, donde se realiza las
 * actividades de ingreso, salida y listado de los vehiculos
 *
 */
@Component
public class Vigilante {

	@Autowired
	private ParqueoRepository parqueoRepository;

	@Autowired
	private Validacion validacion;

	@Autowired
	private CalcularCobro calcularCobro;

	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Metodo que realiza el ingreso de un vehiculo al parqueadero
	 * 
	 * @param parqueoEntradaDto El vehiculo que desea ingresar
	 * @return El vehiculo ingreso en caso exito, y null en cao contrario
	 * @throws NoAutorizadoException
	 */
	public ParqueoEntradaDto ingresarVehiculo(ParqueoEntradaDto parqueoEntradaDto) throws NoAutorizadoException {
		parqueoEntradaDto.setFechaIngreso(new Date());
		if (validacion.ingresarVehiculo(parqueoEntradaDto, parqueoRepository)) {

			return modelMapper.map(parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class)),
					ParqueoEntradaDto.class);
		}

		return null;
	}

	/**
	 * Metodo que pertmite retirar un vehiculo del parqueadero
	 * 
	 * @param placa La placa del vehiculo que se desea retirar
	 * @return El vehiculo retirado
	 */
	public ParqueoEntradaDto retirarVehiculo(String placa) {
		Parqueo parqueo = parqueoRepository.findByPlaca(placa);

		if (parqueo != null) {
			parqueo.setFechaSalida(new Date());
			double cobro = calcularCobro.calcularCobro(parqueo.getFechaIngreso(), parqueo.getFechaSalida(),
					parqueo.getTipoVehiculo(), parqueo.getCilindraje());
			parqueo.setCosto(cobro);
			parqueo.setTiempoCobrado(calcularCobro.getTiempoCobrado());
			parqueo.setTiempoParqueado(calcularCobro.getTiempoParqueado());

			return modelMapper.map(parqueoRepository.save(parqueo), ParqueoEntradaDto.class);
		}

		return null;
	}

	/**
	 * Metodo que permite listar los vehiculos parqueados
	 * 
	 * @return La lista de vehiculos
	 */
	public List<ParqueoEntradaDto> findAllParqueados() {

		Type listType = new TypeToken<List<ParqueoEntradaDto>>() {
		}.getType();

		return modelMapper.map(parqueoRepository.findAllParqueados(), listType);
	}

	/**
	 * Metodo que permite buscar un vehiculo parqueado
	 * 
	 * @param placa la placa del vehiculo
	 * @return el vehiculo que coincida con la placa
	 */
	public ParqueoEntradaDto findByPlaca(String placa) {

		Parqueo parqueo = parqueoRepository.findByPlaca(placa);

		if (parqueo != null)
			return modelMapper.map(parqueo, ParqueoEntradaDto.class);
		return null;
	}

}
