package co.com.ceiba.estacionamiento.exception;

public class VehiculoParqueadoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public VehiculoParqueadoException() {
		super("El vehiculo ya se encuentra parqueado");
	}
}
