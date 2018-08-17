package co.com.ceiba.estacionamiento.services;

import java.util.List;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;

public interface IParqueoService {

	public List<ParqueoDto> findAll();

}
