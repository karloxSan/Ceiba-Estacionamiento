package co.com.ceiba.estacionamiento.exception;

public class CapacidadExcedidaException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CapacidadExcedidaException() {
		super("Capacidad del parqueadero Excedida");
	}
}
