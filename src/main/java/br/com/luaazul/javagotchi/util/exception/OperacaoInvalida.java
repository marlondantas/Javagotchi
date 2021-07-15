package br.com.luaazul.javagotchi.util.exception;

public class OperacaoInvalida extends RuntimeException {
	
	private String mensagem;
	
	public OperacaoInvalida(String meString)
	{
		this.mensagem = meString;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
}
