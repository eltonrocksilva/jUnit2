package br.emprestimo.testeUnitario;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.emprestimo.modelo.Usuario;

public class UC02CadastrarUsuario {
public static Usuario usuario;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usuario = new Usuario();
		usuario.setNome("Jose da Silva");
		usuario.setRa("123456");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test(expected=RuntimeException.class)
	public void CT01UC02CadastrarUsuario_com_ra_invalido_branco() {
		usuario.setRa("");
	}
	@Test(expected=RuntimeException.class)
	public void CT02UC02CadastrarUsuario_com_ra_invalido_nulo() {
		usuario.setRa(null);
	}
	@Test
	public void CT03UC02CadastrarUsuario_obtem_ra() {
		usuario.setRa("123456");
		assertEquals("123456",usuario.getRa());
	}

	@Test(expected=RuntimeException.class)
	public void CT04UC02CadastrarUsuario_com_nome_invalido_branco() {
		usuario.setNome("");
	}
	@Test(expected=RuntimeException.class)
	public void CT05UC02CadastrarUsuario_com_nome_invalido_nulo() {
		usuario.setNome(null);
	}
	@Test
	public void CT06UC02CadastrarUsuario_obtem_nome() {
		usuario.setNome("Jose da Silva");
		assertEquals("Jose da Silva",usuario.getNome());
	}
	@Test
	public void CT07UC02CadastrarUsuario_teste_estado() {
		Usuario resultadoEsperado = new Usuario();
		resultadoEsperado.setNome("Jose da Silva");
		resultadoEsperado.setRa("123456");
		assertTrue(resultadoEsperado.equals(usuario));
	}
}
