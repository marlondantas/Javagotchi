package br.com.luaazul.javagotchi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;

@Entity
@Table( name = "TBOD_USUARIO",indexes = { @Index(name = "IDX_USUARIO", columnList = "DS_USUARIO") })
public class Usuario {

	@Id
	@Column(name = "DS_USUARIO",nullable = false)
	private String usuario;
	
	@Column(name = "DS_NOME")
	private String nome;
	@Column(name = "DS_SENHA")
	private String senha;
	//MD5
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<BichoVirtual> pets;
	
	@Column(name = "VL_DINHEIRO")
	private float dinheiro;
	
	@Transient
	private String servidor;
	
	public Usuario(){
		
	}


	public Usuario(String idUsuario, String idServidor) {
		this.setServidor(idServidor);
		this.setUsuario(idUsuario);
	}


	public float getDinheiro() {
		return dinheiro;
	}


	public void setDinheiro(float dinheiro) {
		this.dinheiro = dinheiro;
	}


	public String getServidor() {
		return servidor;
	}


	public void setServidor(String servidor) {
		this.servidor = servidor;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<BichoVirtual> getPets() {
		return pets;
	}

	public void setPets(List<BichoVirtual> pets) {
		this.pets.clear();
		// TODO correcao de encpsulamento
	}
	
	
}
