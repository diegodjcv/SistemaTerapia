package ec.edu.ups.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Reporte {

	@Id
	@Column(unique = true)
	@NotNull
	private int codigo;
	
	@NotNull
	private Date fecha;
	
	@NotNull
	private String observaciones;

	@ManyToOne 
	@JoinColumn(name = "ninio_cedula")
	private Usuario ninio;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return observaciones;
	}

	public void setDescripcion(String descripcion) {
		this.observaciones = descripcion;
	}

	public Usuario getNinio() {
		return ninio;
	}

	public void setNinio(Usuario ninio) {
		this.ninio = ninio;
	}
	
	
	
	
	
}
