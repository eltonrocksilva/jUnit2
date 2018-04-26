package br.emprestimo.dadosDeTeste;

import br.emprestimo.modelo.Livro;

public class ObtemLivro {

	public static Livro comDadosValidos() {
		Livro livro = new Livro();
		livro.setAutor("Pressman");
		livro.setIsbn("2222");
		livro.setTitulo("Engenharia de Software");
		return livro;
	}
	
	public static Livro comISBNInvalido_branco() {
		Livro livro = new Livro();
		livro.setIsbn("");
		return livro;
	}
	
	public static Livro comISBNInvalido_nulo() {
		Livro livro = new Livro();
		livro.setIsbn(null);
		return livro;
	}
	
}
