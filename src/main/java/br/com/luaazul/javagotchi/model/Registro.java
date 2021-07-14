package br.com.luaazul.javagotchi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "TBOD_REGISTRO")
public class Registro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_REGISTRO")
	private int id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_REGISTRO")
	
	private Date dataRegistro;
	@Column(name = "DS_OPERACAO")
	private String operacao;
	@Column(name = "CD_USUARIO")
	private String usuario;
	@Column(name = "CD_STATUS")
	private int status;
	
	@Column(name = "DS_RESPONSE")
	private String response;
	@Column(name = "DS_REQUEST")
	private String request;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	public Registro(){
		
	}
	
	public Registro(String usuario, String mensagem, String name) {
		
		this.setUsuario(usuario);
		this.setRequest(mensagem);
		this.setOperacao(name);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
