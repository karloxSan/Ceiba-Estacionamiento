package co.com.ceiba.estacionamiento.services;

import java.util.List;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;

public interface IParqueoService {

	public List<ParqueoEntradaDto> findAllParqueados();

	public ParqueoEntradaDto crear(ParqueoEntradaDto parqueoEntradaDto);

	public ParqueoEntradaDto findByPlaca(String placa);

	public void actualizarParqueo(String placa);

}
