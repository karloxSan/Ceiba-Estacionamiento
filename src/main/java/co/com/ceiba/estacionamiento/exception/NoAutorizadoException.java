package co.com.ceiba.estacionamiento.exception;

public class NoAutorizadoException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoAutorizadoException() {
		super("No esta autorizado a ingresar");
	}

}
