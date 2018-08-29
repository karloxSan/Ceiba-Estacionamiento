package co.com.ceiba.estacionamiento.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.util.Constante;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryCeldaTest {

	private FactoryCelda factoryCelda;
	private Celda celda;

	@Before
	public void inicial() {
		factoryCelda = new FactoryCelda();
	}

	@Test
	public void celdaCarro() {
		celda = factoryCelda.getCelda(Constante.TIPO_CARRO);
		assertEquals(Constante.CAPACIDAD_CARRO, celda.getMaximoCelda());
	}

	@Test
	public void celdaMoto() {
		celda = factoryCelda.getCelda(Constante.TIPO_MOTO);
		assertEquals(Constante.CAPACIDAD_MOTO, celda.getMaximoCelda());
	}

	@Test
	public void celdaNull() {
		celda = factoryCelda.getCelda(Constante.INICIALIZADOR_A_VACIO);
		assertNull(celda);
	}

}
