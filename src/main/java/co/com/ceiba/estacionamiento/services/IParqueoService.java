package co.com.ceiba.estacionamiento.services;

import java.util.List;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;

public interface IParqueoService {

	public List<ParqueoDto> findAll();

	public ParqueoDto crar(ParqueoDto parqueoDto);

}
