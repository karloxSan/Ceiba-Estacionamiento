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
		parqueoEntradaDto.setFechaIngreso(new Date());
		if (validacion.validarEntrada(parqueoEntradaDto))
			return modelMapper.map(parqueoRepository.saveAndFlush(modelMapper.map(parqueoEntradaDto, Parqueo.class)),
					ParqueoEntradaDto.class);

		return null;

	}

	@Override
	@Transactional
	public void actualizarParqueo(String placa) {

		Parqueo parqueo = parqueoRepository.findByPlaca(placa);

		if (parqueo != null) {
			parqueo.setFechaSalida(new Date());
			double cobro = calcularCobro.calcularCobro(parqueo.getFechaIngreso(), parqueo.getFechaSalida(),
					parqueo.getTipoVehiculo(), parqueo.getCilindraje());
			parqueo.setCosto(cobro);

			parqueoRepository.save(parqueo);
		}

	}

}
