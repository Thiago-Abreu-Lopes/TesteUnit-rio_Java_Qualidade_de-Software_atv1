package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	Locacao locacao1;
	Filme filme1;
	Filme filme2;
	Filme filme3;
	Cliente cliente1;
	Cliente cliente2;
	@Before
	public void setUp() throws Exception {
		
		filme1 = new Filme("Java", Genero.ROMANCE);
		filme1.valorCompra = 100;
		filme1.id=40;

		filme2 = new Filme("JavaScript", Genero.ROMANCE);
		filme2.valorCompra = 50;
		filme2.id=20;

		filme3 = new Filme("Cobol", Genero.DRAMA);
		filme3.valorCompra = 190;
		filme3.id=10;
		
		cliente1 = new Cliente("Izaias", 2);
		cliente2 = new Cliente("Thiago", 3);
		cliente1.setSituacao(true);
		cliente2.setSituacao(true);
		cliente1.preferencia(filme3);
		cliente1.preferencia(filme2);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	@Test
	public void testLocacaoPreferido() throws Exception {
		locacao1 = cliente1.alugarPreferencias(0);
		assertEquals(cliente1,locacao1.cliente);
		assertEquals(filme3,locacao1.filme);
	}

}
