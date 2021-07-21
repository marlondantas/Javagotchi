package br.com.luaazul.javagotchi.model.tamagotchi.enuns;

public enum EstadoTipoBichoENUM {

	NORMAL("Normal",0);
	
	EstadoTipoBichoENUM(){
		
	}
	
	private String estado;
	private int chave;
	
	EstadoTipoBichoENUM(String estado, int key){
		this.estado = estado;
		this.chave = key;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public int getChave() {
		return this.chave;
	}
	
}
