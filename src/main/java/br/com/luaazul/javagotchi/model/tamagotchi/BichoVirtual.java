
package br.com.luaazul.javagotchi.model.tamagotchi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "TBOD_BICHO_VIRTUAL",indexes = { 
		@Index(name = "IDX_BICHO_VIRTUAL_USUARIO", columnList = "DS_USUARIO"),
		@Index(name = "IDX_BICHO_VIRTUAL_SERVIDOR", columnList = "DS_SERVIDOR"),
		@Index(name = "IDX_BICHO_VIRTUAL_STATUS", columnList = "CD_STATUS")})
public class BichoVirtual {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PET")
	private int id;
	
	@Column(name = "DS_USUARIO",nullable = false)
	private String usuario;
	
	@Column(name = "DS_SERVIDOR",nullable = false)
	private String servidor;
	
	@Column(name = "DS_NOME")
	private String nome;
	
	@Column(name = "VL_VIDA")
	private float vida;
	@Column(name = "VL_VIDA_TOTAL")
	private float vidaTotal;
	
	@Column(name = "VL_ENERGIA")
	private float energia;
	@Column(name = "VL_FELICIDADE")
	private float felicidade;
	@Column(name = "VL_FOME")
	private float fome;
	@Column(name = "VL_LIMPEZA")
	private float limpeza;
	
	
	@Column(name = "VL_EX_PONTOS")
	private float exPontos;
	
	@Column(name = "CD_VIVO")
	private boolean vivo;
	
	@Column(name = "CD_STATUS")
	private char status;
	
	@Column(name = "CD_TIPO_BICHO")
	private int tipoBicho;
	
	public BichoVirtual(){
		
	}

	
	public BichoVirtual doFeed(String idItem) {
		
		this.setFome(getFome() + 30);
		
		if(this.getVida()+10 <this.getVidaTotal()) {
			this.setVida(getVida() + 10);
		}
		this.setEnergia(this.getEnergia()  - 10);
		this.setFelicidade(getFelicidade() + 20);
		
		return this;
	}
	
	public BichoVirtual doSleep(String idItem) {
		this.setFome(getFome() - 10);
		if(this.getVida()+10 <this.getVidaTotal()) {
			this.setVida(getVida() + 40);
		}
		this.setEnergia(this.getEnergia()  + 80);
		this.setFelicidade(getFelicidade() + 20);
		return this;
	}
	
	public BichoVirtual doPlay(String idItem) {
		this.setFome(getFome() - 20);
//		if(this.getVida()+10 <this.getVidaTotal()) {
//			this.setVida(getVida() + 10);
//		}
		this.setEnergia(this.getEnergia()  - 10);
		this.setFelicidade(getFelicidade() + 40);
		return this;
	}
	
	public BichoVirtual doTrain(String idItem) {
		this.setFome(getFome() - 30);
//		if(this.getVida()+10 <this.getVidaTotal()) {
//			this.setVida(getVida() + 10);
//		}
		this.setEnergia(this.getEnergia()  - 20);
		this.setFelicidade(getFelicidade() - 20);
		this.setExPontos(getExPontos()+20);
		
		return this;
		
	}
	
	public BichoVirtual doBath(String idItem) {
		
		this.setFome(getFome() - 10);
//		if(this.getVida()+10 <this.getVidaTotal()) {
//			this.setVida(getVida() + 10);
//		}
		this.setEnergia(this.getEnergia()  - 10);
		this.setFelicidade(getFelicidade() + 30);
		this.setLimpeza(100);
		
		return this;
		
	}
	
	public String getUsuario() {
		return usuario;
	}




	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}




	public float getLimpeza() {
		return limpeza;
	}


	public void setLimpeza(float limpeza) {
		this.limpeza = limpeza;
	}


	public String getServidor() {
		return servidor;
	}




	public void setServidor(String servidor) {
		this.servidor = servidor;
	}




	public char getStatus() {
		return status;
	}




	public void setStatus(char status) {
		this.status = status;
	}




	public int getTipoBicho() {
		return tipoBicho;
	}




	public void setTipoBicho(int tipoBicho) {
		this.tipoBicho = tipoBicho;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public float getVida() {
		return vida;
	}


	public void setVida(float vida) {
		this.vida = vida;
	}


	public float getVidaTotal() {
		return vidaTotal;
	}


	public void setVidaTotal(float vidaTotal) {
		this.vidaTotal = vidaTotal;
	}


	public float getEnergia() {
		return energia;
	}


	public void setEnergia(float energia) {
		this.energia = energia;
	}


	public float getFelicidade() {
		return felicidade;
	}


	public void setFelicidade(float felicidade) {
		this.felicidade = felicidade;
	}


	public float getFome() {
		return fome;
	}


	public void setFome(float fome) {
		this.fome = fome;
	}


	public float getExPontos() {
		return exPontos;
	}


	public void setExPontos(float exPontos) {
		this.exPontos = exPontos;
	}


	public boolean isVivo() {
		return vivo;
	}


	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	
	
	
}
