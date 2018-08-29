package co.com.ceiba.estacionamiento.util;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Clase de soporte al vigilante para calcular el cobro del parqueo a los
 * vehiculos
 */
@Component
public class CalcularCobro {

	private int dias;
	private int horas;
	private int minutos;
	private String tiempoParqueado;
	private String tiempoCobrado;

	/**
	 * Metodo que permite calcular la diferencia de dias, horas y minutos entre dos
	 * fechas
	 * 
	 * @param fechaIngreso Fecha en la cual ingreso el vehiculo al parqueadero
	 * @param fechaSalida  Fecha en la cual sale el vehiculo del parqueadero
	 */
	public void calcularDiferencia(Date fechaIngreso, Date fechaSalida) {
		dias = Constante.INICIALIZADOR_A_CERO;
		horas = Constante.INICIALIZADOR_A_CERO;
		minutos = Constante.INICIALIZADOR_A_CERO;
		tiempoParqueado = Constante.INICIALIZADOR_A_VACIO;

		int diferencia = (int) ((fechaSalida.getTime() - fechaIngreso.getTime()) / Constante.DIVISION_MIL);

		if (diferencia >= Constante.DIA_MILISEGUNDO) {
			dias = (diferencia / Constante.DIA_MILISEGUNDO);
			diferencia -= (dias * Constante.DIA_MILISEGUNDO);
		}
		if (diferencia >= Constante.HORA_MILISEGUNDO) {
			horas = (diferencia / Constante.HORA_MILISEGUNDO);
			diferencia -= (horas * Constante.HORA_MILISEGUNDO);
		}
		if (diferencia >= Constante.MINUTO_MILISEGUNDO) {
			minutos = (diferencia / Constante.MINUTO_MILISEGUNDO);
		}
		tiempoParqueado = Constante.DIAS + dias + Constante.HORAS + horas + Constante.MINUTOS + minutos;

	}

	/**
	 * Metodo que permite calcular el cobro de parqueo del vehiculo
	 * 
	 * @param fechaIngreso Fecha en la cual ingreso el vehiculo al parqueadero
	 * @param fechaSalida  Fecha en la cual sale el vehiculo del parqueadero
	 * @param tipovehiculo El tip de vehiculo parqueado
	 * @param cilindraje   El cilindraje del vehiculo
	 * @return El costo total del parqueo del vehiculo
	 */
	public double calcularCobro(Date fechaIngreso, Date fechaSalida, String tipovehiculo, int cilindraje) {
		calcularDiferencia(fechaIngreso, fechaSalida);
		return calcularCobroVehiculo(cilindraje, tipovehiculo);

	}

	/**
	 * Metodo que permite calcular el cobro de una moto o carro
	 * 
	 * @param cilindraje Cilindraje de la moto o carro
	 * @return Valor del cobro
	 */
	public double calcularCobroVehiculo(int cilindraje, String tipoVehiculo) {
		double cobro;
		tiempoCobrado = Constante.INICIALIZADOR_A_VACIO;

		if (minutos > Constante.INICIALIZADOR_A_CERO) {
			horas++;
			minutos = Constante.INICIALIZADOR_A_CERO;
		}

		if (horas >= Constante.CANT_HORAS_COBRO_DIA) {
			if (horas < Constante.CANT_HORAS_DIA) {
				dias++;
				horas = Constante.INICIALIZADOR_A_CERO;
			} else {
				dias++;
				horas -= Constante.CANT_HORAS_DIA;
			}
		}

		tiempoCobrado = Constante.DIAS + dias + Constante.HORAS + horas + Constante.MINUTOS + minutos;

		if (tipoVehiculo.equalsIgnoreCase(Constante.TIPO_CARRO)) {
			cobro = ((dias * Constante.VALOR_DIA_CARRO) + (horas * Constante.VALOR_HORA_CARRO));
		} else {
			cobro = ((dias * Constante.VALOR_DIA_MOTO) + (horas * Constante.VALOR_HORA_MOTO));

			if (cilindraje > Constante.CILINDRAJE_MOTO) {
				cobro += Constante.RECARGO_CILINDRAJE;
			}
		}
		return cobro;
	}

	public String getTiempoParqueado() {
		return tiempoParqueado;
	}

	public String getTiempoCobrado() {
		return tiempoCobrado;
	}

}