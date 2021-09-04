package ec.edu.ups.modelo;

import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Grupo {

	@Id
	private int id;
	
	@NotNull
	private String descripcion;
	
	@OneToOne
	private Usuario ninio1;
	
	@OneToOne
	private Usuario ninio2;
	
	@OneToOne
	private Usuario terapista;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getNinio1() {
		return ninio1;
	}

	public void setNinio1(Usuario ninio1) {
		this.ninio1 = ninio1;
	}

	public Usuario getNinio2() {
		return ninio2;
	}

	public void setNinio2(Usuario ninio2) {
		this.ninio2 = ninio2;
	}

	public Usuario getTerapista() {
		return terapista;
	}

	public void setTerapista(Usuario terapista) {
		this.terapista = terapista;
	}
	
	
	
	
}
