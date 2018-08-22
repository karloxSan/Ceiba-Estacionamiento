package co.com.ceiba.estacionamiento.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PARQUEOS")
public class Parqueo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String placa;

	@Column(name = "fecha_ingreso", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
	private Date fechaIngreso;

	@Column(name = "fecha_salida", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
	private Date fechaSalida;

	@NotEmpty
	private String tipoVehiculo;

	@Column(nullable = true)
	private int cilindraje;

	@Column(nullable = true)
	private double costo;

	@Column(nullable = true)
	private String tiempoParqueado;

	@Column(nullable = true)
	private String tiempoCobrado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

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

}
