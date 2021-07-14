package br.com.luaazul.javagotchi.util.exception;

public class UsuarioNaoTemBichoVitual  extends RuntimeException {
	@Override
	  public String getMessage(){
	    return "Usuario não existe";
	  }
}
