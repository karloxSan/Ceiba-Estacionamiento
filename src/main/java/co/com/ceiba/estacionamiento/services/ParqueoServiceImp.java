package co.com.ceiba.estacionamiento.services;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.dtos.ParqueoSalidaDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;
import co.com.ceiba.estacionamiento.util.Validacion;

@Service
public class ParqueoServiceImp implements IParqueoService {

	@Autowired
	private ParqueoRepository parqueoRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private Validacion validacion;

	@Override
	@Transactional(readOnly = true)
	public List<ParqueoSalidaDto> findAllParqueados() {

		Type listType = new TypeToken<List<ParqueoSalidaDto>>() {
		}.getType();

		return modelMapper.map(parqueoRepository.findAllParqueados(), listType);
	}

	@Override
	@Transactional
	public ParqueoEntradaDto crar(ParqueoEntradaDto parqueoEntradaDto) {

		if (validacion.validarEntrada(parqueoEntradaDto))
			return modelMapper.map(parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class)),
					ParqueoEntradaDto.class);

		return null;

	}

	@Override
	@Transactional
	public ParqueoSalidaDto findByPlaca(String placa) {

		List<Parqueo> parqueos = parqueoRepository.findByPlaca(placa);

		if (!parqueos.isEmpty())
			return modelMapper.map(parqueos.get(0), ParqueoSalidaDto.class);
		return null;

	}

	@Override
	@Transactional
	public ParqueoSalidaDto actualizarParqueo(String placa) {

		return null;
	}

}
