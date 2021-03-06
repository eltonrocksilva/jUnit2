package br.emprestimo.testeUnitario;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.emprestimo.dadosDeTeste.ObtemLivro;
import br.emprestimo.dadosDeTeste.ObtemUsuario;
import br.emprestimo.modelo.Emprestimo;
import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.Usuario;
import br.emprestimo.servico.ServicoEmprestimo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class UC01RegistraEmprestimoDeLivro {
	static private Livro livro;
	static private Usuario usuario;
	static private ServicoEmprestimo servico;
	static private Emprestimo emprestimo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//cenario
		livro = ObtemLivro.comDadosValidos();
		/*livro = new Livro();
		livro.setIsbn("121212");
		livro.setTitulo("Engenharia de Software");
		livro.setAutor("Pressman");*/
		usuario = ObtemUsuario.comDadosValidos();
		/*usuario = new Usuario();
		usuario.setRa("11111");
		usuario.setNome("Jose da Silva");*/
		servico = new ServicoEmprestimo();
		emprestimo = new Emprestimo();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Test
	public void CT01UC01FB_registrar_emprestimo_com_sucesso() {
		assertNotNull(servico.empresta(livro, usuario));
	}
	@Test(expected=RuntimeException.class)
	public void CT02UC01FB_registrar_emprestimo_com_dados_invalidos() {
		assertNotNull(servico.empresta(null, usuario));
	}
	@Test
	public void CT03UC01FB_registrar_emprestimo_com_dados_invalidos(){
		try{
			servico.empresta(null, usuario);
			fail ("deveria lan�ar uma exce��o");
		}catch(RuntimeException e){
			assertEquals("Dados inv�lidos.", e.getMessage());
		}
	}
	@Test
	public void CT04UC01FB_registrar_emprestimo_com_sucesso_validacao_da_data() {
		//acao
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		String dataEsperada = new DateTime().plusDays(8).toString(fmt);
		emprestimo = servico.empresta(livro, usuario);
		String dataObtida = emprestimo.getDataDevolucao();
		//verificacao
	    assertTrue(dataEsperada.equals(dataObtida));
	}
	@Test
	public void CT05UC01FB_registrar_emprestimo_com_data_invalida() {
		assertFalse(emprestimo.validaData("29-03-2000"));
	}
	
	@Test(expected=RuntimeException.class)
	public void CT06UC01FB_registrar_emprestimo_livro_invalido_nulo() {
		emprestimo.setLivro(null);
	}
	
	@Test(expected=RuntimeException.class)
	public void CT07UC01FB_registrar_emprestimo_usuario_invalido_nulo() {
		emprestimo.setUsuario(null);
	}
	
	@Test
	public void CT08UC01FB_registrar_emprestimo_com_data_valida() {
		assertTrue(emprestimo.validaData("29/03/2000"));
	}
	
	@Test(expected=RuntimeException.class)
	public void CT09UC01FB_registrar_emprestimo_data_invalida() {
		emprestimo.setDataEmprestimo("29-03-2000");
	}
	
	
	@Test(expected=RuntimeException.class)
	public void CT10UC01FB_registrar_devolucao_data_invalida() {
		emprestimo.setDataDevolucao("29-03-2000");
	}
	
	@Ignore
	@Test
	public void CT11UC01FB_registrar_emprestimo_obtem_data_corrente_manual() {
		assertEquals("26/04/2018",emprestimo.setDataEmprestimo());
	}
	
	@Test
	public void CT12UC01FB_registrar_emprestimo_obtem_data_corrente_sistema() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		assertEquals(new DateTime().toString(fmt),emprestimo.setDataEmprestimo());
	}
	
	@Test
	public void CT13UC01FB_registrar_emprestimo_verifica_dados_de_livro() {
		//cenario
		Livro resultadoEsperado = new Livro();
		resultadoEsperado.setAutor("Pressman");
		resultadoEsperado.setIsbn("2222");
		resultadoEsperado.setTitulo("Engenharia de Software");
		Emprestimo emprestimo = new Emprestimo();
		//acao
		emprestimo.setLivro(resultadoEsperado);
		//verificacao
		assertTrue(resultadoEsperado.equals(emprestimo.getLivro()));
		}
	
	@Test
	public void CT14UC01FB_registrar_emprestimo_verifica_dados_de_usuario() {
		//cenario
		Usuario resultadoEsperado = new Usuario();
		resultadoEsperado.setNome("Fulano");
		resultadoEsperado.setRa("147852");
		Emprestimo emprestimo = new Emprestimo();
		//acao
		emprestimo.setUsuario(resultadoEsperado);
		//verificacao
		assertTrue(resultadoEsperado.equals(emprestimo.getUsuario()));
		}
	
	@Test
	public void CT15UC01FB_registrar_emprestimo_quando_data_domingo_erro() {
		//Cenario
		Emprestimo umEmprestimo = new Emprestimo();
		String data = "29/04/2018"; //domingo
		//acao
		try {
			umEmprestimo.setDataDevolucao(data);
			fail("N�o deveria aceitar uma data no domingo");
		}catch(RuntimeException e){
			//verificacao
			String resultadoEsperado = "Data invalida";
			assertTrue(resultadoEsperado.equals(e.getMessage()));
		}
	}
	
}
