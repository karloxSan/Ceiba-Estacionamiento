package co.com.ceiba.estacionamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;
import co.com.ceiba.estacionamiento.services.IParqueoService;

@RestController
@RequestMapping("/parqueo")
public class ParqueoController {

	@Autowired
	private IParqueoService parqueoService;

	@GetMapping(value = "/listar")
	public List<ParqueoDto> listarParqueo() {
		return parqueoService.findAll();
	}

	@PostMapping("/crear")
	public void crearParqueo(@RequestBody ParqueoDto parqueoDto) {
		parqueoService.crar(parqueoDto);
	}
}
