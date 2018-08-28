package co.com.ceiba.estacionamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.exception.ValidacionException;
import co.com.ceiba.estacionamiento.services.IParqueoService;

/**
 * 
 * Clase controller que expone el serviucio REST a los clientes
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ParqueoController {

	@Autowired
	private IParqueoService parqueoService;

	/**
	 * Metodo GET que permite listar los vehiculos que se encuentran parqueados
	 * 
	 * @return La lista de vehiculos parqueados
	 */
	@GetMapping(value = "/parqueadero")
	public List<ParqueoEntradaDto> listarParqueo() {
		return parqueoService.findAllParqueados();
	}

	/**
	 * Metodo POST que permite ingresar un vehiculo al parqueadero
	 * 
	 * @param parqueoEntradaDto Vehiculo parqueadeo
	 * @throws NoAutorizadoException
	 */
	@PostMapping("/parqueadero")
	@ResponseStatus(HttpStatus.CREATED)
	public ParqueoEntradaDto crearParqueo(@RequestBody ParqueoEntradaDto parqueoEntradaDto) throws ValidacionException {
		return parqueoService.ingresarVehiculo(parqueoEntradaDto);
	}

	/**
	 * Metodo GET que permite buscar un vehiculo parquedao por la placa
	 * 
	 * @param placa La placa del vehiculo
	 * @return El vehiculo que coincide con la placa
	 */
	@GetMapping(value = "/parqueadero/{placa}")
	public ParqueoEntradaDto buscarParqueo(@PathVariable String placa) {
		return parqueoService.findByPlaca(placa);
	}

	/**
	 * Metodo PUT que permite sacar y cobrar a un vehiculo el tiempo de parqueo
	 * 
	 * @param placa La placa del vehiculo que va salir
	 * @return El vehiculo que salio del parqueadero
	 */
	@PutMapping(value = "/parqueadero/{placa}")
	public ParqueoEntradaDto actualizarParqueo(@PathVariable String placa) {
		return parqueoService.retirarVehiculo(placa);
	}

}
