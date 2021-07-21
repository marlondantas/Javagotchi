package br.com.luaazul.javagotchi.model.tamagotchi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.luaazul.javagotchi.model.tamagotchi.enuns.EstadoTipoBichoENUM;

@Entity
@Table(name = "TBOD_ESTADO_TIPO_BICHO")
public class EstadoTipoBicho {

	@Id
	@Column(name = "CD_TIPO_BICHO")
	private int id;
	
	@Enumerated
	@Column(name = "CD_ESTADO")
	private EstadoTipoBichoENUM estado;
	
	@Column(name = "DS_URL")
	private String url;
	
	public EstadoTipoBicho() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EstadoTipoBichoENUM getEstado() {
		return estado;
	}

	public void setEstado(EstadoTipoBichoENUM estado) {
		this.estado = estado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
