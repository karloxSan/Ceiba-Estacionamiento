package co.com.ceiba.estacionamiento.dtos;

import java.util.Date;

public class ParqueoEntradaDto {

	private String placa;

	private String tipoVehiculo;

	private int cilindraje;

	private Date fechaIngreso;

	private Date fechaSalida;

	private double costo;

	private String tiempoParqueado;

	private String tiempoCobrado;

	public String getTiempoParqueado() {
		return tiempoParqueado;
	}

	public void setTiempoParqueado(String tiempoParqueado) {
		this.tiempoParqueado = tiempoParqueado;
	}

	public String getTiempoCobrado() {
		return tiempoCobrado;
	}

	public void setTiempoCobrado(String tiempoCobrado) {
		this.tiempoCobrado = tiempoCobrado;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

}
