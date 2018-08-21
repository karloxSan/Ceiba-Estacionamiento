package co.com.ceiba.estacionamiento.util;

import java.util.Calendar;
import java.util.Date;

public class CalcularTiempo {

	public long calcularDiferencia(Date fechaIngreso, Date fechaSalida, int tipo) {

		Calendar cIngreso = Calendar.getInstance();
		Calendar cSalida = Calendar.getInstance();

		cIngreso.setTime(fechaIngreso);
		cSalida.setTime(fechaSalida);

		long milis1 = cIngreso.getTimeInMillis();

		long milis2 = cSalida.getTimeInMillis();

		long diff = milis2 - milis1;

		if (tipo == 1) {
			// Retorna la diferencia de dias
			return Math.abs(diff / (24 * 60 * 60 * 1000));
		} else if (tipo == 2) {
			// Retorna la diferencia de horas
			return (diff / (60 * 60 * 1000));
		} else {
			// Retorna la diferencia de minutos
			return Math.abs(diff / (60 * 1000));
		}
	}

}