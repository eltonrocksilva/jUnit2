package br.emprestimo.dadosDeTeste;

import br.emprestimo.modelo.Usuario;

public class ObtemUsuario {
	
	public static Usuario comDadosValidos() {
		Usuario usuario = new Usuario();
		usuario.setNome("Fulano");
		usuario.setRa("147852");
		return usuario;
	}
	
	public static Usuario comDadosInvalidos_nome_emBranco() {
		Usuario usuario = new Usuario();
		usuario.setNome("");
		return usuario;
	}
	
	public static Usuario comDadosInvalidos_nome_nulo() {
		Usuario usuario = new Usuario();
		usuario.setNome(null);
		return usuario;
	}
	
	

}
