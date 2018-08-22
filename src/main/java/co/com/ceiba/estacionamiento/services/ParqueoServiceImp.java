package co.com.ceiba.estacionamiento.services;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;
import co.com.ceiba.estacionamiento.util.CalcularCobro;
import co.com.ceiba.estacionamiento.util.Validacion;

@Service
public class ParqueoServiceImp implements IParqueoService {

	@Autowired
	private ParqueoRepository parqueoRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private Validacion validacion;

	@Autowired
	private CalcularCobro calcularCobro;

	@Override
	@Transactional(readOnly = true)
	public List<ParqueoEntradaDto> findAllParqueados() {

		Type listType = new TypeToken<List<ParqueoEntradaDto>>() {
		}.getType();

		return modelMapper.map(parqueoRepository.findAllParqueados(), listType);

	}

	@Override
	@Transactional
	public ParqueoEntradaDto findByPlaca(String placa) {

		Parqueo parqueo = parqueoRepository.findByPlaca(placa);

		if (parqueo != null)
			return modelMapper.map(parqueo, ParqueoEntradaDto.class);
		return null;

	}

	@Override
	@Transactional
	public ParqueoEntradaDto crear(ParqueoEntradaDto parqueoEntradaDto) {
		Date ahora = new Date();
		parqueoEntradaDto.setFechaIngreso(ahora);
		if (validacion.validarEntrada(parqueoEntradaDto))
			return modelMapper.map(parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class)),
					ParqueoEntradaDto.class);

		return null;

	}

	@Override
	@Transactional
	public ParqueoEntradaDto actualizarParqueo(String placa) {
		Date ahora = new Date();
		ParqueoEntradaDto parqueo = modelMapper.map(parqueoRepository.findByPlaca(placa), ParqueoEntradaDto.class);
		if (parqueo != null) {
			parqueo.setFechaSalida(ahora);
			double costo = calcularCobro.calcularCobro(parqueo);
			System.out.println(costo);
		}

		return null;
	}

}
