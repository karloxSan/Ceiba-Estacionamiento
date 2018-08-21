package co.com.ceiba.estacionamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.dtos.ParqueoSalidaDto;
import co.com.ceiba.estacionamiento.services.IParqueoService;

@RestController
@RequestMapping("/parqueo")
public class ParqueoController {

	@Autowired
	private IParqueoService parqueoService;

	@GetMapping(value = "/listar_parqueados")
	public List<ParqueoSalidaDto> listarParqueo() {
		return parqueoService.findAllParqueados();
	}

	@PostMapping("/crear")
	public void crearParqueo(@RequestBody ParqueoEntradaDto parqueoEntradaDto) {
		parqueoService.crar(parqueoEntradaDto);
	}

	@GetMapping(value = "/buscar/{placa}")
	public ParqueoSalidaDto buscarParqueo(@PathVariable String placa) {
		return parqueoService.findByPlaca(placa);
	}
	
	@GetMapping(value = "/sacar/{placa}")
	public ParqueoSalidaDto actualizarParqueo(@PathVariable String placa) {
		return parqueoService.findByPlaca(placa);
	}

}
