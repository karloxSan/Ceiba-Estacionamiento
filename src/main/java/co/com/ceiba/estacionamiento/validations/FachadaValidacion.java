package co.com.ceiba.estacionamiento.validations;

import java.util.List;

import co.com.ceiba.estacionamiento.dtos.ParqueoDto;
import co.com.ceiba.estacionamiento.entities.Parqueo;
import co.com.ceiba.estacionamiento.repositories.ParqueoRepository;
import co.com.ceiba.estacionamiento.util.Constante;

public class FachadaValidacion {

	private ValidacionPlaca validacionPlaca;

	private ValidacionDiaDomingoLunes validacionDiaDomingoLunes;

	private ValidacionCapacidad validacionCapacidad;

//	@Autowired
//	private ParqueoRepository parqueoRepository;

	public FachadaValidacion() {

		validacionPlaca = new ValidacionPlaca();
		validacionDiaDomingoLunes = new ValidacionDiaDomingoLunes();
		validacionCapacidad = new ValidacionCapacidad();

	}

	/**
	 * Metodo que permite validar la entrada de un vehiculo al parquedero
	 * 
	 * @param parqueoDto Vehiculo que desea ingresar al parqueadero
	 * @return True si pasa todas las validaciones, False en caso contrario
	 */
	public boolean validarEntrada(ParqueoDto parqueoDto, ParqueoRepository parqueoRepository) {
		long as = parqueoRepository.count();

		System.out.println(as);

		List<Parqueo> a = parqueoRepository.findByPlaca(parqueoDto.getPlaca());
		// Valida si el vehiculo se encuentra parqueado
		if (a != null) {
			if (validacionCapacidad.validacion(parqueoRepository.countByTipoVehiculo(Constante.TIPO_MOTO),
					parqueoRepository.countByTipoVehiculo(Constante.TIPO_CARRO))) {
				if (validacionPlaca.validacion(parqueoDto)) {
					return validacionDiaDomingoLunes.validacion(parqueoDto);
				}
				return true;
			}
			return false;
		}

		return false;
	}
}
