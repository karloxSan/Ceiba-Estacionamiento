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
import co.com.ceiba.estacionamiento.services.IParqueoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ParqueoController {

	@Autowired
	private IParqueoService parqueoService;

	@GetMapping(value = "/parqueadero")
	public List<ParqueoEntradaDto> listarParqueo() {
		return parqueoService.findAllParqueados();
	}

	@PostMapping("/parqueadero")
	@ResponseStatus(HttpStatus.CREATED)
	public void crearParqueo(@RequestBody ParqueoEntradaDto parqueoEntradaDto) {
		parqueoService.crear(parqueoEntradaDto);
	}

	@GetMapping(value = "/parqueadero/{placa}")
	public ParqueoEntradaDto buscarParqueo(@PathVariable String placa) {
		return parqueoService.findByPlaca(placa);
	}

	@PutMapping(value = "/parqueadero/{placa}")
	public void actualizarParqueo(@PathVariable String placa) {
		parqueoService.actualizarParqueo(placa);
	}

}
