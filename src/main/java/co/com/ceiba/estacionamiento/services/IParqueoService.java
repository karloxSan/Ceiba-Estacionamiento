package co.com.ceiba.estacionamiento.services;

import java.util.List;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.dtos.ParqueoSalidaDto;

public interface IParqueoService {

	public List<ParqueoSalidaDto> findAllParqueados();

	public ParqueoEntradaDto crar(ParqueoEntradaDto parqueoEntradaDto);

	public ParqueoSalidaDto findByPlaca(String placa);

	public ParqueoSalidaDto actualizarParqueo(String placa);

}
