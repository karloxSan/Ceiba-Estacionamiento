package co.com.ceiba.estacionamiento.services;

import java.util.List;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.exception.ValidacionException;

/**
 * Interfaz del servicio que genera el contrato de los metodos a implementar
 * 
 */
public interface IParqueoService {

	/**
	 * Metodo que permite listar los vehiculos parqueados
	 * 
	 * @return La lista de vehiculos
	 */
	public List<ParqueoEntradaDto> findAllParqueados();

	/**
	 * Metodo que permite ingresar unvehiculo alparqueadero
	 * 
	 * @param parqueoEntradaDto El vehiculo que va a ser ingresado al parquedearo
	 * @return el vehiculo ingresado
	 * @throws NoAutorizadoException
	 */
	public ParqueoEntradaDto ingresarVehiculo(ParqueoEntradaDto parqueoEntradaDto) throws ValidacionException;

	/**
	 * Metodo que permite buscar un vehiculo parqueado
	 * 
	 * @param placa la placa del vehiculo
	 * @return el vehiculo que coincida con la placa
	 */
	public ParqueoEntradaDto findByPlaca(String placa);

	/**
	 * Metodo que permite sacar y cobrar el parqueo a un vehiculo
	 * 
	 * @param placa la placa del vehculo
	 * @return el vehiculo que salio del parqueadero
	 */
	public ParqueoEntradaDto retirarVehiculo(String placa);

}
