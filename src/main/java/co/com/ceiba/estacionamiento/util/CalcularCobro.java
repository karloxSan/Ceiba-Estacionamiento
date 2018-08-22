package co.com.ceiba.estacionamiento.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.dtos.ParqueoEntradaDto;

@Component
public class CalcularCobro {

	public int calcularDiferencia(Date fechaIngreso, Date fechaSalida, int tipo) {

		Instant instant = Instant.ofEpochMilli(fechaIngreso.getTime());
		LocalDateTime fechaEntradaCasteada = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		Instant instantFechaSalida = Instant.ofEpochMilli(fechaSalida.getTime());
		LocalDateTime fechaSalidaCasteada = LocalDateTime.ofInstant(instantFechaSalida, ZoneId.systemDefault());

		java.time.Duration duracion = java.time.Duration.between(fechaEntradaCasteada, fechaSalidaCasteada);

		Period periodo = Period.between(fechaEntradaCasteada.toLocalDate(), fechaSalidaCasteada.toLocalDate());
		long seconds = duracion.getSeconds();

		if (tipo == 1) {
			// Retorna la diferencia de dias
			return periodo.getDays();
		} else if (tipo == 2) {
			// Retorna la diferencia de horas
			return (int) (seconds / 3600);
		} else {
			// Retorna la diferencia de minutos
			return (int) ((seconds % 3600) / 60);
		}

	}

	public double calcularCobro(ParqueoEntradaDto parqueoSalidaDto) {
		System.out.println("FechaIngreso  " + parqueoSalidaDto.getFechaIngreso() + "   FechaSalida    "
				+ parqueoSalidaDto.getFechaSalida());
		System.out.println("Dias   "
				+ calcularDiferencia(parqueoSalidaDto.getFechaIngreso(), parqueoSalidaDto.getFechaSalida(), 1));

		System.out.println("Horas   "
				+ calcularDiferencia(parqueoSalidaDto.getFechaIngreso(), parqueoSalidaDto.getFechaSalida(), 2));

		System.out.println("Minutos   "
				+ calcularDiferencia(parqueoSalidaDto.getFechaIngreso(), parqueoSalidaDto.getFechaSalida(), 3));
		return 0.0;
	}

}