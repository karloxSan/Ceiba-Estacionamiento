package co.com.ceiba.estacionamiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.domain.Vigilante;
import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.exception.CapacidadExcedidaException;
import co.com.ceiba.estacionamiento.exception.NoAutorizadoException;
import co.com.ceiba.estacionamiento.exception.VehiculoParqueadoException;

/**
 * Clase que implementa el contrato de la interfaz IParqueoService
 *
 */
@Service
public class ParqueoServiceImp implements IParqueoService {

	@Autowired
	private Vigilante vigilante;

	/**
	 * Metodo que permite listar los vehiculos parqueados
	 * 
	 * @return La lista de vehiculos
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ParqueoEntradaDto> findAllParqueados() {
		return vigilante.findAllParqueados();
	}

	/**
	 * Metodo que permite buscar un vehiculo parqueado
	 * 
	 * @param placa la placa del vehiculo
	 * @return el vehiculo que coincida con la placa
	 */
	@Override
	@Transactional
	public ParqueoEntradaDto findByPlaca(String placa) {
		return vigilante.findByPlaca(placa);
	}

	/**
	 * Metodo que permite ingresar unvehiculo alparqueadero
	 * 
	 * @param parqueoEntradaDto El vehiculo que va a ser ingresado al parquedearo
	 * @return el vehiculo ingresado
	 * @throws NoAutorizadoException 
	 */
	@Override
	@Transactional
	public ParqueoEntradaDto ingresarVehiculo(ParqueoEntradaDto parqueoEntradaDto) throws NoAutorizadoException, VehiculoParqueadoException, CapacidadExcedidaException {
		return vigilante.ingresarVehiculo(parqueoEntradaDto);
	}

	/**
	 * Metodo que permite sacar y cobrar el parqueo a un vehiculo
	 * 
	 * @param placa la placa del vehculo
	 * @return el vehiculo que salio del parqueadero
	 */
	@Override
	@Transactional
	public ParqueoEntradaDto retirarVehiculo(String placa) {
		return vigilante.retirarVehiculo(placa);

	}

}
