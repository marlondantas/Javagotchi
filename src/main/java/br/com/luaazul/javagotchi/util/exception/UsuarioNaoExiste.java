package br.com.luaazul.javagotchi.util.exception;

public class UsuarioNaoExiste  extends RuntimeException {
	@Override
	  public String getMessage(){
	    return "Usuario não tem  bicho virtual";
	  }
}
