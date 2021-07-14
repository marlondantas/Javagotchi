package br.com.luaazul.javagotchi.view;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import br.com.luaazul.javagotchi.util.exception.UsuarioNaoExiste;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoTemBichoVitual;

public abstract class AbstractOperacao implements MessageCreateListener{
	
	@Override
    public void onMessageCreate(MessageCreateEvent event) {
		execute(event);
    }
	
	public void execute(MessageCreateEvent event) {
		//DO something
	}
	
	public void execute(MessageCreateEvent event, String[] arrayParametros) throws UsuarioNaoExiste, UsuarioNaoTemBichoVitual, Exception {
		//DO something
	}
	
}
