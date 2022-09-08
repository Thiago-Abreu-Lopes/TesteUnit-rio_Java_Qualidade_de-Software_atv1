package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TransacaoTest {

	Transacao transacao;
	Locacao locacao1;
	Locacao locacao2;
	Locacao locacao3;
	Filme filme1;
	Filme filme2;
	Filme filme3;
	Cliente cliente1;
	Cliente cliente2;

	@Before
	public void setUp() throws Exception {
		locacao1 = new Locacao();
		locacao2 = new Locacao();
		locacao3 = new Locacao();
		
		cliente1 = new Cliente("Izaias", 2);
		cliente2 = new Cliente("Thiago", 3);
		cliente1.setSituacao(true);
		cliente2.setSituacao(true);
		cliente1.preferencia(filme3);
		cliente1.preferencia(filme2);
		
		filme1 = new Filme("Java", Genero.ROMANCE);
		filme1.valorCompra = 100;
		filme1.id=40;

		filme2 = new Filme("JavaScript", Genero.ROMANCE);
		filme2.valorCompra = 50;
		filme2.id=20;

		filme3 = new Filme("Cobol", Genero.DRAMA);
		filme3.valorCompra = 190;
		filme3.id=10;
		
		locacao1.alugar(cliente1, filme1);
		locacao1.valorAluguel = 10;
		locacao2.alugar(cliente2, filme2);
		locacao2.valorAluguel = 50;
		locacao3.alugar(cliente1, filme3);
		locacao3.valorAluguel = 30;
		
		

		transacao = new Transacao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void valorLocacaoTotalTest() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		assertEquals(transacao.alugueis.get(0).cliente.nome, "Izaias");
		assertTrue("Filme deveria ser selecionado corretamente",transacao.alugueis.get(1).filme.id==20);
		assertEquals(60, transacao.valorLocacaoTotal(), 0.1);
	}

	
	@Test 
	public void buscaClienteIdTest() {
		
		transacao.alugueis.add(locacao2);
		assertEquals("Thiago",transacao.buscaCliente(3).nome);
	}
	
	@Test 
	public void calculoLucroTest() {
		
		transacao.alugueis.add(locacao2);
		
		assertEquals(100,transacao.calculoLucro(20),0.01);
	}
	
	@Test
	public void testeDeContagemDoGeneroMaisAlugado() {
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao3);
		assertEquals(transacao.generoPreferido(),Genero.ROMANCE);
		assertNotEquals(transacao.generoPreferido(),Genero.DRAMA);
	}
	
	@Test
	public void testeDeDescontoPorGenero() {
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao3);
		transacao.descontoPorGenero(Genero.ROMANCE, 0.5);
		
		assertTrue(locacao1.valorAluguel== 5.0);
		assertTrue(locacao2.valorAluguel== 25.0);
		assertTrue(locacao3.valorAluguel== 30.0);
	}
	
	
	

}
