package co.com.ceiba.estacionamiento.services;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;
import co.com.ceiba.estacionamiento.validations.FachadaValidacion;

@Service
public class ParqueoServiceImp implements IParqueoService {

	@Autowired
	private ParqueoRepository parqueoRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private FachadaValidacion fachadaValidacion;

	@Override
	@Transactional(readOnly = true)
	public List<ParqueoDto> findAll() {

		Type listType = new TypeToken<List<ParqueoDto>>() {
		}.getType();

		return modelMapper.map(parqueoRepository.findAll(), listType);
	}

	@Override
	@Transactional
	public ParqueoDto crar(ParqueoDto parqueoDto) {
		if (fachadaValidacion.validarEntrada(parqueoDto))
			return modelMapper.map(parqueoRepository.saveAndFlush(modelMapper.map(parqueoDto, Parqueo.class)),
					ParqueoDto.class);

		return null;

	}

}
